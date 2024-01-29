package com.solvd.navigator.util;

import com.solvd.navigator.model.BusStationStatus;
import com.solvd.navigator.model.Station;
import com.solvd.navigator.model.exceptions.NoBusContinuityException;
import com.solvd.navigator.service.StationService;

import java.util.*;

import static com.solvd.navigator.util.Utils.*;

public class BusPath {
    public final int V = new StationService().getStationsAmount();

    public List<BusStationStatus> findBusPath(int[][] dist, int startStation, int endStation) throws NoBusContinuityException {
        startStation -= 1;
        endStation -= 1;
        //Path matrix
        int[][] pathMatrix = new int[V][V];

        int[][] auxDist = copyArray(dist); // I have to make a copy, because otherwise the algorithm will modify the matrix from the calling method
        floydWarshallAlgorithm(auxDist, pathMatrix, AllPairShortestPath.INF);
        List<Integer> pathFromAtoB = pathFromAtoB(pathMatrix, startStation, endStation);


        List<Station> stations = getAllStations();
        // TODO: Replace the above line with the below line after the buses and bus_stations
        //       tables are created (don't forget to Modify the DAO classes
//        List<Station> stations = new StationService().getAllStations();

        List<BusStationStatus> path = new LinkedList<>();
        int initialValue = 1;

        int stationId = pathFromAtoB.getFirst();
        Station station = stations.get(stationId);
        List<Integer> buses = station.getBuses();
        if (isThereOnlyOneBusPassingThroughThisStation(station)) {
            int busId = buses.getFirst();
            path.add(new BusStationStatus(-1, busId, -1, stationId));
        } else {
            List<Integer> lastStationBuses = buses;
            station = stations.get(pathFromAtoB.get(1));
            buses = station.getBuses();
            boolean foundBusMatch = false;
            for (int bus : buses) {
                if (lastStationBuses.contains(bus)) {
                    int lastStationId = stationId;
                    stationId = pathFromAtoB.get(1);
                    path.add(new BusStationStatus(-1, bus, -1, lastStationId));
                    path.add(new BusStationStatus(-1, -1, bus, stationId));
                    foundBusMatch = true;
                    break;
                }
            }
            if (!foundBusMatch) {
                throwNoBusContinuityException(pathFromAtoB, 1);
            }
            initialValue = 2;
        }

        for (int i = initialValue; i < pathFromAtoB.size(); i++) {
            stationId = pathFromAtoB.get(i);
            station =  stations.get(stationId);
            buses = station.getBuses();
            if (isThereOnlyOneBusPassingThroughThisStation(station)) {
                int onlyOptionBusId = buses.getFirst();
                int lastStationId = pathFromAtoB.get(i-1);
                Station lastStation = stations.get(lastStationId);
                if (isThisTheBusThePassengerItsOn(onlyOptionBusId, path)) {
                    path.add(new BusStationStatus(-1,-1,onlyOptionBusId,stationId));
                } else if (isThisBusPresentOnLastStation(lastStation, onlyOptionBusId)) {
                    updateLastEntryWithTheSwitchInBuses(path, onlyOptionBusId);
                    path.add(new BusStationStatus(-1, -1, onlyOptionBusId, stationId));
                } else {
                    throwNoBusContinuityException(pathFromAtoB, i);
                }
            } else if (doesTheBusThePassengerItsOnPassThroughThisStation(station, path)) {
                path.add(new BusStationStatus(
                        -1,
                        -1,
                        path.getLast().getCurrentBusId(),
                        stationId
                ));
            } else {
                int lastStationId = pathFromAtoB.get(i-1);
                Station lastStation = stations.get(lastStationId);
                List<Integer> lastStationBuses = lastStation.getBuses();
                boolean foundBusMatch = false;
                for (int bus : buses) {
                    if (lastStationBuses.contains(bus)) {
                        updateLastEntryWithTheSwitchInBuses(path, bus);
                        path.add(new BusStationStatus(-1, -1, bus, stationId));
                        foundBusMatch = true;
                        break;
                    }
                }
                if (!foundBusMatch) {
                    throwNoBusContinuityException(pathFromAtoB, i);
                }
            }
        }
        return path;
    }

    private static void throwNoBusContinuityException(List<Integer> pathFromAtoB, int i) throws NoBusContinuityException {
        List<String> stationNames = new StationService().getAllStationsNames();
        throw new NoBusContinuityException(String.format(
                "There is no bus continuity from station %s to station %s",
                stationNames.get(pathFromAtoB.get(i -1)),
                stationNames.get(pathFromAtoB.get(i))
        ));
    }

    private static void updateLastEntryWithTheSwitchInBuses(List<BusStationStatus> path, int busId) {
        BusStationStatus lastBusStationStatus = path.getLast();
        lastBusStationStatus.setGetOffBusId(lastBusStationStatus.getCurrentBusId());
        lastBusStationStatus.setGetOnBusId(busId);
        lastBusStationStatus.setCurrentBusId(-1);
    }

    private static boolean isThisBusPresentOnLastStation(Station lastStation, int onlyOptionBus) {
        return lastStation.getBuses().contains(onlyOptionBus);
    }

    private static boolean isThisTheBusThePassengerItsOn(int onlyOptionBus, List<BusStationStatus> path) {
        return onlyOptionBus == path.getLast().getCurrentBusId();
    }

    private static boolean doesTheBusThePassengerItsOnPassThroughThisStation(Station station, List<BusStationStatus> path) {
        return station.getBuses().contains(path.getLast().getCurrentBusId());
    }

    private static boolean isThereOnlyOneBusPassingThroughThisStation(Station station) {
        return station.getBuses().size() == 1;
    }

    private List<Station> getAllStations() {
        List<Station> stations = new LinkedList<>();
        // To keep in mind Bus1=1, Bus2=2 and Bus=3
        stations.add(new Station(1, "Jackson Street 140", Arrays.asList(1)));
        stations.add(new Station(2, "Oak Street 243", Arrays.asList(1, 2, 3)));
        stations.add(new Station(3, "Park Street 9", Arrays.asList(1)));
        stations.add(new Station(4, "Main Street 332", Arrays.asList(1, 2, 3)));
        stations.add(new Station(5, "Sunset Street 920", Arrays.asList(3)));
        stations.add(new Station(6, "Dogwood Street 93", Arrays.asList(2, 3)));
        stations.add(new Station(7, "Green Street 204", Arrays.asList(3)));
        return stations;
    }
}
