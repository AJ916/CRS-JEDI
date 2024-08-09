package com.flipkart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoOps {
    private Connection connect() {
        Connection conn = null;
        try {
            // Database connection details
            String url = "jdbc:mysql://localhost:3306/db1"; // Replace with your database name
            String user = "root"; // Replace with your MySQL username
            String password = "Jaatraaj@700"; // Replace with your MySQL password

            // Establish the connection
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public String getRolebyLogin(String username) {
        String role = null;
        String sql = "SELECT role FROM User WHERE username = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                role = rs.getString("role");
            } else {
                System.out.println("User not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return role;
    }
    public boolean isApproved(String username) {
        boolean isapp = false;
       // String role = null;
      //  String sql = "SELECT isApproved FROM student WHERE username = ?";
        String  sql = "SELECT s.isApproved\n" +
                "FROM Student s\n" +
                "JOIN User u ON s.student_id = u.user_id\n" +
                "WHERE u.username = ?;\n";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                isapp = rs.getBoolean("isApproved");
            } else {
                System.out.println("User not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isapp;
    }


}
