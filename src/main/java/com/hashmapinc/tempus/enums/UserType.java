package com.hashmapinc.tempus.enums;

import lombok.Getter;

@Getter
public enum UserType {
    TENANT("Tenant administrator"), CUSTOMER("Business Unit"), SYS_ADMIN("System administrator");

    private String role;

    UserType(String role) {
        this.role = role;
    }
}
