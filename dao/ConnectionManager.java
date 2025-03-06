package carsharing.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String JDBC_DRIVER = "org.h2.Driver";

    private static Connection connection;

    public static Connection initConnection(String url) throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        connection = DriverManager.getConnection(url);
        connection.setAutoCommit(true);
        return connection;
    }


    public static Connection getConnection() {
        return connection;
    }
}