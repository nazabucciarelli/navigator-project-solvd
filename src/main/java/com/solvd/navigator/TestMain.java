package com.solvd.navigator;

import com.solvd.navigator.model.BusStationStatus;
import com.solvd.navigator.model.exceptions.NoBusContinuityException;
import com.solvd.navigator.service.MatrixService;
import com.solvd.navigator.util.BusPath;

import java.util.List;

public class TestMain {

    public static void main(String[] args) {
        int[][] graph = MatrixService.generateGraphMatrix();
        BusPath busPath = new BusPath();
        List<BusStationStatus> path = null;
        try {
            path = busPath.findBusPath(graph, 5, 3);
        } catch (NoBusContinuityException e) {
            throw new RuntimeException(e);
        }
        path.forEach(System.out::println);
    }
}
