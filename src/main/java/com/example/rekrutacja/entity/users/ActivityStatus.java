package com.example.rekrutacja.entity.users;

public enum ActivityStatus {

    ACTIVE(Names.ACTIVE_NAME), INACTIVE(Names.INACTIVE_NAME);

    public class Names {
        public static final String ACTIVE_NAME = "ACTIVE";
        public static final String INACTIVE_NAME = "INACTIVE";
    }

    private final String label;

    ActivityStatus(String label) {
        this.label = label;
    }

    public String toString() {
        return this.label;
    }
}
