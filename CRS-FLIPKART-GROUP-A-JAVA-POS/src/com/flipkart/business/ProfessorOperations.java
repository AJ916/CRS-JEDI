package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.ProfessorDaoOps;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProfessorOperations {
	private ProfessorDaoOps professorDaoOps;
	public ProfessorOperations() {
		professorDaoOps=new ProfessorDaoOps();
	}

	public void viewEnrolledStudents(Integer profId) {
		// Fetch the course by courseID
		professorDaoOps.viewEnrolledStudents(profId);
	}


    public void courseSelection(Integer profId) {
		professorDaoOps.courseSelection(profId);
    }

	public boolean addGradesForCourse(int professorId, String courseId) {
		// Verify that the professor teaches the course
		if (!professorDaoOps.isCourseTaughtByProfessor(professorId, courseId)) {
			System.out.println("You are not assigned to teach this course.");
			return false;
		}

		// Get the list of students enrolled in the course
		List<Integer> studentIds = professorDaoOps.getStudentsInCourse(courseId);

		if (studentIds.isEmpty()) {
			System.out.println("No students are enrolled in this course.");
			return false;
		}

		// Loop through each student and prompt for a grade
		for (int studentId : studentIds) {
			System.out.print("Enter grade for student ID " + studentId + ": ");
			String grade = new java.util.Scanner(System.in).nextLine();

			if (!professorDaoOps.addGrade(studentId, courseId, grade)) {
				System.out.println("Failed to add grade for student ID " + studentId);
				return false;
			}
		}

		System.out.println("All grades have been successfully added for course " + courseId);
		return true;
	}

	public List<Course> getCoursesTaughtByProfessor(int professorId) {
		return professorDaoOps.getCoursesTaughtByProfessor(professorId);
	}
}

