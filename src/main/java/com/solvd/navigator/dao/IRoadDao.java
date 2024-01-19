package com.solvd.navigator.dao;

import com.solvd.navigator.model.Road;

import java.util.List;

public interface IRoadDao extends IBaseDao<Road> {

    Road getById(int id);

    List<Road> getAll();
}
