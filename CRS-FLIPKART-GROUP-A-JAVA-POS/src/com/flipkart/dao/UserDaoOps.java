package com.flipkart.dao;

import com.flipkart.bean.User;

import java.sql.*;

public class UserDaoOps {
    private Connection connect() {
        Connection conn = null;
        try {
            // Database connection details
            String url = "jdbc:mysql://localhost:3306/CRS_POS_DB"; // Replace with your database name
            String user = "root"; // Replace with your MySQL username
            String password = "Kunal@1912"; // Replace with your MySQL password

            // Establish the connection
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void getRolebyLogin(User user) {
        String sql = "SELECT role,user_id FROM User WHERE username = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getUserName());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user.setRole(rs.getString("role"));
                user.setUserId(rs.getInt("user_id"));
            } else {
                System.out.println("User not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    public Boolean checkCredentials(String username, String password) {
        Boolean result = false;
        String sql = "SELECT * FROM User WHERE username = ? AND password = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String username1 = rs.getString("username");
                String password1 = rs.getString("password");
                if (username.equals(username1) && password.equals(password1)) {
                    result = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    public Boolean updatePassword(String username, String newPassword) {
        Boolean result = false;
        String sql = "UPDATE User SET password = ? WHERE username = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newPassword);
            pstmt.setString(2, username);
            int affectedRows = pstmt.executeUpdate();

            // If at least one row was updated, the operation was successful
            if (affectedRows > 0) {
               result=true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
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
