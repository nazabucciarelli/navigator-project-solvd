package com.solvd.navigator.model;

import java.util.List;
import java.util.Objects;

public class Station {
    private Integer id;
    private String name;
    private Integer xCordenate;
    private Integer yCordenate;
    private List<Bus> buses;
    private Street street;


    public Station(Integer id, String name, Integer xCordenate, Integer yCordenate, List<Bus> buses, Street street) {
        this.id = id;
        this.name = name;
        this.xCordenate = xCordenate;
        this.yCordenate = yCordenate;
        this.buses = buses;
        this.street = street;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getxCordenate() {
        return xCordenate;
    }

    public void setxCordenate(Integer xCordenate) {
        this.xCordenate = xCordenate;
    }

    public Integer getyCordenate() {
        return yCordenate;
    }

    public void setyCordenate(Integer yCordenate) {
        this.yCordenate = yCordenate;
    }

    public List<Bus> getBuses() {
        return buses;
    }

    public void setBuses(List<Bus> buses) {
        this.buses = buses;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return Objects.equals(id, station.id) && Objects.equals(name, station.name) && Objects.equals(xCordenate, station.xCordenate) && Objects.equals(yCordenate, station.yCordenate) && Objects.equals(buses, station.buses) && Objects.equals(street, station.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, xCordenate, yCordenate, buses, street);
    }
}
