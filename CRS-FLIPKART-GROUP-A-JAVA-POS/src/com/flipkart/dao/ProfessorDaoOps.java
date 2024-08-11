package com.flipkart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class ProfessorDaoOps {
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

    public void showAvailableCourses() {
        String sql = "SELECT * FROM course WHERE professor_id IS NULL AND is_offered = true";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            // Table header with borders
            System.out.println("+-----------+--------------------------------+");
            System.out.printf("| %-9s | %-30s |%n", "Course ID", "Course Name");
            System.out.println("+-----------+--------------------------------+");

            // Print each row of the result set with borders
            while (rs.next()) {
                String courseId = rs.getString("course_id");
                String courseName = rs.getString("course_name");

                // Print course_id and course_name in table format
                System.out.printf("| %-9s | %-30s |%n", courseId, courseName);
            }

            // Table footer
            System.out.println("+-----------+--------------------------------+");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void courseSelection(Integer profId) {
        showAvailableCourses();

        // Prompt the professor to select a course by entering the Course ID
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Course ID to select the course to teach: ");
        String selectedCourseId = scanner.nextLine(); // Read as String

        // Update the course table to assign the professor to the selected course
        String updateSql = "UPDATE course SET professor_id = ? WHERE course_id = ? AND professor_id IS NULL AND is_offered = true";

        try (Connection conn = this.connect();
             PreparedStatement updatePstmt = conn.prepareStatement(updateSql)) {

            // Set the professor_id and course_id in the prepared statement
            updatePstmt.setInt(1, profId);
            updatePstmt.setString(2, selectedCourseId); // Use String for course_id

            // Execute the update
            int rowsAffected = updatePstmt.executeUpdate();

            // Check if the update was successful
            if (rowsAffected > 0) {
                System.out.println("Course successfully assigned to the professor.");
            } else {
                System.out.println("Failed to assign the course. Please check the Course ID.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


        // Method to get enrolled students for a given course
        public void viewEnrolledStudents(String courseID) {
             String sql = "SELECT ce.student_id, u.name " +
                    "FROM CourseEnrollment ce " +
                    "JOIN user u ON ce.student_id = u.user_id " +
                    "WHERE ce.course_id = ?";
            ;

            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                // Set the course_id in the prepared statement
                pstmt.setString(1, courseID);

                try (ResultSet rs = pstmt.executeQuery()) {

                    System.out.println("Enrolled Students for Course ID: " + courseID);
                    System.out.println("+------------+----------------------+");
                    System.out.printf("| %-10s | %-20s |%n", "Student ID", "Student Name");
                    System.out.println("+------------+----------------------+");

                    while (rs.next()) {
                        String studentId = rs.getString("student_id");
                        String studentName = rs.getString("name");
                        System.out.printf("| %-10s | %-20s |%n", studentId, studentName);
                    }

                    System.out.println("+------------+----------------------+");

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    public void viewEnrolledStudents(Integer profId) {

            // First, get the list of courses taught by the professor
            String coursesSql = "SELECT course_id FROM course WHERE professor_id = ?";

            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(coursesSql)) {

                // Set the professor_id in the prepared statement
                pstmt.setInt(1, profId);

                try (ResultSet rs = pstmt.executeQuery()) {

                    boolean hasCourses = false;

                    // Iterate through the courses taught by the professor
                    while (rs.next()) {
                        hasCourses = true;
                        String courseId = rs.getString("course_id");

                        // Call the method to view enrolled students for each course
                        viewEnrolledStudents(courseId);
                        System.out.println(); // Print a newline for better readability
                    }

                    if (!hasCourses) {
                        System.out.println("The professor is not teaching any courses.");
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }


    }
    }

