package com.solvd.navigator.dao.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static ConnectionPool instance;
    private static Properties p = new Properties();
    private static String url;
    private static String userName;
    private static String password;
    private static List<Connection> connectionPool;
    private static List<Connection> usedConnections = new ArrayList<>();
    private static final int INITIAL_POOL_SIZE = 5;

    private ConnectionPool() {
        connectionPool = new ArrayList<>(INITIAL_POOL_SIZE);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, userName, password);
            } catch (SQLException e) {
                LOGGER.error(e);
            }
            connectionPool.add(connection);
        }
    }

    public static ConnectionPool create() {
        if (instance == null)
            instance = new ConnectionPool();
        return instance;
    }

    static {
        try (FileInputStream f = new FileInputStream("src/main/resources/db.properties")) {
            p.load(f);
        } catch (IOException e) {
            LOGGER.info(e);
        }
        url = p.getProperty("db.url");
        userName = p.getProperty("db.username");
        password = p.getProperty("db.password");
    }

    synchronized public Connection getConnection() {
        if (connectionPool.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                LOGGER.error(e);
            }
        }
        Connection connection = connectionPool
                .removeLast();
        usedConnections.add(connection);
        return connection;
    }

    synchronized public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        notify();
        return usedConnections.remove(connection);
    }

    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }
}
