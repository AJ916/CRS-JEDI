package com.flipkart.business;


import com.flipkart.bean.Admin;
import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Course;
import com.flipkart.dao.AdminDaoOps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AdminOperations2 {
	private List<Admin> admins;
	private List<Course> courseCatalogue;
	private List<Professor> professors;
	private List<Integer> approvedStudents;
	private Map<Integer, GradeCard> gradeCards;
	private ProfessorOperations professorOps;
	private AdminDaoOps adminDaoOps;


	public AdminOperations2() {


		admins = new ArrayList<>();
		approvedStudents = new ArrayList<>();
		professorOps = new ProfessorOperations("admin");
		adminDaoOps = new AdminDaoOps();

		courseCatalogue = new ArrayList<>();

	}


	public Admin findAdminByUsername(String userName) {
		for (Admin admin : admins) {
			if (admin.getUserName().equals(userName)) {
				return admin;
			}
		}
		return null;

	}
//	public Student findStudentById(int studentId) {
//		// Return the student object based on studentId
//
//		for (Student student : students) {
//			if (student.getStudentID() == studentId) {
//				return student;
//			}
//		}
//		return null; // Or handle the case when the student is not found
//	}

	public void approveStudentRegistration(int studentId) {
		approvedStudents.add(studentId);
		System.out.println("Student registration approved for student ID: " + studentId);
	}

	public List<Course> getCourseCatalogue() {

		return courseCatalogue;
	}

	public void addCourse() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Course ID: \n");
		String courseId = sc.nextLine();
		System.out.println("Enter Course Name: \n");
		String course_name = sc.nextLine();
		System.out.println("Is the course Offered: \n");
		boolean isOffered = sc.nextBoolean();
		adminDaoOps.addCourse(courseId,course_name,isOffered);
	}

	public void showAllProfs(){
		adminDaoOps.showAllProfs();
	}



	public Integer addProfessor(String username, String professorName, String role, String password, String department, String designation) {

		return adminDaoOps.addProfessor(username, professorName, role, password, department, designation);

	}

	public void removeProfessor(Integer professorID) {
		adminDaoOps.removeProf(professorID);
	}

	Float CalculateCgpa(GradeCard gc) {
		return null;
	}

	GradeCard generateGradeCard(int studentID) {
		return null;

	}

	public Course findCourseById(String courseID) {
		for (Course course : courseCatalogue) {
			if (course.getCourseID().equals(courseID)) {
				return course;
			}
		}
		return null;
	}

	public void sendFeePayNotification() {
	}

	public void PaymentCompletionNotification() {

	}

	public void viewApprovedStudents() {
		adminDaoOps.viewApprovedStudents();
	}

	public void showUnapprovedStudents() {
		System.out.println("The list of unapproved students is:");
		adminDaoOps.printUnapprovedStudents();
		System.out.println("Enter the student id you wish to approve:");
		Scanner sc = new Scanner(System.in);
		int studentId = sc.nextInt();
		adminDaoOps.approveOneStudent(studentId);
	}

	public void showAllCourses(){
		adminDaoOps.showAllCourses();
	}

	public void removeCourse(String course_id){
		adminDaoOps.removeCourse(course_id);
	}

	public void setAddDropWindow(boolean open) {
		adminDaoOps.setAddDropWindow(open);
	}
}
