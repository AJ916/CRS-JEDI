package com.flipkart.bean;

import java.util.ArrayList;

public class GradeCard extends RegisteredCourses {

	public GradeCard() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	private Integer studentID;
	private Integer semester;
	private Float cgpa;
	
	public GradeCard(Integer studentID, Integer semesterID, ArrayList<String> courseID, Integer studentID2,
			Integer semester, Float cgpa) {
		super(studentID, semesterID, courseID);
		studentID = studentID2;
		this.semester = semester;
		this.cgpa = cgpa;
	}

	public Integer getStudentID() {
		return studentID;
	}

	public void setStudentID(Integer studentID) {
		this.studentID = studentID;
	}

	public Integer getSemester() {
		return semester;
	}

	public void setSemester(Integer semester) {
		this.semester = semester;
	}

	public Float getCgpa() {
		return cgpa;
	}

	public void setCgpa(Float cgpa) {
		this.cgpa = cgpa;
	}

}
