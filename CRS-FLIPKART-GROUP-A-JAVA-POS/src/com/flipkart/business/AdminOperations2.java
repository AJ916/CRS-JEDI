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
	private AdminDaoOps adminDaoOps;


	public AdminOperations2() {


		admins = new ArrayList<>();
		adminDaoOps = new AdminDaoOps();
	}


	public Admin findAdminByUsername(String userName) {
		for (Admin admin : admins) {
			if (admin.getUserName().equals(userName)) {
				return admin;
			}
		}
		return null;

	}

	public void addCourse() {
		Scanner sc = new Scanner(System.in);
		String courseId;
		while (true){
			System.out.println("Enter Course ID: ");
			courseId= sc.nextLine();
			if(adminDaoOps.isCourseExists(courseId)){
				System.out.println("Course already exists!");
			}
			else{
				break;
			}
		}
		System.out.println("Enter Course Name: ");
		String course_name = sc.nextLine();
		System.out.println("Is the course Offered: ");
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

	public void sendFeePayNotification() {
	}

	public void viewApprovedStudents() {
		adminDaoOps.viewApprovedStudents();
	}

	public void showUnapprovedStudents() {
		System.out.println("The list of unapproved students is:");
		boolean flag = adminDaoOps.printUnapprovedStudents();
		if(flag) {
			System.out.println("Enter the student id you wish to approve:");
			Scanner sc = new Scanner(System.in);
			int studentId = sc.nextInt();
			adminDaoOps.approveOneStudent(studentId);
		}
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
