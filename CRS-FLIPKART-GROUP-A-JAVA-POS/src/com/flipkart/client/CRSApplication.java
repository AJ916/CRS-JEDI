/**
 * 
 */
package com.flipkart.client;

import java.util.Scanner;

/**
 * 
 */
public class CRSApplication {

	private Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		CRSApplication newUser = new CRSApplication();
		newUser.showMenu();
	}

	private void showMenu() {
		while (true) {
			System.out.println("\n************* Welcome to CRS Application *************\n");
			System.out.println("\nChoose an option from the menu: ");
			System.out.println("---------------------------------------");
			System.out.println("Press 1: Login");
			System.out.println("Press 2: Student Registration");
			System.out.println("Press 3: Update Password");
			System.out.println("Press 4: Exit ");
			System.out.println("*********************************************************");
			int menuOption = sc.nextInt();
			sc.nextLine();

			switch (menuOption) {
			case 1:
				login();
				break;

			case 2:
				registerStudent();
				break;

			case 3:
				updatePassword();
				break;

			case 4:
				sc.close();
				System.out.println("Exited Successfully!");
				return;

			default:
				System.out.println("Invalid input");
			}
		}
	}

	private void login() {
		String username, password, role = null;

		System.out.println("********************************");
		System.out.println("Enter your Username: ");
		username = sc.nextLine();
		System.out.println("Enter your Password: ");
		password = sc.nextLine();
		System.out.println("Choose your Role: ");
		System.out.println("Press 1 for Student");
		System.out.println("Press 2 for Professor");
		System.out.println("Press 3 for Admin");
		int roleOption = sc.nextInt();
		switch (roleOption) {
		case 1:
			role = "student";
			break;

		case 2:
			role = "professor";
			break;

		case 3:
			role = "admin";
			break;
		default:
			System.out.println("Invalid option");
		}
		switch (role) {
		case "student":
			System.out.println("********************************");
			System.out.println("Logged In Successfully as a Student");
			System.out.println("Welcome " + username + " !!");
            CRSStudentMenu stud = new CRSStudentMenu();
            stud.CreateStudentMenu(username);
			break;

		case "professor":
			System.out.println("********************************");
			System.out.println("Logged In Successfully as a Professor");
			System.out.println("Welcome " + username +" Sir!");
            CRSProfessorMenu prof = new CRSProfessorMenu();
            prof.CreateProfessorMenu(username);
			break;

		case "admin":
			System.out.println("********************************");
			System.out.println("Logged In Successfully as an Admin");
			System.out.println("Welcome " + username + " !!");
            CRSAdminMenu adm = new CRSAdminMenu();
            adm.CreateAdminMenu(username);
			break;

		default:
			System.out.println("Invalid Role");
			System.out.println("********************************");
		}
	}
	void registerStudent() {
		
		System.out.println("in register");
	}
	void updatePassword() {
		System.out.println("in upadte");
	}
	
}
