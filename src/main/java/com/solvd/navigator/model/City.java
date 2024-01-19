package com.solvd.navigator.model;

import java.util.List;

public class City {
    private Integer id;
    private String name;
    private List<Street> streets;

    public City(Integer id, String name, List<Street> streets) {
        this.id = id;
        this.name = name;
        this.streets = streets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Street> getStreets() {
        return streets;
    }

    public void setStreets(List<Street> streets) {
        this.streets = streets;
    }
}
