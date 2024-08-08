package com.flipkart.client;

import java.util.Scanner;

public class CRSStudentMenu {

	public void CreateStudentMenu(String StudId) {
		// TODO Auto-generated method stub
		System.out.println("in student menu!");

		Scanner sc = new Scanner(System.in);

		int input = 0;
		while (true) {
			System.out.println("\n************* Welcome Professor *************\n");
			System.out.println("\nChoose an option from the menu: ");
			System.out.println("---------------------------------------");
			System.out.println("Press 1: Course Selection");
			System.out.println("Press 2: Add Course");
			System.out.println("Press 3: Drop Course");
			System.out.println("Press 4: Finish registration ");
			System.out.println("Press 5: View Registered Courses");
			System.out.println("Press 6: View Report Card");
			System.out.println("Press 7: Check Payment Window");
			System.out.println("Press 8: Do Payment");
			System.out.println("Press 9: Logout");
			System.out.println("*********************************************************");
			input =sc.nextInt();
			switch (input) {
			case 1:
				registerCourses(StudId);
			case 2:
				addCourse(StudId);
				break;
			case 3:
				dropCourse(StudId);
				break;

			case 4:
				finishRegistration(StudId);
				break;
			case 5:
				viewRegisteredCourses(StudId);
				break;
			case 6:
				viewReportCard(StudId);
				break;
			case 7:
				checkPaymentWindow(StudId);
				break;
			case 8:
				DoPayment(StudId);
				break;
			case 9:
				return;
			default:
				System.out.println("***** Wrong Choice *****");
			}
		}
	}

	private void DoPayment(String studId) {
		// TODO Auto-generated method stub

	}

	private void checkPaymentWindow(String studId) {
		// TODO Auto-generated method stub

	}

	private void viewReportCard(String studId) {
		// TODO Auto-generated method stub

	}

	private void viewRegisteredCourses(String studId) {
		// TODO Auto-generated method stub

	}

	private void finishRegistration(String studId) {
		// TODO Auto-generated method stub

	}

	private void dropCourse(String studId) {
		// TODO Auto-generated method stub

	}

	private void addCourse(String studId) {
		// TODO Auto-generated method stub

	}

	private void registerCourses(String studId) {
	}

}
