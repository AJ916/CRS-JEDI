package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.ProfessorDaoOps;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProfessorOperations {
	//	private List<Professor> professors;
	private static List<Professor> professors = new ArrayList<>();
	private AdminOperations2 adminOps;
	private StudentOperations studentOps;
	private ProfessorDaoOps professorDaoOps;
	public ProfessorOperations() {
		adminOps = new AdminOperations2();
		studentOps = new StudentOperations();
		professorDaoOps=new ProfessorDaoOps();
	}
	private String identifier;
	// Constructor with an argument
	public ProfessorOperations(String identifier) {
		this.identifier = identifier;

	}

	public List<Professor> getProfessors() {
		return professors;
	}

	public boolean addProfessor(String userName, String name, String role, String password, Integer professorId, String department, String designation, Integer UserId) {
		if (getProfessorIdByUsername(userName) == null) {
			professors.add(new Professor(userName, name, role, password, professorId, department, designation, UserId));
			return true;
		}
		return false;
	}
	public Integer getProfessorIdByUsername(String userName) {
		for (Professor professor : professors) {
			if (professor.getUserName().equals(userName)) {
				return professor.getProfessorId();
			}
		}
		return null;
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

