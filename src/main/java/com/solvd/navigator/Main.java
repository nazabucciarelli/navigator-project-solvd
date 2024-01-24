package com.solvd.navigator;

import com.solvd.navigator.service.MatrixService;
import com.solvd.navigator.util.UserInterface;

public class Main {
    public static void main(String[] args) {
        int[][] graph = MatrixService.generateGraphMatrix();
        UserInterface ui = new UserInterface(graph);
        ui.start();
    }
}