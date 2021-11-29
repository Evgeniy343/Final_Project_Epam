package com.epam.jwd.web.db;

import com.epam.jwd.web.db.impl.ConnectionPool;
import com.epam.jwd.web.exception.CouldNotInitializeConnectionPoolError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockingConnectionPool implements ConnectionPool {

    private static final Logger LOGGER = LogManager.getLogger(LockingConnectionPool.class);
    private static final int MINIMAL_CONNECTIONS_AMOUNT = 8;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/epam_cafe";
    private static final String DB_USER = "Zhendos";
    private static final String DB_PASSWORD = "27J11e20K01a";
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition needInConnections = lock.newCondition();
    private static final Double POOL_EXPANSION_COEFFICIENT = 0.5;
    private static final Double POOL_COMPRESSION_COEFFICIENT = 0.2;
    private static final Long COLLECTOR_TIME_INTERVAL = 60000L;
    public static final double EXPANSION_LIMIT = 0.75;

    private final Queue<ProxyConnection> availableConnections;
    private final List<ProxyConnection> givenAwayConnections;
    private volatile static LockingConnectionPool instance;
    private boolean initialized = false;
    private volatile boolean isCreateConnections = false;
    private ConnectionTTL connectionTTL;


    private LockingConnectionPool() {
        this.availableConnections = new ArrayDeque<>();
        this.givenAwayConnections = new ArrayList<>();
    }

    @Override
    public boolean init() {
        lock.lock();
        try {
            if (!initialized) {
                registerDrivers();
                initializeConnections(MINIMAL_CONNECTIONS_AMOUNT, true);
                connectionTTL = new ConnectionTTL();
                initialized = true;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean shutdown() {
        lock.lock();
        try {
            if (initialized) {
                closeConnections();
                deregisterDrivers();
                connectionTTL.shutDown();
                initialized = false;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Connection takeConnection() throws InterruptedException {
        lock.lock();
        try {
            while (availableConnections.isEmpty()) {
                needInConnections.await();
            }
            final ProxyConnection connection = availableConnections.poll();
            givenAwayConnections.add(connection);
            return connection;
        } finally {
            if ((calculateAmountOfConnections() * EXPANSION_LIMIT < givenAwayConnections.size()
                    && !isCreateConnections)) {
                expansionConnectionPool();
            }
            lock.unlock();
        }
    }

    @Override
    @SuppressWarnings("SuspiciousMethodCalls")
    public void returnConnection(Connection connection) {
        lock.lock();
        try {
            if (givenAwayConnections.remove(connection)) {
                availableConnections.add((ProxyConnection) connection);
                needInConnections.signalAll();
            } else {
                LOGGER.warn("Attempt to add unknown connection to Connection Pool. Connection: {}", connection);
            }
        } finally {
            lock.unlock();
        }
    }

    private void expansionConnectionPool() {
        new Thread(() -> initializeConnections(calculateAmountOfConnections()
                * POOL_EXPANSION_COEFFICIENT, true)).start();
    }

    private void initializeConnections(double amount, boolean failOnConnectionException) {
        try {
            List<ProxyConnection> connections = new ArrayList<>();
            for (int i = 0; i < amount; i++) {
                final Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                LOGGER.info("initialized connection {}", conn);
                final ProxyConnection proxyConnection = ProxyConnection.of(conn, this);
                connections.add(proxyConnection);
            }
            if (availableConnections.isEmpty()) {
                availableConnections.addAll(connections);
            } else {
                pushAvailableConnections(connections);
            }
        } catch (SQLException e) {
            LOGGER.error("Error occurred creating Connection");
            if (failOnConnectionException) {
                throw new CouldNotInitializeConnectionPoolError("Failed to create Connection", e);
            }
        }
    }

    private void pushAvailableConnections(List<ProxyConnection> connections) {
        lock.lock();
        try {
            availableConnections.addAll(connections);
        } finally {
            needInConnections.signalAll();
            isCreateConnections = false;
            lock.unlock();
        }
    }

    public static LockingConnectionPool getInstance() {
        if (instance == null) {
            lock.lock();
            {
                if (instance == null) {
                    instance = new LockingConnectionPool();
                }
            }
            lock.unlock();
        }
        return instance;
    }

    private void registerDrivers() {
        LOGGER.trace("driver registration");
        try {
            DriverManager.registerDriver(DriverManager.getDriver(DB_URL));
        } catch (SQLException e) {
            throw new CouldNotInitializeConnectionPoolError("Failed to register driver", e);
        }
    }

    private void closeConnections() {
        closeConnections(this.availableConnections);
        closeConnections(this.givenAwayConnections);
    }

    private void closeConnections(Collection<ProxyConnection> connections) {
        for (ProxyConnection conn : connections) {
            closeConnection(conn);
            connections.remove(conn);
        }
    }

    private void closeConnections(Queue<ProxyConnection> availableConnections, int amount) {
        while (amount != 0) {
            Optional<ProxyConnection> connection = Optional.ofNullable(availableConnections.poll());
            if (connection.isPresent()) {
                closeConnection(connection.get());
                availableConnections.remove(connection.get());
            }
            amount--;
        }
    }


    private void closeConnection(ProxyConnection conn) {
        try {
            conn.realClose();
            LOGGER.info("closed connection {}", conn);
        } catch (SQLException e) {
            LOGGER.error("Could not close connection", e);
        }
    }

    private static void deregisterDrivers() {
        LOGGER.trace("unregistering sql drivers");
        final Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            try {
                DriverManager.deregisterDriver(drivers.nextElement());
            } catch (SQLException e) {
                LOGGER.error("could not deregister driver", e);
            }
        }
    }

    private int calculateAmountOfConnections() {
        return availableConnections.size() + givenAwayConnections.size();
    }

    private class ConnectionTTL {

        private final Timer timer = new Timer();

        public ConnectionTTL() {
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    if (availableConnections.size() > MINIMAL_CONNECTIONS_AMOUNT) {
                        LOGGER.trace("Compression of connection pool");
                        closeConnections(availableConnections, removedConnectionsNum());
                    }
                }
            };
            timer.schedule(timerTask, COLLECTOR_TIME_INTERVAL, COLLECTOR_TIME_INTERVAL);
        }

        private int removedConnectionsNum() {
            final int removedConnectionsNum = (int) (availableConnections.size() * POOL_COMPRESSION_COEFFICIENT);
            if (availableConnections.size() - removedConnectionsNum >= MINIMAL_CONNECTIONS_AMOUNT) {
                return removedConnectionsNum;
            } else {
                return availableConnections.size() - MINIMAL_CONNECTIONS_AMOUNT;
            }
        }

        private void shutDown() {
            timer.cancel();
        }
    }
}
