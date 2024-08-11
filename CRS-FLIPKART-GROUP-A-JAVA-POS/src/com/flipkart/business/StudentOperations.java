package com.flipkart.business;

import java.util.ArrayList;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Payment;
import com.flipkart.bean.Student;
import com.flipkart.dao.studentDaoOps;

public class StudentOperations {
	private List<Student> students;
	private AdminOperations2 adminOps;
	private studentDaoOps studentDaoOps;

	public StudentOperations(){
		students = new ArrayList<>();
		adminOps= new AdminOperations2();
		studentDaoOps = new studentDaoOps();
		ArrayList<String> courses = new ArrayList<>();
	}

	public List<Student> getStudents() {
		return students;
	}

	public int addStudent(String userName, String name, String role, String password,String department) {
		int sId = studentDaoOps.registerNewStudent(userName,password,role,name,department);
		return sId;
	}
	public Student findStudentByUsername(String userName){
		for (Student student : students) {
			if(student.getUserName().equals(userName)){
				return student;
			}
		}
		return null;
	}

	public void registerCourses(int studentId, List<String> primaryCourses, List<String> alternateCourses) {
		int registeredCount = 0;
		// Try registering for primary courses
		for (String courseId : primaryCourses) {
			boolean success = studentDaoOps.registerStudentForCourse(studentId, courseId);
			if (success) {
				registeredCount++;
			}
			if (registeredCount >= 4) {
				break;
			}
		}

		// If not all primary courses were available, try alternate courses
		if (registeredCount < 4) {
			for (String courseId : alternateCourses) {
				boolean success = studentDaoOps.registerStudentForCourse(studentId, courseId);
				if (success) {
					registeredCount++;
				}
				if (registeredCount >= 4) {
					break;
				}
			}
		}

		// Output the result
		if (registeredCount >= 4) {
			System.out.println("Courses successfully registered.");
		} else {
			System.out.println("Unable to register in 4 courses. Registered in " + registeredCount + " courses.");
		}
	}

	public void addCourse(int studentId, String courseId) {
		studentDaoOps.registerStudentForCourse(studentId, courseId);
	}


	public void dropCourse(int studentId, String courseId) {
		studentDaoOps.removeStudentFromCourse(studentId,courseId);
	}

	public void viewReportCard(int studentID, int semesterId) {
//		Student student = findStudentById(studentID);
//		if (student != null) {
//			return student.getGradeCard();
//		}
//		System.out.println("Student not found.");
//		return null;
		return;
	}
	public Boolean checkPaymentWindow(int StudentID) {
		System.out.println("Payment window status checked.");
		return true;
	}
	public void DoPayment(Payment payment) {
		System.out.println("Payment processed successfully.");
	}

	public void viewRegisteredCourses(int studentID) {
		studentDaoOps.viewRegisteredCourses(studentID);
	}

	public Student findStudentById(int studentId) {
		for (Student student : students) {
			if (student.getStudentID() == studentId) {
				return student;
			}
		}
		return null;
	}

	public void showCourseCatalog() {
		studentDaoOps.displayCourseCatalog();
	}

	public boolean isValidCourseId(String courseId) {
		boolean flag= studentDaoOps.isValidCourseId(courseId);
		return flag;
	}

	public boolean isStudentAlreadyRegistered(int studentId) {
		boolean flag = studentDaoOps.isStudentAlreadyRegistered(studentId);
		return flag;
	}

	public boolean isAddDropWindowOpen() {
		return studentDaoOps.isAddDropWindowOpen();
	}

	public boolean isUsernameTaken(String username) {
		return studentDaoOps.isUsernameTaken(username);
	}
}
