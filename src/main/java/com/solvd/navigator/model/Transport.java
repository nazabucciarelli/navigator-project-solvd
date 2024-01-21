package com.solvd.navigator.model;

public enum Transport {
    CAR("car"), BUS("bus");

    private final String name;

    Transport(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
