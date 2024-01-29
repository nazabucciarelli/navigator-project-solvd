package com.solvd.navigator.util;

import java.util.LinkedList;
import java.util.List;

public class Utils {

    public static int[][] copyArray(int[][] matrix) {
        int[][] m = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, m[i], 0, matrix[i].length);
        }
        return m;
    }

    public static void floydWarshallAlgorithm(int[][] dist, int[][] pathMatrix, int INF) {
        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist.length; j++) {
                if (dist[i][j] == INF) {
                    pathMatrix[i][j] = -1;
                } else {
                    pathMatrix[i][j] = i;
                }
            }
        }

        for (int k = 0; k < dist.length; k++) {
            for (int i = 0; i < dist.length; i++) {
                for (int j = 0; j < dist.length; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        pathMatrix[i][j] = pathMatrix[k][j];
                    }
                }
            }
        }
    }

    public static List<Integer> pathFromAtoB(int[][] paths, int startStation, int endStation) {
        //Initialize list to add stations to follow to arrive at the final station
        List<Integer> list = new LinkedList<>();

        int end = endStation;
        list.addFirst(end);

        while (paths[startStation][end] != startStation) {
            list.addFirst(paths[startStation][end]);
            end = paths[startStation][end];
        }

        list.addFirst(startStation);

        return list;
    }
}
