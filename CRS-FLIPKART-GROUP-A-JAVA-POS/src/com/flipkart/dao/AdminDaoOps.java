package com.flipkart.dao;

import com.flipkart.bean.Professor;
import com.flipkart.bean.User;

import java.sql.*;

public class AdminDaoOps {

    private Connection connect() {
        Connection conn = null;
        try {
            // Database connection details
            String url = "jdbc:mysql://localhost:3306/CRS_Db"; // Replace with your database name
            String user = "root"; // Replace with your MySQL username
            String password = "Kunal@1912"; // Replace with your MySQL password

            // Establish the connection
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public Integer addProfessor(String username, String professorName, String role, String password, String department, String designation) {

        String usersql = "INSERT INTO user (username, name, role, password) VALUES (?, ?, ?, ?)";
        String profsql = "INSERT INTO professor (professor_id, department, designation) VALUES (?, ?, ?)";
        int pId = 0;
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(usersql, Statement.RETURN_GENERATED_KEYS);) {
            pstmt.setString(1, username);
            pstmt.setString(2, professorName);
            pstmt.setString(3, role);
            pstmt.setString(4, password);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        pId = generatedKeys.getInt(1);

                        // Insert into professor table
                        try (PreparedStatement insertProfessorStatement = conn.prepareStatement(profsql)) {
                            insertProfessorStatement.setInt(1, pId);
                            insertProfessorStatement.setString(2, department);
                            insertProfessorStatement.setString(3, designation);

                            int professorRows = insertProfessorStatement.executeUpdate();
                            if (professorRows > 0) {
                                System.out.println("Professor inserted successfully");
                            } else {
                                System.out.println("Professor insertion failed");
                                return -1;
                            }
                        }
                        return pId;
                    } else {
                        System.out.println("Professor insertion failed, no ID obtained");
                        return -1;
                    }
                }
            } else {
                System.out.println("Professor insertion failed");
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pId;
    }

    public void printUnapprovedStudents() {
        String sql = "SELECT student_id FROM Student WHERE isApproved = 0";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            boolean foundUnapproved = false;

            while (rs.next()) {
                foundUnapproved = true;
                System.out.println(rs.getInt("student_id")); // Use column name instead of index for clarity
            }

            if (!foundUnapproved) {
                System.out.println("No Unapproved Students Found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void approveOneStudent(Integer student_id) {
        boolean result = false;
        String sql = "UPDATE Student SET isApproved = 1 WHERE student_id = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student_id.toString());
            int affectedRows = pstmt.executeUpdate();

            // If at least one row was updated, the operation was successful
            if (affectedRows > 0) {
                System.out.println("StudentID: " + student_id + " Approved Successfully");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
