package com.solvd.navigator.services;

import com.solvd.navigator.dao.IRoadDao;
import com.solvd.navigator.dao.jdbc.RoadDao;
import com.solvd.navigator.model.Road;
import com.solvd.navigator.model.exceptions.ElementDoesNotExistException;

import java.util.List;

public class RoadService {
    private final IRoadDao roadDao = new RoadDao();

    /**
     * Returns the road with the matching id. If there is not a match,
     * an ElementDoesNotExistException is thrown.
     *
     * @param id
     * @return Road object
     * @throws ElementDoesNotExistException
     */
    public Road getById(int id) throws ElementDoesNotExistException {
        if (id == 0) {
            throw new RuntimeException("Parameter \"id\" have to be positive and not zero");
        }
        Road road = roadDao.getById(id);
        if (road.getId() == 0) {
            throw new ElementDoesNotExistException(String.format(
                    "The element with id: \"%d\" does not exist in the database", id
            ));
        }
        return road;
    }

    /**
     * Returns a list with all the entries from the roads table
     *
     * @return List of Road
     */
    public List<Road> getAllRoads() {
        return roadDao.getAll();
    }
}
