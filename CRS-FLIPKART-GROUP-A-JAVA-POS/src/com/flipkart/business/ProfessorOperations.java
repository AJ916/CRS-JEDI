package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProfessorOperations {
	private List<Professor> professors;
	private AdminOperations adminOps;
	private StudentOperations studentOps;
	private List<Grade> grades;
	public ProfessorOperations() {
		professors = new ArrayList<>();
		professors.add(new Professor("parth1", "parth", "professor", "pass", 1, "CS", "Senior"));
		professors.add(new Professor("ansh1", "ansh", "professor", "pass", 2, "CS", "Senior"));
		grades = new ArrayList<>();
		adminOps = new AdminOperations();
		studentOps = new StudentOperations();
	}
	public List<Professor> getProfessors() {
		return professors;
	}

	public boolean addProfessor(String userName, String name, String role, String password, Integer instructorID, String department, String designation) {
		if (findProfessorByUsername(userName) == null) {
			professors.add(new Professor(userName, name, role, password, instructorID, department, designation));
			return true;
		}
		return false;
	}
	public Professor findProfessorByUsername(String userName) {
		for (Professor professor : professors) {
			if (professor.getUserName().equals(userName)) {
				return professor;
			}
		}
		return null;
	}
	public void getCourses(String profID) {
		List<Course> courses = adminOps.getCourseCatalogue().stream()
				.filter(course -> course.getInstructorID() != null && course.getInstructorID().equals(profID))
				.toList();
		System.out.println("Courses taught by Professor with ID " + profID + ":");
		for (Course course : courses) {
			System.out.println(course.getCourseID() + " - " + course.getCoursename());
		}
	}

	public void addGrade(Integer studentID, String courseID, String alphaGrade) {
//		grades.add(new Grade(studentID, courseID, alphaGrade));
//		System.out.println("Grade added successfully for student ID: " + studentID);
	}
//	public void viewEnrolledStudents(String courseID) {
//		List<Student> students = adminOps.getCourseCatalogue().stream()
//				.filter(course -> course.getCourseID().equals(courseID))
//				.flatMap(course -> course.getEnrolledStudents().stream()
//						.map(studentId -> adminOps.findStudentById(studentId)))
//				.toList();
//
//		System.out.println("Students enrolled in course " + courseID + ":");
//		for (Student student : students) {
//			System.out.println(student.getStudentID() + " - " + student.getName());
//		}
//	}
	public void viewEnrolledStudents(String courseID) {
		// Fetch the course by courseID
		Course course = adminOps.getCourseCatalogue().stream()
				.filter(c -> c.getCourseID().equals(courseID))
				.findFirst()
				.orElse(null);

		if (course == null) {
			System.out.println("Course with ID " + courseID + " not found.");
			return;
		}

		// Get the list of student IDs enrolled in the course
		List<Integer> studentIDs = course.getEnrolledStudents();

		// Fetch the students by their IDs
		List<Student> students = studentIDs.stream()
				.map(studentID -> studentOps.findStudentById(studentID)) // Assuming you have a StudentOperations instance named studentOps
				.filter(Objects::nonNull) // Filter out null values if any
				.toList();

		System.out.println("Students enrolled in course " + courseID + ":");
		for (Student student : students) {
			System.out.println(student.getStudentID() + " - " + student.getName());
		}
	}

	public void courseSelection(Integer instructorID, String courseID) {
		Professor professor = findProfessorByID(instructorID);
		if (professor != null) {
			Course course = adminOps.findCourseById(courseID);
			if (course != null) {
				course.setInstructorID(professor.getUserName()); // Set instructorID to professor's username
				System.out.println("Course " + courseID + " assigned to Professor " + professor.getName());
			} else {
				System.out.println("Course not found.");
			}
		} else {
			System.out.println("Course not found.");
		}
	}
	public void viewProfessors() {
		for (Professor professor : professors) {
			System.out.println(professor.getInstructorID() + " " + professor.getDepartment() + " " + professor.getName() + " " + professor.getUserName());
		}
	}
	private Professor findProfessorByID(Integer instructorID) {
		for (Professor professor : professors) {
			if (professor.getInstructorID().equals(instructorID)) {
				return professor;
			}
		}
		return null;
	}
}
