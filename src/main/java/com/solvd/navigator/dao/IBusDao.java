package com.solvd.navigator.dao;

import com.solvd.navigator.model.Bus;
import com.solvd.navigator.model.Station;

import java.sql.Connection;
import java.util.List;

public interface IBusDao extends IBaseDao<Bus> {
    List<Station> getStationsByBusId(int busId, Connection conn);
}
