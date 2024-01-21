package com.solvd.navigator;

import com.solvd.navigator.model.util.UserInterface;

import static com.solvd.navigator.model.util.AllPairShortestPath.INF;

public class Main {
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

        UserInterface ui = new UserInterface(graph);
        ui.start();
    }
}