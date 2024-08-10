package com.flipkart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class studentDaoOps {
    private Connection connect() {
        Connection conn = null;
        try {
            // Database connection details
            String url = "jdbc:mysql://localhost:3306/db1"; // Replace with your database name
            String user = "root"; // Replace with your MySQL username
            String password = "Thanos8#yuoto"; // Replace with your MySQL password

            // Establish the connection
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public int registerNewStudent(String username, String password, String role, String name, String department) {
        String userSql = "INSERT INTO User (username, password, name, role) VALUES (?, ?, ?, ?)";
        String studentSql = "INSERT INTO Student (student_id, department) VALUES (?, ?)"; // Adjust if necessary
        int sId = 0;
        try (Connection conn = this.connect();
             PreparedStatement userPstmt = conn.prepareStatement(userSql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Set parameters for the User table insertion
            userPstmt.setString(1, username);
            userPstmt.setString(2, password);
            userPstmt.setString(3, name);
            userPstmt.setString(4, role);


            // Execute the User insertion
            int affectedRows = userPstmt.executeUpdate();

            if (affectedRows > 0) {
                // Retrieve the generated key (sId)
                try (ResultSet generatedKeys = userPstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        sId = generatedKeys.getInt(1); // Assuming sId is an INT

                        // Insert into the Student table
                        try (PreparedStatement studentPstmt = conn.prepareStatement(studentSql)) {
                            studentPstmt.setInt(1, sId);
                            studentPstmt.setString(2, department);

                            int studentAffectedRows = studentPstmt.executeUpdate();
                            if (studentAffectedRows > 0) {
                                System.out.println("Student record added successfully.");
                            } else {
                                System.out.println("Student record insertion failed.");
                                return -1;
                            }
                        }

                        return sId; // Return the generated sId
                    } else {
                        System.out.println("User insertion failed, no ID obtained.");
                        return -1; // Or throw an exception if preferred
                    }
                }
            } else {
                System.out.println("User insertion failed.");
                return -1; // Or throw an exception if preferred
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    return sId;
    }

}
