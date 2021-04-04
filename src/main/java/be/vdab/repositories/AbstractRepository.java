package be.vdab.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract class AbstractRepository {
    private static final String URL = "jdbc:mysql://localhost/bieren";
    private static final String USER = "root";
    private static final String PASSWORD = "Dorado.7";

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
