package com.solvd.navigator.model;

import java.util.Objects;

public class Street {
    private Integer id;
    private String name;

    public Street(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Street street = (Street) o;
        return Objects.equals(id, street.id) && Objects.equals(name, street.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
