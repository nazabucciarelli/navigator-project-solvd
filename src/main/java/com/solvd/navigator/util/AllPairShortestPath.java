package com.solvd.navigator.util;

import com.solvd.navigator.model.PathContainer;
import com.solvd.navigator.model.exceptions.NoSecondPathException;
import com.solvd.navigator.service.StationService;

import java.util.LinkedList;
import java.util.List;

import static com.solvd.navigator.util.Utils.*;

public class AllPairShortestPath {
    private final StationService stationService;
    public final static int INF = 99999;
    public final int V;

    public AllPairShortestPath() {
        this.stationService = new StationService();
        this.V = stationService.getStationsAmount();
    }

    public PathContainer floydWarshallWithShortestPath(int[][] dist, int startStation, int endStation) {
        startStation -= 1;
        endStation -= 1;
        //Path matrix
        int[][] pathMatrix = new int[V][V];

        int[][] auxDist = copyArray(dist); // I have to make a copy, because otherwise the algorithm will modify the matrix from the calling method

        floydWarshallAlgorithm(auxDist, pathMatrix,INF);

        List<Integer> pathFromAtoB = pathFromAtoB(pathMatrix, startStation, endStation);

        return new PathContainer(auxDist[startStation][endStation], pathFromAtoB);
    }

    public PathContainer floydWarshallWithSecondShortestPath(int[][] dist, int startStation, int endStation) throws NoSecondPathException {
        startStation -= 1;
        endStation -= 1;
        //Path matrix

        int[][] shortestDist = copyArray(dist);
        int[][] shortestPathMat = new int[V][V];
        floydWarshallAlgorithm(shortestDist, shortestPathMat, INF);

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

            floydWarshallAlgorithm(tempDist, tempPath, INF);
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
}

