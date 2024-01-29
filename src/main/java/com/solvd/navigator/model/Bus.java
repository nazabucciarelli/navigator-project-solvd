package com.solvd.navigator.model;

import java.util.List;

public class Bus {
    private int id;
    private String name;
    private List<Station> stations;

    public Bus() {
        this.id = 0;
        this.name = "";
        this.stations = null;
    }

    public Bus(int id, String name, List<Station> stations) {
        this.id = id;
        this.name = name;
        this.stations = stations;
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

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stations=" + stations +
                '}';
    }
}
