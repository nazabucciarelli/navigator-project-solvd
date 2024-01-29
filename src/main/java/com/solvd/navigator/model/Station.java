package com.solvd.navigator.model;

import java.util.List;

public class Station {
    private int id;
    private String name;
    private List<Integer> busesId;

    public Station(int id, String name, List<Integer> busesId) {
        this.id = id;
        this.name = name;
        this.busesId = busesId;
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

    public List<Integer> getBusesId() {
        return busesId;
    }

    public void setBusesId(List<Integer> busesId) {
        this.busesId = busesId;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", buses=" + busesId +
                '}';
    }
}