package com.solvd.navigator.services;

import com.solvd.navigator.dao.IRoadDao;
import com.solvd.navigator.dao.IStationDao;
import com.solvd.navigator.dao.jdbc.RoadDao;
import com.solvd.navigator.dao.jdbc.StationDao;
import com.solvd.navigator.model.Road;
import com.solvd.navigator.model.Station;

import java.util.List;

public class MatrixService {
    private static final int INF = 9999;

    public static int[][] generateGraphMatrix() {
        IRoadDao roadDao = new RoadDao();
        List<Road> roads = roadDao.getAll();
        IStationDao stationDao = new StationDao();
        List<Station> stations = stationDao.getAll();

        int size = stations.size();

        int[][] matrix = new int[size][size];

        for (Road road : roads) {
            matrix[road.getFromStationId()-1][road.getToStationId()-1] = (int)road.getDistance();
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0 && !(i==j)) {
                    matrix[i][j] = INF;
                }
            }
        }
        return matrix;
    }

    public static String[][] prettifyMatrix(int[][] matrix) {
        int size = matrix.length;
        String[][] graph = new String[size][size];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == INF) {
                    graph[i][j] = "INF";
                } else {
                    graph[i][j] = String.valueOf(matrix[i][j]);
                }
            }
        }
        return graph;
    }
}
