package com.emi.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Database db = new Database();
    private static final String URL = "jdbc:mysql://localhost/employees";
    private static final String user = "EmiPersistence";
    private static final String password = "persistence";
    private Connection conn;

    public static Database instance() {
        return db;
    }

    private Database() {
    }

    public Connection getConnection() {
        return conn;
    }

    public void connect() throws SQLException {
        conn = DriverManager.getConnection(URL, user, password);
    }

    public void closeConnection() throws SQLException {
        conn.close();
    }
}
