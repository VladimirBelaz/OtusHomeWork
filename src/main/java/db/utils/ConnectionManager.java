package db.utils;

import java.sql.*;

public class ConnectionManager {
    private final String connection_url = System.getenv("connection_url");
    private final String login = System.getenv("login");
    private final String password = System.getenv("password");

    private Connection connection;
    private Statement statement;

    private static ConnectionManager instance;

    public static ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    public ConnectionManager() {
        try {
            connection = DriverManager.getConnection(connection_url, login, password);
            statement = connection.createStatement();
        } catch (SQLException ex) {
            System.err.println("Ошибка подключения к БД: " + ex.getMessage());
        }
    }

    public ResultSet executeQuery(String query) {
        try {
            return statement.executeQuery(query);
        } catch (SQLException ex) {
            System.err.println("Ошибка запроса: " + ex.getMessage());
            return null;
        }
    }

    public int executeUpdate(String query) {
        try {
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            System.err.println("Ошибка обновления: " + ex.getMessage());
        }
        return 0;
    }

    public void close() {
        try {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException ex) {
            System.err.println("Ошибка закрытия: " + ex.getMessage());
        }
    }
}