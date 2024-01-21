package com.solvd.navigator.model;

import java.util.List;

public class PathContainer {
    private int shortestDistance;
    private List<Integer> pathFromAtoB;

    public PathContainer(int shortestDistance, List<Integer> pathFromAtoB) {
        this.shortestDistance = shortestDistance;
        this.pathFromAtoB = pathFromAtoB;
    }

    public int getShortestDistance() {
        return shortestDistance;
    }

    public void setShortestDistance(int shortestDistance) {
        this.shortestDistance = shortestDistance;
    }

    public List<Integer> getPathFromAtoB() {
        return pathFromAtoB;
    }

    public void setPathFromAtoB(List<Integer> pathFromAtoB) {
        this.pathFromAtoB = pathFromAtoB;
    }
}
