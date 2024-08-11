package com.flipkart.dao;

import java.sql.*;

import com.flipkart.dao.AdminDaoOps;

public class studentDaoOps {
    private AdminDaoOps adminDaoOps = new AdminDaoOps();

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
                                System.out.println("Student record updated.");
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
        } catch (SQLIntegrityConstraintViolationException e) {
            // Check if the exception is due to a unique constraint violation on the username
            if (e.getMessage().contains("Duplicate entry") && e.getMessage().contains("for key 'username'")) {
                System.out.println("Username already exists.");
                return -1;
            } else {
                e.printStackTrace();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    return sId;
    }

    public void displayCourseCatalog() {
        String sql = "SELECT c.course_id, c.course_name, u.name AS professor_name " +
                "FROM course c " +
                "LEFT JOIN user u ON c.professor_id = u.user_id " +
                "WHERE c.is_offered = true";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("+-----------+--------------------------------+-------------------------+-----------------+");
            System.out.printf("| %-9s | %-30s | %-15s |%n", "Course ID", "Course Name", "Professor");
            System.out.println("+-----------+--------------------------------+-------------------------+-----------------+");

            while (rs.next()) {
                String courseId = rs.getString("course_id");
                String courseName = rs.getString("course_name");
               // String department = rs.getString("department");
                String professorName = rs.getString("professor_name") != null ? rs.getString("professor_name") : "TBD";
                System.out.printf("| %-9s | %-30s | %-15s |%n", courseId, courseName, professorName);
            }

            System.out.println("+-----------+--------------------------------+-------------------------+-----------------+");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean registerStudentForCourse(int studentId, String courseId) {
        String checkSeatsSql = "SELECT available_seats FROM course WHERE course_id = ? AND available_seats > 0";
        String registerSql = "INSERT INTO CourseEnrollment (student_id, course_id) VALUES (?, ?)";
        String updateSeatsSql = "UPDATE course SET available_seats = available_seats - 1 WHERE course_id = ?";

        try (Connection conn = this.connect();
             PreparedStatement checkSeatsStmt = conn.prepareStatement(checkSeatsSql);
             PreparedStatement registerStmt = conn.prepareStatement(registerSql);
             PreparedStatement updateSeatsStmt = conn.prepareStatement(updateSeatsSql)) {

            // Check if the course has available seats
            checkSeatsStmt.setString(1, courseId);
            ResultSet rs = checkSeatsStmt.executeQuery();

            if (rs.next()) {
                int availableSeats = rs.getInt("available_seats");

                if (availableSeats > 0) {
                    // Register the student for the course
                    registerStmt.setInt(1, studentId);
                    registerStmt.setString(2, courseId);
                    registerStmt.executeUpdate();

                    // Update the available seats in the course
                    updateSeatsStmt.setString(1, courseId);
                    updateSeatsStmt.executeUpdate();

                    System.out.println("Student " + studentId + " successfully registered for course " + courseId + ".");
                    return true; // Registration succeeded
                } else {
                    System.out.println("Course " + courseId + " is full. No seats available.");
                    return false; // No seats available
                }
            } else {
                System.out.println("Course " + courseId + " does not exist.");
                return false; // Course does not exist
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false; // Registration failed due to an SQL exception
        }
    }

    public boolean removeStudentFromCourse(int studentId, String courseId) {
        String checkEnrollmentSql = "SELECT COUNT(*) FROM CourseEnrollment WHERE student_id = ? AND course_id = ?";
        String removeEnrollmentSql = "DELETE FROM CourseEnrollment WHERE student_id = ? AND course_id = ?";
        String updateSeatsSql = "UPDATE course SET available_seats = available_seats + 1 WHERE course_id = ?";

        try (Connection conn = this.connect();
             PreparedStatement checkEnrollmentStmt = conn.prepareStatement(checkEnrollmentSql);
             PreparedStatement removeEnrollmentStmt = conn.prepareStatement(removeEnrollmentSql);
             PreparedStatement updateSeatsStmt = conn.prepareStatement(updateSeatsSql)) {

            // Check if the student is registered for the course
            checkEnrollmentStmt.setInt(1, studentId);
            checkEnrollmentStmt.setString(2, courseId);
            ResultSet rs = checkEnrollmentStmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);

                if (count > 0) {
                    // Remove the student from the course
                    removeEnrollmentStmt.setInt(1, studentId);
                    removeEnrollmentStmt.setString(2, courseId);
                    removeEnrollmentStmt.executeUpdate();

                    // Update the available seats in the course
                    updateSeatsStmt.setString(1, courseId);
                    updateSeatsStmt.executeUpdate();

                    System.out.println("Student " + studentId + " successfully removed from course " + courseId + ".");
                    return true; // Removal succeeded
                } else {
                    System.out.println("Student " + studentId + " is not registered for course " + courseId + ".");
                    return false; // Student is not registered for the course
                }
            } else {
                System.out.println("Course " + courseId + " does not exist.");
                return false; // Course does not exist
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false; // Removal failed due to an SQL exception
        }
    }

    public boolean isValidCourseId(String courseId) {
        String sql = "SELECT COUNT(*) FROM Course WHERE course_id = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Set the courseId parameter
            pstmt.setString(1, courseId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Check if the count is greater than 0
                    return rs.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean isStudentAlreadyRegistered(int studentId) {
        String sql = "SELECT COUNT(*) FROM CourseEnrollment WHERE student_id = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Check if the count is greater than 0
                    return rs.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void viewRegisteredCourses(int studentID) {
        String sql = "SELECT c.course_id, c.course_name " +
                "FROM CourseEnrollment ce " +
                "JOIN Course c ON ce.course_id = c.course_id " +
                "WHERE ce.student_id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set the student ID parameter
            pstmt.setInt(1, studentID);

            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("Registered Courses for student " + studentID + ":");
                System.out.println("+-----------+--------------------------------+");
                System.out.printf("| %-9s | %-30s |%n", "Course ID", "Course Name");
                System.out.println("+-----------+--------------------------------+");

                // Iterate through the result set and print course details
                while (rs.next()) {
                    String courseId = rs.getString("course_id");
                    String courseName = rs.getString("course_name");
                    System.out.printf("| %-9s | %-30s |%n", courseId, courseName);
                }

                System.out.println("+-----------+--------------------------------+");

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Getter for checking if the add/drop window is open
    public boolean isAddDropWindowOpen() {
        String sql = "SELECT is_add_drop_window_open FROM SystemSettings WHERE id = 1";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                return rs.getBoolean("is_add_drop_window_open");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Default to false if there's an issue
    }

    public boolean isUsernameTaken(String username) {
        String sql = "SELECT COUNT(*) FROM User WHERE username = ?";
        try (Connection conn = this.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) { // Removed the extra curly brace

            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0; // If count > 0, the username is taken
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception based on your error handling strategy
        }
        return false; // If there's an error or no match, assume the username is available
    }
}
