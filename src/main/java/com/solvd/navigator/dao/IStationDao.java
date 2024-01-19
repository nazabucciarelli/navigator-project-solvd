package com.solvd.navigator.dao;

import com.solvd.navigator.model.Station;

import java.util.List;

public interface IStationDao extends IBaseDao<Station> {

    Station getById(int id);

    List<Station> getAll();
}
