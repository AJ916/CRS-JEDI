package com.flipkart.bean;

public class Professor extends User{

	private Integer professorId;
	private String department;
	private String designation;
	public Integer getProfessorId() {
		return professorId;
	}
	public void setProfessorID(Integer instructorID) {
		this.professorId = instructorID;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Professor(String userName, String name, String role, String password,Integer instructorID, String department, String designation, Integer UserId) {
		super(userName,name,role,password, UserId);
		this.professorId = instructorID;
		this.department = department;
		this.designation = designation;
	}
	public Professor() {
		super();
	}
	
}
