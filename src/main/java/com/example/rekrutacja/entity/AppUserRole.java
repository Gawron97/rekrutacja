package com.example.rekrutacja.entity;

public enum AppUserRole {
    ADMIN(Names.ADMIN_NAME, RoleNames.ADMIN_ROLE),
    ADMINISTRATION_EMPLOYEE(Names.ADMINISTRATION_EMPLOYEE_NAME, RoleNames.ADMINISTRATION_EMPLOYEE_ROLE),
    CANDIDATE(Names.CANDIDATE_NAME, RoleNames.CANDIDATE_ROLE);

    public class Names {
        public static final String ADMIN_NAME = "ADMIN";
        public static final String ADMINISTRATION_EMPLOYEE_NAME = "ADMINISTRATION_EMPLOYEE";
        public static final String CANDIDATE_NAME = "CANDIDATE";
    }

    public class RoleNames {
        public static final String ADMIN_ROLE = "ROLE_ADMIN";
        public static final String ADMINISTRATION_EMPLOYEE_ROLE = "ROLE_ADMINISTRATION_EMPLOYEE";
        public static final String CANDIDATE_ROLE = "ROLE_CANDIDATE";
    }

    private final String label;
    private final String roleLabel;

    AppUserRole(String label, String roleLabel) {
        this.label = label;
        this.roleLabel = roleLabel;
    }

    public String roleName() {
        return roleLabel;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
