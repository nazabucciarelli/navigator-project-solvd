package com.solvd.navigator.service;

import com.solvd.navigator.dao.IBusDao;
import com.solvd.navigator.dao.jdbc.BusDao;
import com.solvd.navigator.model.Bus;
import com.solvd.navigator.model.exceptions.ElementDoesNotExistException;

import java.util.List;

public class BusService {
    private final IBusDao busDao = new BusDao();

    /**
     * Returns the bus with the matching id. If there is not a match,
     * an ElementDoesNotExistException is thrown.
     *
     * @param id
     * @return Road object
     * @throws ElementDoesNotExistException
     */
    public Bus getById(int id) throws ElementDoesNotExistException {
        if (id == 0) {
            throw new RuntimeException("Parameter \"id\" have to be positive and not zero");
        }
        Bus bus = busDao.getById(id);
        if (bus.getId() == 0) {
            throw new ElementDoesNotExistException(String.format(
                    "The element with id: \"%d\" does not exist in the database", id
            ));
        }
        return bus;
    }

    /**
     * Returns a list with all the entries from the buses table
     *
     * @return List of buses
     */
    public List<Bus> getAllBuses() {
        List<Bus> buses = busDao.getAll();
        if (buses.isEmpty()) {
            throw new RuntimeException("There are no buses in the database");
        }
        return buses;
    }
}
