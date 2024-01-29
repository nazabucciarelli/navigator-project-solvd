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
        List<Station> stations = new StationService().getAllStations();

        List<BusStationStatus> path = new LinkedList<>();
        int initialValue = 1;

        int stationId = pathFromAtoB.getFirst();
        Station station = stations.get(stationId);
        List<Integer> buses = station.getBusesId();
        if (isThereOnlyOneBusPassingThroughThisStation(station)) {
            int busId = buses.getFirst();
            path.add(new BusStationStatus(-1, busId, -1, stationId));
        } else {
            List<Integer> lastStationBuses = buses;
            station = stations.get(pathFromAtoB.get(1));
            buses = station.getBusesId();
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
            buses = station.getBusesId();
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
                List<Integer> lastStationBuses = lastStation.getBusesId();
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
        return lastStation.getBusesId().contains(onlyOptionBus);
    }

    private static boolean isThisTheBusThePassengerItsOn(int onlyOptionBus, List<BusStationStatus> path) {
        return onlyOptionBus == path.getLast().getCurrentBusId();
    }

    private static boolean doesTheBusThePassengerItsOnPassThroughThisStation(Station station, List<BusStationStatus> path) {
        return station.getBusesId().contains(path.getLast().getCurrentBusId());
    }

    private static boolean isThereOnlyOneBusPassingThroughThisStation(Station station) {
        return station.getBusesId().size() == 1;
    }
}
