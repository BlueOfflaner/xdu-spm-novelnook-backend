package com.xdu.nook.api.enums;

public enum InformationSrc {
    MESSAGE("message"),
    Material("material"),
    User("user");


    private final String name;

    InformationSrc(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
