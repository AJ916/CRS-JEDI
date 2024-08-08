package com.flipkart.business;

import com.flipkart.bean.Admin;
import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.ArrayList;
import java.util.List;

public class AdminOperations {

	public AdminOperations() {
		// TODO Auto-generated constructor stub

	}
	List<Admin> admins=  new ArrayList<Admin>();

	public void createAdmins(){
		Admin admin1= new Admin();
		admin1.setName("admin1");
		admin1.setPassword("password");
		admin1.setUserName("admin1");
		admin1.setRole("admin");
		admins.add(admin1);
	}

	public String findAdminByUsername(String userName){
		createAdmins();

		for(Admin admin1: admins){
			if(admin1.getUserName().equals(userName)){
				String pass= admin1.getPassword();
				return pass;
			}
		}
//		System.out.println(userName);
		return null;
	}
	void approveStudentRegistration(int studentId,int semesterId) {

	}
	void addCourse(String course_name, String courseID, int semester){
		
	}
	void removeCourse(String courseID) {
		
	}
	void addProfessor(Professor professor) {
		
	}
	void removeProfessor(int professorID) {
		
	}
	Float CalculateCgpa(GradeCard gc) {
		return null;
	}
	GradeCard generateGradeCard(int studentID) {
		return null;
		
	}
	void sendFeePayNotification() {
	}

	void PaymentCompletionNotification() {

	}
	
}
