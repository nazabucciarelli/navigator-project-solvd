package com.solvd.navigator.dao;

import com.solvd.navigator.model.Station;

import java.util.List;

public interface IStationDao extends IBaseDao<Station> {

    /**
     * Returns the station with the matching name. If there is not a match,
     * a station with id=0 is returned.
     *
     * @param name of the station
     * @return Station object
     */
    Station getByName(String name);

    /**
     * Returns the station with the matching id. If there is not a match,
     * a station with id=0 is returned.
     *
     * @param id of the station
     * @return Station object
     */
    Station getById(int id);

    /**
     * Returns a list with all the entries from the stations table
     *
     * @return List of Station
     */
    List<Station> getAll();
}
