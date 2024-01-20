package com.solvd.navigator.dao.jdbc;

import com.solvd.navigator.dao.IRoadDao;
import com.solvd.navigator.model.Road;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoadDao implements IRoadDao {
    ConnectionPool connectionPool = ConnectionPool.create();
    private static final Logger LOGGER = LogManager.getLogger(RoadDao.class);

    /**
     * Returns the road with the matching id. If there is not a match,
     * a station with id=0 is returned.
     *
     * @param id of the road
     * @return Road object
     */
    @Override
    public Road getById(int id) {
        Road road = new Road();
        Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM roads WHERE id = ?"
            );
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                road.setId(resultSet.getInt("id"));
                road.setDistance(resultSet.getFloat("distance"));
                road.setFromStationId(resultSet.getInt("from_stations_id"));
                road.setToStationId(resultSet.getInt("to_stations_id"));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
            Utils.closeAll(resultSet, preparedStatement);
        }
        return road;
    }

    /**
     * Returns a list with all the entries from the roads table
     *
     * @return List of Roads
     */
    @Override
    public List<Road> getAll() {
        List<Road> list = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM roads"
            );
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Road road = new Road();
                road.setId(resultSet.getInt("id"));
                road.setDistance(resultSet.getFloat("distance"));
                road.setFromStationId(resultSet.getInt("from_stations_id"));
                road.setToStationId(resultSet.getInt("to_stations_id"));
                list.add(road);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
            Utils.closeAll(resultSet, preparedStatement);
        }
        return list;
    }
}
