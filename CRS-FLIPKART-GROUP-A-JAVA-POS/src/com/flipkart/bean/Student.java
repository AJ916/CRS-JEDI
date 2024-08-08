package com.flipkart.bean;

import java.util.ArrayList;

public class Student extends User{

	private Integer studentID;
	private String department;
	private ArrayList<String> courseID = new ArrayList<String>();
	public Student(String userName, String name, String role, String password,Integer studentID, String department, ArrayList<String> courseID) {
		super(userName,name,role,password);
		this.studentID = studentID;
		this.department = department;
		this.courseID = courseID;
	}
	public Student() {
		super();
	}

	public Integer getStudentID() {
		return studentID;
	}

	public void setStudentID(Integer studentID) {
		this.studentID = studentID;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public ArrayList<String> getCourseID() {
		return courseID;
	}

	public void setCourseID(ArrayList<String> courseID) {
		this.courseID = courseID;
	}
	
	
}
