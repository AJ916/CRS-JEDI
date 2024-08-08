package com.flipkart.business;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.ArrayList;
import java.util.List;

public class ProfessorOperations {
	private List<Professor> professors;
	public ProfessorOperations() {
		professors = new ArrayList<>();
		professors.add(new Professor("parth1", "parth", "professor", "pass", 1, "CS", "Senior"));
		professors.add(new Professor("ansh1", "ansh", "professor", "pass", 2, "CS", "Senior"));
		// TODO Auto-generated constructor stub
	}
	public List<Professor> getProfessors() {
		return professors;
	}
	public boolean addProfessor(String userName, String name, String role, String password, Integer instructorID, String department, String designation) {
		if(findProfessorByUsername(userName)==null){
			professors.add(new Professor(userName,name,role,password,instructorID,department,designation));
			return true;
		}
		return false;
	}
	public Professor findProfessorByUsername(String userName){
		for (Professor professor : professors) {
			if(professor.getUserName().equals(userName)){
				return professor;
			}
		}
		return null;
	}
	void addGrade(Integer studentID, Integer semesterID,String courseID, String alphaGrade) {
		
	}
	void ViewEnrolledStudents(String courseID, Integer semesterID) {
		
	}
	void CourseSelection(Integer instructorID, Integer semesterID, String courseID) {
		
	}
	public void viewProfessors() {
		for (Professor professor : professors) {

			System.out.println(professor.getInstructorID()+" "+professor.getDepartment()+" "+professor.getName()+" "+professor.getUserName());
		}
	}
}
