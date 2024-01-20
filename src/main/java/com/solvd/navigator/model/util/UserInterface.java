package com.solvd.navigator.model.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private static final Scanner input = new Scanner(System.in);
    private List<String> stations;

    private AllPairShortestPath shortestPath;

    public UserInterface() {
        this.stations = new ArrayList<>(List.of("Jackson Street 140", "Oak Street 243", "Park Street 9", "Dogwood Street 93", "Main Street 332", "Green Street 204", "Sunset Street 920"));
        this.shortestPath = new AllPairShortestPath();
    }

    public void start() {
        System.out.println("Welcome to Navigator App");
        System.out.println("Available Stations: ");
        showAvailableStations();
    }

    public void calculateShortestDistance(int[][] graph) {
        int startStation = userOption("Start");
        int endStation = userOption("End");
        input.close();

        System.out.println("The start station is " + startStation);
        System.out.println("The end station is " + endStation);

        int[][] shortestGraph = shortestPath.floydWarshall(graph, startStation, endStation);

        System.out.printf("The shortest distance between %d and %d is %d%n",
                startStation, endStation, shortestGraph[startStation - 1][endStation - 1]);

        shortestPath.printSolution(shortestGraph);
    }

    private void showAvailableStations() {
        for (String station : stations) {
            System.out.println(station);
        }
    }

    private int userOption(String stationType) {
        String option;
        do {
            System.out.println("Enter the name of your " + stationType + " Station: ");
            option = input.nextLine();

            if (!stations.contains(option)) {
                System.out.println("Please enter a valid station name.");
            }

        } while(!stations.contains(option));

        for (String station : stations) {
            if (option.equalsIgnoreCase(station)) {
                return stations.indexOf(station) + 1;
            }
        }

        return 0;
    }
}
