package com.flipkart.dao;

import com.flipkart.bean.Professor;
import com.flipkart.bean.User;

import java.sql.*;

public class AdminDaoOps {

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

    public void addCourse(String course_id, String course_name, Boolean isOffered) {
        String userSql = "INSERT INTO Course (course_id, course_name, professor_id,total_seats,available_seats,is_offered) VALUES (?, ?, null,10,10,?)";

        try (Connection conn = this.connect();
             PreparedStatement userPstmt = conn.prepareStatement(userSql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Set parameters for the User table insertion
            userPstmt.setString(1, course_id);
            userPstmt.setString(2, course_name);
            userPstmt.setBoolean(3, isOffered);


            // Execute the User insertion
            int affectedRows = userPstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Course added succesfully\n" + "Course_ID: " + course_id);

            } else {
                System.out.println("Course insertion failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void showAllProfs() {
        String sql = "SELECT professor_id FROM Professor";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {


            while (rs.next()) {
                System.out.println(rs.getInt("professor_id")); // Use column name instead of index for clarity
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAllCourses() {
        String sql = "SELECT course_id FROM Course";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {


            while (rs.next()) {
                System.out.println(rs.getString("course_id")); // Use column name instead of index for clarity
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeProf(Integer instructor_id){
        String sql = "DELETE FROM Professor WHERE professor_id = ?";

        try (Connection conn = this.connect();
             PreparedStatement userPstmt = conn.prepareStatement(sql)) {

            // Set parameters for the User table insertion
            userPstmt.setInt(1, instructor_id);



            // Execute the User insertion
            int affectedRows = userPstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Professor with ID:"+instructor_id+" Removed Successfully");

            } else {
                System.out.println("Prof not found ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeCourse(String course_id){
        String sql = "DELETE FROM Course WHERE course_id = ?";

        try (Connection conn = this.connect();
             PreparedStatement userPstmt = conn.prepareStatement(sql)) {

            // Set parameters for the User table insertion
            userPstmt.setString(1, course_id);



            // Execute the User insertion
            int affectedRows = userPstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Course with ID:"+course_id+" Removed Successfully");

            } else {
                System.out.println("Course ID incorrect not found ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewApprovedStudents() {
        String sql = "SELECT * FROM Student WHERE isApproved = true";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {


            while (rs.next()) {
                System.out.println(rs.getString("student_id")); // Use column name instead of index for clarity
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}