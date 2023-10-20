package fr.maefy.db;

import fr.maefy.api.API;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DBConnection {
    String address;
    int port;
    String databaseName;
    String username;
    String password;
    Connection connection;

    public DBConnection(String address, int port , String databaseName ,String username ,String password) {

        this.address = address;
        this.port = port;
        this.databaseName = databaseName;
        this.username = username;
        this.password = password;

        String jdbcURL = "jdbc:mariadb://"
                + address
                + ":"
                + port
                +"/"
                +databaseName;

        try {
            connection = DriverManager.getConnection(jdbcURL,username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Connection getConnection() {
        return connection;
    }
}
