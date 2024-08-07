package com.flipkart.business;

import java.util.ArrayList;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Payment;

public class StudentOperations {

	public StudentOperations() {
		// TODO Auto-generated constructor stub
	}
	boolean addCourse(int studentId, int semesterId, String courseId, boolean isPrimary) {
		return false;
	}
	boolean dropCourse(int studentId, int semesterId, String courseId) {
		return false;
	}
	boolean finishRegistration(int studentId, int semesterId) {
		return true;
	}
	ArrayList<Course> viewAvailableCourses(){
		return null;
	}
	GradeCard viewReportCard(int StudentID, int semesterId) {
		return null;
		
	}
	Boolean checkPaymentWindow(int StudentID) {
		return false;
	}
	void DoPayment(Payment payment) {

	}
	void viewRegisteredCourses(int studentID, int semesterId) {
		
	}
}
