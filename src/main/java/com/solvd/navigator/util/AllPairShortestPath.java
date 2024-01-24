package com.solvd.navigator.util;

import com.solvd.navigator.model.PathContainer;
import com.solvd.navigator.model.exceptions.NoSecondPathException;
import com.solvd.navigator.service.StationService;

import java.util.LinkedList;
import java.util.List;

public class AllPairShortestPath {
    private final StationService stationService;
    public final static int INF = 99999;
    public final int V;

    public AllPairShortestPath() {
        this.stationService = new StationService();
        this.V = stationService.getStationsAmount();
    }

    private void floydWarshallAlgorithm(int[][] dist, int[][] pathMatrix) {
        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist.length; j++) {
                if (dist[i][j] == INF) {
                    pathMatrix[i][j] = -1;
                } else {
                    pathMatrix[i][j] = i;
                }
            }
        }

        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        pathMatrix[i][j] = pathMatrix[k][j];
                    }
                }
            }
        }
    }

    public PathContainer floydWarshallWithShortestPath(int[][] dist, int startStation, int endStation) {
        startStation -= 1;
        endStation -= 1;
        //Path matrix
        int[][] pathMatrix = new int[V][V];

        int[][] auxDist = copyArray(dist); // I have to make a copy, because otherwise the algorithm will modify the matrix from the calling method

        floydWarshallAlgorithm(auxDist, pathMatrix);

        List<Integer> pathFromAtoB = pathFromAtoB(pathMatrix, startStation, endStation);

        return new PathContainer(auxDist[startStation][endStation], pathFromAtoB);
    }

    public PathContainer floydWarshallWithSecondShortestPath(int[][] dist, int startStation, int endStation) throws NoSecondPathException {
        startStation -= 1;
        endStation -= 1;
        //Path matrix

        int[][] shortestDist = copyArray(dist);
        int[][] shortestPathMat = new int[V][V];
        floydWarshallAlgorithm(shortestDist, shortestPathMat);

        List<Integer> shortestPath = pathFromAtoB(shortestPathMat, startStation, endStation);

        int[] path = shortestPath.stream().mapToInt(i -> i).toArray();
        int[][] secondDist = copyArray(dist);
        int[][] secondPath = new int[V][V];
        int secondShortestDistance = INF;

        for (int i = 1; i < path.length; i++) {
            int[][] tempDist = copyArray(dist);
            int[][] tempPath = new int[V][V];

            // I block the path between node i and i-1 of the least path, so the
            // algorithm has to find another path, one of these paths will be the
            // second least path, I just need to compare them and find it
            tempDist[path[i]][path[i - 1]] = INF;
            tempDist[path[i - 1]][path[i]] = INF;

            floydWarshallAlgorithm(tempDist, tempPath);
            if (tempDist[startStation][endStation] < secondShortestDistance) {
                secondShortestDistance = tempDist[startStation][endStation];
                secondDist = copyArray(tempDist);
                secondPath = copyArray(tempPath);
            }
        }

        if (secondShortestDistance == INF) {
            List<String> stations = stationService.getAllStationsNames();

            throw new NoSecondPathException(String.format("There is no alternative path between %s and %s",
                    stations.get(startStation), stations.get(endStation)));
        }

        List<Integer> secondShortestPath = pathFromAtoB(secondPath, startStation, endStation);

        return new PathContainer(secondDist[startStation][endStation], secondShortestPath);
    }

    private int[][] copyArray(int[][] matrix) {
        int[][] m = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, m[i], 0, matrix[i].length);
        }
        return m;
    }

    private List<Integer> pathFromAtoB(int[][] paths, int startStation, int endStation) {
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

