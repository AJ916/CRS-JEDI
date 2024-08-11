package com.flipkart.client;

import com.flipkart.bean.Payment;
import com.flipkart.bean.GradeCard;
import com.flipkart.business.StudentOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.*;

public class CRSStudentMenu {
	private StudentOperations studentOperations;
	public CRSStudentMenu() {
		studentOperations = new StudentOperations();
	}

	public void CreateStudentMenu(Integer studentId) {
		// TODO Auto-generated method stub
		System.out.println("in student menu!");

		Scanner sc = new Scanner(System.in);

		int input = 0;
		while (true) {
			System.out.println("\n************* Welcome Student *************\n");
			System.out.println("\nChoose an option from the menu: ");
			System.out.println("---------------------------------------");
			System.out.println("Press 1: Register Courses");
			System.out.println("Press 2: Add Course");
			System.out.println("Press 3: Drop Course");
			System.out.println("Press 4: View Registered Courses");
			System.out.println("Press 5: View Report Card");
			System.out.println("Press 6: CheckPaymentWindow");
			System.out.println("Press 7: DoPayment");
			System.out.println("Press 8: Logout");
			System.out.println("*********************************************************");
			input =sc.nextInt();
			switch (input) {
				case 1:
					registerCourses(studentId);
					break;
				case 2:
					addCourse(studentId);
					break;
				case 3:
					dropCourse(studentId);
					break;
				case 4:
					viewRegisteredCourses(studentId);
					break;
				case 5:
					viewReportCard(studentId);
					break;
				case 6:
					checkPaymentWindow(studentId);
					break;
				case 7:
					doPayment(studentId);
					break;
				case 8:
//				System.exit(0);
					return;
				default:
					System.out.println("***** Wrong Choice *****");
			}
		}
	}

	private void doPayment(int studentId) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Payment Amount: ");
		Integer amount = sc.nextInt();
		System.out.println("Enter Payment ID: ");
		Integer paymentID = sc.nextInt();
		// Assuming payment status is true for successful payments
		Boolean paymentStatus = true;

		Payment payment = new Payment(studentId, paymentID, paymentStatus, amount);
		studentOperations.DoPayment(payment);
		System.out.println("Payment processed.");
	}

	private void checkPaymentWindow(int studentId) {
		Boolean isOpen = studentOperations.checkPaymentWindow(studentId);
		if (isOpen) {
			System.out.println("Payment window is open.");
		} else {
			System.out.println("Payment window is closed.");
		}
	}

	private void viewReportCard(int studentId) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Semester ID: ");
		int semesterId = sc.nextInt();
//		GradeCard gradeCard = studentOperations.viewReportCard(studentId, semesterId);
//		if (gradeCard != null) {
//			// Print or process the GradeCard details
//			System.out.println("Grade Card Details:");
//			// Assuming GradeCard has a method to display details
//			// System.out.println(gradeCard.getDetails());
//		} else {
//			System.out.println("Report Card not found.");
//		}
	}

	private void viewRegisteredCourses(int studentId) {
		studentOperations.viewRegisteredCourses(studentId); // Assuming semesterId 0 or as needed
	}

	private void addCourse(Integer studentId) {
		// TODO Auto-generated method stub
		if (!(studentOperations.isAddDropWindowOpen())) {
			System.out.println("Course addition is currently disabled.");
			return;
		}
		Scanner sc = new Scanner(System.in);
		studentOperations.showCourseCatalog();
		String courseId;
		while (true) {
			System.out.print("Enter Course ID to add: ");
			courseId = sc.nextLine();
			if (studentOperations.isValidCourseId(courseId)) {
				studentOperations.addCourse(studentId, courseId);
				break;
			} else {
				System.out.println("Invalid Course ID. Please enter a valid Course ID.");
			}
		}
	}
	private void dropCourse(int studentId) {
		if (!(studentOperations.isAddDropWindowOpen())) {
			System.out.println("Course Dropping is currently disabled.");
			return;
		}
		Scanner sc = new Scanner(System.in);
		studentOperations.viewRegisteredCourses(studentId);
		String courseId;
		while (true) {
			System.out.print("Enter Course ID to drop course: ");
			courseId = sc.nextLine();
			if (studentOperations.isValidCourseId(courseId)) {
				studentOperations.dropCourse(studentId, courseId);
				break;
			} else {
				System.out.println("Invalid Course ID. Please enter a valid Course ID.");
			}
		}
	}



	private void registerCourses(int studentId) {
		// Check if the student is already registered in any courses
		if (studentOperations.isStudentAlreadyRegistered(studentId)) {
			System.out.println("You are already registered in courses.");
			return; // Exit the method if the student is already registered
		}

		Scanner scanner = new Scanner(System.in);

		// Display available courses
		studentOperations.showCourseCatalog();

		// Set to track selected courses and avoid duplicates
		Set<String> selectedCourses = new HashSet<>();

		// Prompt the student to select 4 primary courses
		List<String> primaryCourses = new ArrayList<>();
		System.out.println("Select 4 primary courses:");
		for (int i = 1; i <= 4; i++) {
			String courseId;
			while (true) {
				System.out.print("Enter Course ID for primary course " + i + ": ");
				courseId = scanner.nextLine();
				if (studentOperations.isValidCourseId(courseId) && !selectedCourses.contains(courseId)) {
					primaryCourses.add(courseId);
					selectedCourses.add(courseId); // Add to the set to track the selection
					break;
				} else if (selectedCourses.contains(courseId)) {
					System.out.println("You have already selected this course. Please choose a different Course ID.");
				} else {
					System.out.println("Invalid Course ID. Please enter a valid Course ID.");
				}
			}
		}

		// Prompt the student to select 2 alternate courses
		List<String> alternateCourses = new ArrayList<>();
		System.out.println("Select 2 alternate courses:");
		for (int i = 1; i <= 2; i++) {
			String courseId;
			while (true) {
				System.out.print("Enter Course ID for alternate course " + i + ": ");
				courseId = scanner.nextLine();
				if (studentOperations.isValidCourseId(courseId) && !selectedCourses.contains(courseId)) {
					alternateCourses.add(courseId);
					selectedCourses.add(courseId); // Add to the set to track the selection
					break;
				} else if (selectedCourses.contains(courseId)) {
					System.out.println("You have already selected this course. Please choose a different Course ID.");
				} else {
					System.out.println("Invalid Course ID. Please enter a valid Course ID.");
				}
			}
		}

		// Call StudentOperations to attempt course registration
		studentOperations.registerCourses(studentId, primaryCourses, alternateCourses);
	}

}
