package com.solvd.navigator.model.util;

import com.solvd.navigator.model.Transport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private static final Scanner input = new Scanner(System.in);
    private List<String> stations;
    private int[][] graph;

    private AllPairShortestPath allPairShortestPath;

    public UserInterface(int[][] graph) {
        this.stations = new ArrayList<>(List.of("Jackson Street 140", "Oak Street 243", "Park Street 9", "Dogwood Street 93", "Main Street 332", "Green Street 204", "Sunset Street 920"));
        this.allPairShortestPath = new AllPairShortestPath();
        this.graph = graph;
    }

    public void start() {
        System.out.println("Welcome to Navigator App");
        System.out.println("Select the mean of transport you are using");
        System.out.println("  - CAR");
        System.out.println("  - BUS");
        String option = input.nextLine();
        List<String> transportNames = Arrays.stream(Transport.values())
                .map(Transport::getName)
                .toList();
        while (!transportNames.contains(option.toLowerCase())) {
            System.out.println("Please, select a valid option: Car or Bus");
            option = input.nextLine();
        }
        navigation(Transport.valueOf(option.toUpperCase()));
    }

    public void navigation(Transport transport) {
        System.out.println("You selected navigation by " + transport.getName());
        showAvailableStations();
        requestStations(transport);
    }

    public void requestStations(Transport transport) {
        int startStation = userOption("start");
        int endStation = userOption("end");
        input.close();
        if (transport == Transport.CAR) {
            getShortestPath(startStation, endStation);
        } else {
            getPathAndBuses(startStation, endStation);
        }
    }

    public void getShortestPath(int startStation, int endStation) {
        int[][] shortestGraph = allPairShortestPath.floydWarshall(this.graph, startStation, endStation);
        System.out.printf("The shortest distance between %s station and %s station is %d%n",
                stations.get(startStation - 1), stations.get(endStation - 1),
                shortestGraph[startStation - 1][endStation - 1]);
    }

    public void getPathAndBuses(int startStation, int endStation) {
        // TO - DO
    }

    private void showAvailableStations() {
        System.out.println("The available stations are: ");
        for (String station : stations) {
            System.out.println(" - " + station);
        }
    }

    private int userOption(String stationType) {
        String option;
        List<String> stationsLowercase = stations.stream()
                .map(String::toLowerCase)
                .toList();
        do {
            System.out.println("Enter the name of your " + stationType + " Station: ");
            option = input.nextLine();
            if (!stationsLowercase.contains(option.toLowerCase())) {
                System.out.println("Please enter a valid station name.");
            }
        } while (!stationsLowercase.contains(option.toLowerCase()));

        for (String station : stations) {
            if (option.equalsIgnoreCase(station)) {
                return stations.indexOf(station) + 1;
            }
        }
        return 0;
    }
}
