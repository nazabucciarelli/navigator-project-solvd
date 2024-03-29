package com.solvd.navigator.util;

import com.solvd.navigator.model.PathContainer;
import com.solvd.navigator.model.Transport;
import com.solvd.navigator.model.exceptions.NoBusContinuityException;
import com.solvd.navigator.model.exceptions.NoSecondPathException;
import com.solvd.navigator.service.StationService;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private static final Scanner input = new Scanner(System.in);
    private List<String> stations;
    private final StationService stationService;
    private final int[][] graph;

    private final AllPairShortestPath allPairShortestPath;

    public UserInterface(int[][] graph) {
        this.stations = new ArrayList<>();
        this.stationService = new StationService();
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
            getSecondShortestPath(startStation, endStation);
        } else {
            getPathAndBuses(startStation, endStation);
        }
    }

    public void getShortestPath(int startStation, int endStation) {
        PathContainer shortestPath = allPairShortestPath.floydWarshallWithShortestPath(this.graph, startStation, endStation);
        List<String> path = shortestPath.getPathFromAtoB()
                .stream()
                .map(integer -> this.stations.get(integer))
                .toList();
        System.out.printf("The shortest distance between %s station and %s station is %d%n and you will have to" +
                        " pass through the stations " + path, stations.get(startStation - 1),
                stations.get(endStation - 1), shortestPath.getShortestDistance());
        System.out.println(" ");
    }

    private void getSecondShortestPath(int startStation, int endStation) {
        PathContainer shortestPath = null;
        try {
            shortestPath = allPairShortestPath.floydWarshallWithSecondShortestPath(this.graph, startStation, endStation);
        } catch (NoSecondPathException e) {
            System.out.println(e.getMessage());
            return;
        }
        List<String> path = shortestPath.getPathFromAtoB()
                .stream()
                .map(integer -> this.stations.get(integer))
                .toList();
        System.out.printf("The second shortest distance between %s station and %s station is %d%n and you will have to" +
                        " pass through the stations " + path, stations.get(startStation - 1),
                stations.get(endStation - 1), shortestPath.getShortestDistance());
        System.out.println(" ");
    }

    public void getPathAndBuses(int startStation, int endStation) {
        try {
            String indications = new BusPath().getIndicationsByBus(this.graph, startStation, endStation);
            System.out.printf(
                    "To get from %s to %s by bus, you must follow the next indications:%n",
                    stations.get(startStation - 1),
                    stations.get(endStation - 1)
            );
            System.out.println(indications);
        } catch (NoBusContinuityException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showAvailableStations() {
        this.stations = stationService.getAllStationsNames();
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
