package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
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
	private List<Grade> grades;
	private ProfessorDaoOps professorDaoOps;
	public ProfessorOperations() {
		grades = new ArrayList<>();
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
	public void getCourses(String profID) {
		List<Course> courses = adminOps.getCourseCatalogue().stream()
				.filter(course -> course.getprofessorId() != null && course.getprofessorId().equals(profID))
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
	public void viewEnrolledStudents(Integer profId) {
		// Fetch the course by courseID
		professorDaoOps.viewEnrolledStudents(profId);
	}

	public void viewProfessors() {
		for (Professor professor : professors) {
			System.out.println(professor.getProfessorId() + " " + professor.getDepartment() + " " + professor.getName() + " " + professor.getUserName()+ " "+ professor.getPassword());
		}
	}
	private Professor findProfessorByID(Integer professorId) {
		for (Professor professor : professors) {
			if (professor.getProfessorId().equals(professorId)) {
				return professor;
			}
		}
		return null;
	}

    public void courseSelection(Integer profId) {
		professorDaoOps.courseSelection(profId);
    }
}

