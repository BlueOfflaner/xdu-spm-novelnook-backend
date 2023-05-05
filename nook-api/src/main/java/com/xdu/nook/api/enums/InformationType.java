package com.xdu.nook.api.enums;

public enum InformationType {
    ACKNOWLEDGE("acknowledge"),
    INFORM("inform");

    private final String name;

    InformationType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
