package com.solvd.navigator.dao.jdbc;

import com.solvd.navigator.dao.IStationDao;
import com.solvd.navigator.model.Station;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StationDao implements IStationDao {

    ConnectionPool connectionPool = ConnectionPool.create();
    private static final Logger LOGGER = LogManager.getLogger(StationDao.class);

    /**
     * Returns the station with the matching name. If there is not a match,
     * a station with id=0 is returned.
     *
     * @param name of the station
     * @return Station object
     */
    @Override
    public Station getByName(String name) {
        Station station = new Station();
        Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM stations WHERE name = ?"
            );
            preparedStatement.setString(1, name);
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                station.setId(resultSet.getInt("id"));
                station.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
            Utils.closeAll(resultSet, preparedStatement);
        }
        return station;
    }

    /**
     * Returns the station with the matching id. If there is not a match,
     * a station with id=0 is returned.
     *
     * @param id of the station
     * @return Station object
     */
    @Override
    public Station getById(int id) {
        Station station = new Station();
        Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM stations WHERE id = ?"
            );
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                station.setId(resultSet.getInt("id"));
                station.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
            Utils.closeAll(resultSet, preparedStatement);
        }
        return station;
    }

    /**
     * Returns a list with all the entries from the stations table
     *
     * @return List of Station
     */
    @Override
    public List<Station> getAll() {
        List<Station> list = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM stations"
            );
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Station station = new Station();
                int stationId = resultSet.getInt("id");
                station.setId(stationId);
                station.setName(resultSet.getString("name"));
                station.setBusesId(getListOfBusesId(stationId, connection));
                list.add(station);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
            Utils.closeAll(resultSet, preparedStatement);
        }
        return list;
    }

    private List<Integer> getListOfBusesId(int stationId, Connection connection) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        List<Integer> busesId = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT buses_id FROM bus_stations WHERE stations_id = ?");
            preparedStatement.setInt(1, stationId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                busesId.add(resultSet.getInt("buses_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return busesId;
    }
}
