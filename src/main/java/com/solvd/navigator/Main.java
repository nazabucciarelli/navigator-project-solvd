package com.solvd.navigator;

import com.solvd.navigator.model.Road;
import com.solvd.navigator.model.Station;
import com.solvd.navigator.model.exceptions.ElementDoesNotExistException;
import com.solvd.navigator.services.RoadDaoService;
import com.solvd.navigator.services.StationDaoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        System.out.println("Hello world!");

    StationDaoService stationService = new StationDaoService();
    Station station = null;
        try {
        try {
            station = stationService.getByName("Irigoyen 220");
        } catch (ElementDoesNotExistException ex) {
            throw new RuntimeException(ex);
        }
        try {
            station = stationService.getById(station.getId());
        } catch (ElementDoesNotExistException ex) {
            throw new RuntimeException(ex);
        }
    } catch (ElementDoesNotExistException e) {
        LOGGER.error(e);
        // Do something to handle the exception
    }
    // continue with the code
        LOGGER.info(station);

    RoadDaoService roadDaoService = new RoadDaoService();
    Road road = null;
    try{
        try {
            road = roadDaoService.getById(road.getId());
        } catch (ElementDoesNotExistException ex) {
            throw new RuntimeException(ex);
        }
    }catch(ElementDoesNotExistException e){
        LOGGER.error(e);
        // Do something to handle the exception
    }
    // continue with the code
        LOGGER.info(road);

    String[][] graph = Service.prettifyMatrix(matrix);

        for(i = 0; i < graph.length; ++i) {
        for(int j = 0; j < graph[i].length; ++j) {
            System.out.print(graph[i][j] + " ");
        }

        System.out.println();
    }
    }
}
