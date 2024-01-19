package com.solvd.navigator.model;

import java.util.Objects;

public class Bus {
    private Integer id;
    private String identifierLetter;

    public Bus(Integer id, String identifierLetter) {
        this.id = id;
        this.identifierLetter = identifierLetter;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentifierLetter() {
        return identifierLetter;
    }

    public void setIdentifierLetter(String identifierLetter) {
        this.identifierLetter = identifierLetter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bus bus = (Bus) o;
        return Objects.equals(id, bus.id) && Objects.equals(identifierLetter, bus.identifierLetter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, identifierLetter);
    }

    @Override
    public String toString() {
        return "Bus{" +
                "id=" + id +
                ", identifierLetter='" + identifierLetter + '\'' +
                '}';
    }
}
