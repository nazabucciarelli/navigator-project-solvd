package com.solvd.navigator.services;

import com.solvd.navigator.dao.IRoadDao;
import com.solvd.navigator.dao.jdbc.RoadDao;
import com.solvd.navigator.model.Road;
import com.solvd.navigator.model.exceptions.ElementDoesNotExistException;

import java.util.List;

public class RoadDaoService{
    public IRoadDao roadDao = new RoadDao();

    public Road getById(int id) throws ElementDoesNotExistException {
        if (id == 0) {
            throw new NullPointerException("Parameter \"id\" can't be 0");
        }
        Road road = roadDao.getById(id);
        if (road.getId() == 0) {
            throw new ElementDoesNotExistException(String.format(
                    "The element with id: \"%s\" does not exist in the database", id
            ));
        }
        return road;
    }


    public List<Road> getAll() {
        return getAll();
    }


    public void showAvailableRoads() {
        System.out.println(showAvailableRoads();

    }
}
