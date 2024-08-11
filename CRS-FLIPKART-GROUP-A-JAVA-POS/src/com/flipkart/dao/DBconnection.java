package com.flipkart.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    private static final String URL = "jdbc:mysql://localhost:3306/CRS_POS_DB"; // Replace with your database name
    private static final String USER = "root"; // Replace with your MySQL username
    private static final String PASSWORD = "qwertyuiop"; // Replace with your MySQL password

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Ensure MySQL JDBC driver is loaded
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
