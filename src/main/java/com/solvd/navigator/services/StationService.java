package com.solvd.navigator.services;

import com.solvd.navigator.dao.IStationDao;
import com.solvd.navigator.dao.jdbc.StationDao;
import com.solvd.navigator.model.Station;
import com.solvd.navigator.model.exceptions.ElementDoesNotExistException;

import java.util.List;

public class StationDaoService{
    private IStationDao stationDao = new StationDao();

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


    public Station getById(int id) throws ElementDoesNotExistException {
        if (id == 0) {
            throw new NullPointerException("Parameter \"id\" can't be 0");
        }
        Station station = stationDao.getById(id);
        if (station.getId() == 0) {
            throw new ElementDoesNotExistException(String.format(
                    "The element with id: \"%s\" does not exist in the database", id
            ));
        }
        return station;
    }



    public List<Station> getAll() {
        return getAll();
    }

    public boolean showAvailableStations() {
        System.out.println(showAvailableStations());
        return false;
    }
}
