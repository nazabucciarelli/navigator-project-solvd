package com.solvd.navigator.dao;

import com.solvd.navigator.model.Road;

import java.util.List;

public interface IRoadDao extends IBaseDao<Road> {

    /**
     * Returns the road with the matching id. If there is not a match,
     * a road with id=0 is returned.
     *
     * @param id of the road
     * @return Road object
     */
    Road getById(int id);

    /**
     * Returns a list with all the entries from the roads table
     *
     * @return List of Road
     */
    List<Road> getAll();
}
