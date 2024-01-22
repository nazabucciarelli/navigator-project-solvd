package com.solvd.navigator;

import com.solvd.navigator.model.PathContainer;
import com.solvd.navigator.util.AllPairShortestPath;

import java.util.ArrayList;
import java.util.List;

import static com.solvd.navigator.util.AllPairShortestPath.INF;

public class AlgorithmMain {
    public static void main(String[] args) {

        int[][] graph = {
                {   0,   3, INF, INF, INF, INF, INF },
                {   3,   0, INF,   8,   3, INF, INF },
                {  10, INF,   0, INF, INF, INF, INF },
                { INF, INF, INF,   0, INF,   6, INF },
                { INF, INF,   2,   4,   0, INF, INF },
                { INF, INF, INF,   6,   5,   0,  3},
                { INF, INF, INF, INF,   6, INF,  0},
        };

        AllPairShortestPath allPairShortestPath = new AllPairShortestPath();
        int start = 2 - 1;
        int end = 7 - 1;

        //Method Call
        PathContainer pathsWrapper = allPairShortestPath.floydWarshallWithPath(graph, start, end);

        //Print the shortest distance
        System.out.println("Shortest distance: " + pathsWrapper.getShortestDistance());

        //Print path
        List<Integer> pathFromAtoB = pathsWrapper.getPathFromAtoB();
        System.out.printf("Path from %d to %d = %s%n", start, end, pathFromAtoB);

        //Example to print path with station name
        List<String> stations = new ArrayList<>(List.of("Jackson Street 140", "Oak Street 243", "Park Street 9", "Dogwood Street 93", "Main Street 332", "Green Street 204", "Sunset Street 920"));

        pathFromAtoB.stream()
                .map(stations::get)
                .forEach(s -> System.out.print(" -> " + s ));



    }
}

