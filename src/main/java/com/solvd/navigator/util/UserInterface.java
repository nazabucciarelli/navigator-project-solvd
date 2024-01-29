package com.solvd.navigator.util;

import com.solvd.navigator.model.Bus;
import com.solvd.navigator.model.PathContainer;
import com.solvd.navigator.model.Station;
import com.solvd.navigator.model.Transport;
import com.solvd.navigator.model.exceptions.NoSecondPathException;
import com.solvd.navigator.service.BusService;
import com.solvd.navigator.service.StationService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private static final Scanner input = new Scanner(System.in);
    private final List<String> stations;
    private final int[][] graph;

    private final AllPairShortestPath allPairShortestPath;
    private final BusService busService;

    public UserInterface(int[][] graph) {
        this.busService = new BusService();
        this.stations = new StationService().getAllStationsNames();
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
            getStationsAndBuses(startStation, endStation);
        }
    }

    public void getShortestPath(int startStation, int endStation) {
        PathContainer shortestPath = allPairShortestPath.floydWarshallWithShortestPath(this.graph, startStation, endStation);
        List<String> path = shortestPath.getPathFromAtoB()
                .stream()
                .map(this.stations::get)
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
                .map(this.stations::get)
                .toList();
        System.out.printf("The second shortest distance between %s station and %s station is %d%n and you will have to" +
                        " pass through the stations " + path, stations.get(startStation - 1),
                stations.get(endStation - 1), shortestPath.getShortestDistance());
        System.out.println(" ");
    }

    public void getStationsAndBuses(int startStation, int endStation) {
        PathContainer shortestPath = allPairShortestPath.floydWarshallWithShortestPath(this.graph, startStation, endStation);
        List<String> path = shortestPath.getPathFromAtoB()
                .stream()
                .map(this.stations::get)
                .toList();
        navigateByBus(path);
    }

    private void navigateByBus(List<String> path) {
        List<Bus> buses = busService.getAllBuses();
        List<Bus> busesToTake = new ArrayList<>();
        List<String> pathAux = new ArrayList<>(path);

        String startStation = pathAux.getFirst();
        String endStation = pathAux.getLast();

        for (Bus bus : buses) {
            boolean busPassThroughStartStation = false;
            boolean busPassThroughEndStation = false;
            for (Station station : bus.getStations()) {
                if (station.getName().equalsIgnoreCase(startStation)) {
                    busPassThroughStartStation = true;
                }
                if (station.getName().equalsIgnoreCase(endStation)) {
                    busPassThroughEndStation = true;
                }
            }
            if (busPassThroughStartStation && busPassThroughEndStation) {
                System.out.println("You have to take the bus " + bus.getName() +
                        " to go from the station " + startStation + " to the " +
                        "station " + endStation);
                return;
            }
        }
        while (!pathAux.isEmpty()) {
            for (Bus bus : buses) {
                for (Station station : bus.getStations()) {
                    if (pathAux.isEmpty()) {
                        break;
                    }
                    if (pathAux.getFirst().equalsIgnoreCase(station.getName())) {
                        if (!busesToTake.contains(bus)) {
                            busesToTake.add(bus);
                        }
                        pathAux.removeFirst();
                    }
                }

            }
        }
        List<String> stationsToChangeBus = getStationsToChangeBus(path, busesToTake);
        System.out.println("To go from station " + startStation
                + " to station " + endStation + " you should "
                + "take the buses " + busesToTake + " and change of bus in the" +
                " stations " + stationsToChangeBus);

    }

    private static List<String> getStationsToChangeBus(List<String> path, List<Bus> busesToTake) {
        List<String> stationsToChangeBus = new ArrayList<>();
        for (int i = 0; i <= busesToTake.size(); i++) {
            if (i == busesToTake.size() - 1) {
                break;
            }
            Bus bus = busesToTake.get(i);
            List<Station> busStations = bus.getStations();
            Bus nextBus = busesToTake.get(i + 1);
            List<Station> nextBusStations = nextBus.getStations();

            for (String stationName : path) {
                boolean busPassThroughStation = false;
                boolean nextBusPassThroughStation = false;
                for (Station station : busStations) {
                    if (stationName.equalsIgnoreCase(station.getName())) {
                        busPassThroughStation = true;
                        break;
                    }
                }
                for (Station station : nextBusStations) {
                    if (stationName.equalsIgnoreCase(station.getName())) {
                        nextBusPassThroughStation = true;
                        break;
                    }
                }
                if (busPassThroughStation && nextBusPassThroughStation) {
                    stationsToChangeBus.add(stationName);
                    break;
                }
            }

        }
        return stationsToChangeBus;
    }

    private void showAvailableStations() {
        System.out.println("The available stations are: ");
        for (String station : this.stations) {
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
