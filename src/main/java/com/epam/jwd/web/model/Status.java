package com.epam.jwd.web.model;

import java.util.Arrays;
import java.util.List;

public enum Status {
    ACCEPTED_FOR_PROCESSING,
    PREPARING,
    READY;

    private static final List<Status> ALL_AVAILABLE_STATUS = Arrays.asList(values());

    public static List<Status> valuesAsList() {
        return ALL_AVAILABLE_STATUS;
    }

    public static Status of(String name) {
        for (Status status : values()) {
            if (status.name().equalsIgnoreCase(name)) {
                return status;
            }
        }
        return ACCEPTED_FOR_PROCESSING;
    }
}
