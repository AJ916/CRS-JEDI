package com.flipkart.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Payment;
import com.flipkart.bean.Student;

public class StudentOperations {
	private List<Student> students;

public StudentOperations(){
	students = new ArrayList<>();
	students.add(new Student("ajey1","ajey","student","pass",101,"CS",null));
	students.add(new Student("nikhil1","nikhil","student","pass",102,"CS",null));
	students.add(new Student("kunal1","kunal","student","pass",103,"CS",null));
}

	public List<Student> getStudents() {
		return students;
	}

//	public void addStudent() {
//		Student newStudent1 = new Student();
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("Enter Your ID");
//		newStudent1.setStudentID(scanner.nextInt());
//		System.out.println("Enter Your Name");
//		newStudent1.setName(scanner.next());
//		System.out.println("Enter Your Password");
//		newStudent1.setPassword(scanner.next());
//		newStudent1.setRole("student");
//		System.out.println("Enter Student UserName");
//		newStudent1.setUserName(scanner.next());
//		System.out.println("Enter Student Department");
//		newStudent1.setDepartment(scanner.next());
//		students.add(newStudent1);
//	}
	public boolean addStudent(String userName, String name, String role, String password,Integer studentID, String department) {
		if(findStudentByUsername(userName)==null){
			students.add(new Student(userName,name,role,password,studentID,department,null));
			return true;
		}
        return false;
    }
	public Student findStudentByUsername(String userName){
		for (Student student : students) {
			if(student.getUserName().equals(userName)){
				return student;
			}
		}
		return null;
	}

	void registerCourses(int studentId, String courseId) {
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

	public void viewStudents() {
		for (Student student : students) {
			System.out.println(student.getStudentID()+" "+student.getDepartment()+" "+student.getName()+" "+student.getUserName());
		}
	}
}
