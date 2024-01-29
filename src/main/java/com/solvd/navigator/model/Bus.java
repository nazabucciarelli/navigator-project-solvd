package com.solvd.navigator.model;

public class Bus {
    private int id;
    private String name;

    public Bus(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Bus() {
        this.id = 0;
        this.name = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}