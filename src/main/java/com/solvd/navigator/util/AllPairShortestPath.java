package com.solvd.navigator.util;

import com.solvd.navigator.model.PathContainer;
import com.solvd.navigator.service.StationService;

import java.lang.*;
import java.util.*;

public class AllPairShortestPath {
    private StationService stationService;
    public final static int INF = 99999;
    public final int V;

    public AllPairShortestPath() {
        this.stationService = new StationService();
        this.V = stationService.getStationsAmount();
    }

    public int[][] floydWarshall(int[][] dist, int startStation, int endStation) {
        int i, j, k;
        //startStation endStation never used
        for (k = 0; k < V; k++) {
            for (i = 0; i < V; i++) {
                for (j = 0; j < V; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        return dist;
    }

    public PathContainer floydWarshallWithPath(int[][] dist, int startStation, int endStation) {
        startStation -= 1;
        endStation -=1;
        //Path matrix
        int[][] pathMatrix = new int[V][V];

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

        List<Integer> pathFromAtoB = pathFromAtoB(pathMatrix, startStation, endStation);

        return new PathContainer(dist[startStation][endStation], pathFromAtoB);
    }

    public List<Integer> pathFromAtoB(int[][] paths, int startStation, int endStation) {

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

    public void printSolution(int[][] dist) {
        System.out.println(
                "The following matrix shows the shortest "
                        + "distances between every pair of vertices");
        for (int i = 0; i < V; ++i) {
            for (int j = 0; j < V; ++j) {
                if (dist[i][j] == INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }
    }

}

