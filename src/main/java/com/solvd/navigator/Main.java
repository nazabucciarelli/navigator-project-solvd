package com.solvd.navigator;

import com.solvd.navigator.model.util.UserInterface;
import com.solvd.navigator.services.MatrixService;

import static com.solvd.navigator.model.util.AllPairShortestPath.INF;

public class Main {
    public static void main(String[] args) {
        int[][] graph = MatrixService.generateGraphMatrix();

        UserInterface ui = new UserInterface(graph);
        ui.start();
    }
}