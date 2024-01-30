package com.solvd.navigator.model;

import com.solvd.navigator.service.StationService;

import java.util.List;
import java.util.Objects;

public class BusStationStatus {
    private int getOffBusId;
    private int getOnBusId;
    private int currentBusId;
    private int stationId;

    public BusStationStatus(int getOffBusId, int getOnBusId, int currentBusId, int stationId) {
        this.getOffBusId = getOffBusId;
        this.getOnBusId = getOnBusId;
        this.currentBusId = currentBusId;
        this.stationId = stationId;
    }

    public int getGetOffBusId() {
        return getOffBusId;
    }

    public void setGetOffBusId(int getOffBusId) {
        this.getOffBusId = getOffBusId;
    }

    public int getGetOnBusId() {
        return getOnBusId;
    }

    public void setGetOnBusId(int getOnBusId) {
        this.getOnBusId = getOnBusId;
    }

    public int getCurrentBusId() {
        return currentBusId;
    }

    public void setCurrentBusId(int currentBusId) {
        this.currentBusId = currentBusId;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BusStationStatus that)) return false;
        return getGetOffBusId() == that.getGetOffBusId() &&
                getGetOnBusId() == that.getGetOnBusId() &&
                getCurrentBusId() == that.getCurrentBusId() &&
                getStationId() == that.getStationId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGetOffBusId(),
                getGetOnBusId(),
                getCurrentBusId(),
                getStationId());
    }

    @Override
    public String toString() {
        StationService stationService = new StationService();
        List<String> names = stationService.getAllStationsNames();
        return "BusStationStatus{" +
                "getOffBusId=" + getOffBusId +
                ", getOnBusId=" + getOnBusId +
                ", currentBusId=" + currentBusId +
                ", stationId=" + names.get(stationId) +
                '}';
    }
}
