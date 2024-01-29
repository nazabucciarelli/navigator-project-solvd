package com.solvd.navigator.dao.jdbc;

import com.solvd.navigator.dao.IBusDao;
import com.solvd.navigator.dao.IStationDao;
import com.solvd.navigator.model.Bus;
import com.solvd.navigator.model.Station;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusDao implements IBusDao {
    ConnectionPool connectionPool = ConnectionPool.create();
    private static final Logger LOGGER = LogManager.getLogger(BusDao.class);

    /**
     * Returns the bus with the matching id. If there is not a match,
     * a bus with id=0 is returned.
     *
     * @param id of the road
     * @return Road object
     */
    @Override
    public Bus getById(int id) {
        Bus bus = new Bus();
        Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM buses WHERE id = ?"
            );
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                int busId = resultSet.getInt("id");
                bus.setId(busId);
                bus.setName(resultSet.getString("name"));
                bus.setStations(getStationsByBusId(busId,connection));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
            Utils.closeAll(resultSet, preparedStatement);
        }
        return bus;
    }

    /**
     * Returns a list with all the entries from the buses table
     *
     * @return List of Bus
     */
    @Override
    public List<Bus> getAll() {
        List<Bus> list = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM buses"
            );
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Bus bus = new Bus();
                int busId = resultSet.getInt("id");
                bus.setId(busId);
                bus.setName(resultSet.getString("name"));
                bus.setStations(getStationsByBusId(busId, connection));
                list.add(bus);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
            Utils.closeAll(resultSet, preparedStatement);
        }
        return list;
    }

    /**
     * Returns the stations that a bus goes through. If there are no stations
     * for a bus, an empty list is returned.
     *
     * @param id of the bus
     * @param conn receives a Connection
     * @return List of Station
     */
    @Override
    public List<Station> getStationsByBusId(int busId, Connection conn) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        List<Station> stations = new ArrayList<>();
        IStationDao stationDao = new StationDao();
        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM bus_stations WHERE buses_id = ?");
            preparedStatement.setInt(1, busId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int stationId = resultSet.getInt("stations_id");
                stations.add(stationDao.getById(stationId));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return stations;
    }
}
