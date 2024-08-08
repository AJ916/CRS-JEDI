package com.flipkart.client;

import java.util.Scanner;

public class CRSAdminMenu {

	public void CreateAdminMenu(String AdminId) {
		// TODO Auto-generated method stub
		System.out.println("in admin menu!");
		Scanner sc = new Scanner(System.in);

		int input = 0;
		while (true) {
			System.out.println("\n************* Welcome Admin *************\n");
			System.out.println("\nChoose an option from the menu: ");
			System.out.println("---------------------------------------");
			System.out.println("Press 1: Approve Student Registeration");
			System.out.println("Press 2: Add Grade");
			System.out.println("Press 3: Remove Course");
			System.out.println("Press 4: Add Professor");
			System.out.println("Press 5: Remove Professor");
			System.out.println("Press 6: Send Pay Notification");
			System.out.println("Press 7: Logout");
			System.out.println("*********************************************************");
			input =sc.nextInt();
			switch (input) {
			case 1:
				approveStudentRegistration(AdminId);
			case 2:
				addCourse(AdminId);
				break;
			case 3:
				removeCourse(AdminId);
				break;

			case 4:
				addProfessor(AdminId);
				break;
			case 5:
				removeProfessor(AdminId);
				break;
			case 6:
				sendFeePayNotification(AdminId);
				break;
			case 7:
				return;
			default:
				System.out.println("***** Wrong Choice *****");
			}
		}
	}

	private void sendFeePayNotification(String adminId) {
		// TODO Auto-generated method stub

	}

	private void viewReportCard(String adminId) {
		// TODO Auto-generated method stub

	}

	private void removeProfessor(String adminId) {
		// TODO Auto-generated method stub

	}

	private void addProfessor(String adminId) {
		// TODO Auto-generated method stub

	}

	private void removeCourse(String adminId) {
		// TODO Auto-generated method stub

	}

	private void addCourse(String adminId) {
		// TODO Auto-generated method stub

	}

	private void approveStudentRegistration(String adminId) {
		// TODO Auto-generated method stub

	}
}
