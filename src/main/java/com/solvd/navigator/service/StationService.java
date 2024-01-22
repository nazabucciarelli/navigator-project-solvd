package com.solvd.navigator.service;

import com.solvd.navigator.dao.IStationDao;
import com.solvd.navigator.dao.jdbc.StationDao;
import com.solvd.navigator.model.Station;
import com.solvd.navigator.model.exceptions.ElementDoesNotExistException;

import java.util.List;

public class StationService{
    private final IStationDao stationDao = new StationDao();

    /**
     * Returns the station with the name supplied. If the station does not
     * exist in the database, an exception is thrown.
     * @param name
     * @return
     * @throws ElementDoesNotExistException
     */
    public Station getByName(String name) throws ElementDoesNotExistException {
        if (name == null) {
            throw new NullPointerException("Parameter \"name\" can't be null");
        }
        if (name.isEmpty()) {
            throw new RuntimeException("Parameter \"name\" can't be empty");
        }
        Station station = stationDao.getByName(name);
        if (station.getId() == 0) {
            throw new ElementDoesNotExistException(String.format(
                    "The element with name: \"%s\" does not exist in the database", name
            ));
        }
        return station;
    }

    /**
     * Returns the station with the name supplied. If the station does not
     * exist in the database, an exception is thrown.
     * @param id
     * @return Station object
     * @throws ElementDoesNotExistException
     */
    public Station getById(int id) throws ElementDoesNotExistException {
        if (id == 0) {
            throw new RuntimeException("Parameter \"id\" have to be positive and not zero");
        }
        Station station = stationDao.getById(id);
        if (station.getId() == 0) {
            throw new ElementDoesNotExistException(String.format(
                    "The element with id: \"%d\" does not exist in the database", id
            ));
        }
        return station;
    }

    /**
     * Returns a list with all the stations in the database
     *
     * @return List of Station
     */
    public List<Station> getAllStations() {
        return stationDao.getAll();
    }

    public List<String> getAllStationsNames() {
        List<Station> stationList = getAllStations();

        return stationList.stream()
                .map(Station::getName)
                .toList();
    }

    public int getStationsAmount() {
        return getAllStations().size();
    }

    /**
     * Checks if stations exist in the database
     * @param name
     * @return true if element exist
     * @throws RuntimeException
     */
    public boolean stationExist(String name) throws RuntimeException {
        if (name == null) {
            throw new NullPointerException("Parameter \"name\" can't be null");
        }
        if (name.isEmpty()) {
            throw new RuntimeException("Parameter \"name\" can't be empty");
        }
        Station station = stationDao.getByName(name);
        return station.getId() != 0;
    }
}
