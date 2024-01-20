package com.solvd.navigator.dao;

import java.util.List;

public interface IBaseDao<T> {

    T getById(int id);

    List<T> getAll();
}