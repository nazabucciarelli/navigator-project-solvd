package com.solvd.navigator;

import com.solvd.navigator.util.UserInterface;
import com.solvd.navigator.service.MatrixService;

public class Main {
    public static void main(String[] args) {
        int[][] graph = MatrixService.generateGraphMatrix();

        UserInterface ui = new UserInterface(graph);
        ui.start();
    }
}