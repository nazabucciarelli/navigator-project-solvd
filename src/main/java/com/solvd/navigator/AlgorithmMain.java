package com.solvd.navigator;

import com.solvd.navigator.model.util.AllPairShortestPath;

import static com.solvd.navigator.model.util.AllPairShortestPath.INF;

public class AlgorithmMain {
    public static void main(String[] args) {

        shortestDistance(1, 4);

    }

    public static void shortestDistance(int startStation, int endStation){

        int graph[][] = {
                { 0  , 5  , INF, 10 },
                { INF, 0  , 3  , INF},
                { INF, INF, 0  , 1  },
                { INF, INF, INF, 0  }
        };

        AllPairShortestPath allPairShortestPath = new AllPairShortestPath();
        allPairShortestPath.floydWarshall(graph);

        System.out.printf("The shortest distance between %d and %d is %d%n",
                startStation, endStation, graph[startStation - 1][endStation - 1]);

    }
}

