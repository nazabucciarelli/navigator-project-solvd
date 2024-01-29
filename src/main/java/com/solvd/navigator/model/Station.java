package com.solvd.navigator.model;

import java.util.List;

public class Station {
    private int id;
    private String name;
    private List<Integer> buses;

    public Station(int id, String name, List<Integer> buses) {
        this.id = id;
        this.name = name;
        this.buses = buses;
    }

    public Station(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Station() {
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

    public List<Integer> getBuses() {
        return buses;
    }

    public void setBuses(List<Integer> buses) {
        this.buses = buses;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", buses=" + buses +
                '}';
    }
}