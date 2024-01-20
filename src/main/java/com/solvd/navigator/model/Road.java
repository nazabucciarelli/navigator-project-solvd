package com.solvd.navigator.model;

public class Road {
    private int id;
    private int fromStationId;
    private int toStationId;
    private float distance;

    public Road(int id, int fromStationId, int toStationId, float distance) {
        this.id = id;
        this.fromStationId = fromStationId;
        this.toStationId = toStationId;
        this.distance = distance;
    }

    public Road() {
        this.id = 0;
        this.fromStationId = 0;
        this.toStationId = 0;
        this.distance = 0.0F;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromStationId() {
        return fromStationId;
    }

    public void setFromStationId(int fromStationId) {
        this.fromStationId = fromStationId;
    }

    public int getToStationId() {
        return toStationId;
    }

    public void setToStationId(int toStationId) {
        this.toStationId = toStationId;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Road{" +
                "id=" + id +
                ", fromStationId=" + fromStationId +
                ", toStationId=" + toStationId +
                ", distance=" + distance +
                '}';
    }
}
