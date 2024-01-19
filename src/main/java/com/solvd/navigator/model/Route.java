package com.solvd.navigator.model;

import java.util.Objects;

public class Route {
    private Integer id;
    private Station from;
    private Station to;

    public Route(Integer id, Station from, Station to) {
        this.id = id;
        this.from = from;
        this.to = to;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Station getFrom() {
        return from;
    }

    public void setFrom(Station from) {
        this.from = from;
    }

    public Station getTo() {
        return to;
    }

    public void setTo(Station to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(id, route.id) && Objects.equals(from, route.from) && Objects.equals(to, route.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, from, to);
    }
}
