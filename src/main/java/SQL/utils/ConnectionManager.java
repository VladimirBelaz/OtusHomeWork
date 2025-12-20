package SQL.utils;

import java.sql.*;

public class ConnectionManager {
    private final String connection_url = System.getenv("connection_url");
    private final String login = System.getenv("login");
    private final String password = System.getenv("password");

    private Connection connection;
    private Statement statement;

    private static  ConnectionManager instance;
    public  static ConnectionManager getInstance() {
        if(instance == null) {
            instance = new ConnectionManager();
        }
        return  instance;
    }

    public ConnectionManager() {
        try {
            connection = DriverManager.getConnection(connection_url, login, password);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        try {
            statement = connection.createStatement();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public ResultSet executeQuery (String query) {
        try {
            return  statement.executeQuery(query);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void executeUpdate (String query) {
        try {
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void close () {
        try {
            connection.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        instance = null;
    }
}
