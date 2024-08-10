/**
 *
 */
package com.flipkart.client;

import com.flipkart.bean.*;
import com.flipkart.business.AdminOperations2;
import com.flipkart.business.ProfessorOperations;
import com.flipkart.business.StudentOperations;
import com.flipkart.business.UserOperations;
//import com.flipkart.dao.UserDaoOps;

import java.util.List;
import java.util.Scanner;

/**
 *
 */
public class CRSApplication {
	private StudentOperations studentOps ;
	private ProfessorOperations profOps ;
	private AdminOperations2 adminOps;
	private Scanner sc ;
	private UserOperations userOps;

	public CRSApplication(){

		studentOps = new StudentOperations();
		profOps = new ProfessorOperations();
		adminOps=new AdminOperations2();
		userOps=new UserOperations();
		sc= new Scanner(System.in);
	}
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
			System.out.println("Press 2: Register Student");
			System.out.println("Press 3: Update Password");
			System.out.println("Press 4: Exit");
			System.out.println("*********************************************************");
			int menuOption = sc.nextInt();
			sc.nextLine();
			switch (menuOption) {
				case 1:
					login();
					break;
				case 2:
					registerNewStudent();
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
					break;
			}
		}
	}

	private void login() {


		User user;
		user = new User();
        System.out.println("********************************");
		System.out.println("Enter your Username: ");
		String username = sc.nextLine();
		System.out.println("Enter your Password: ");

		String password = sc.nextLine();
		if(userOps.checkCredentials(username, password)){
			user.setUserName(username);
			user.setPassword(password);
		}

		userOps.getRolebyLogin(user);

		switch (user.getRole()) {
			case "Student":
				Student stud;
				stud = new Student();
				stud.setUserName(user.getUserName());
				stud.setPassword(user.getPassword());
				stud.setRole("Student");
				stud.setStudentID(user.getUserId());
				if(userOps.isApproved(stud.getUserName())){

					System.out.println("********************************");
					System.out.println("Logged In Successfully as a Student");
					System.out.println("Welcome " + stud.getUserName() + " !!");

					CRSStudentMenu studCrs = new CRSStudentMenu();
//					Integer studID = studentOps.getStudentIdByUsername(stud.getUserName());
					studCrs.CreateStudentMenu(stud.getStudentID());
					System.out.println("Welcome " + stud.getUserName() + " !!");
					break;}
				else{
					System.out.println("you are not approved");
					break;
				}

			case "Professor":
				Professor prof;
				prof = new Professor();
				prof.setUserName(user.getUserName());
				prof.setPassword(user.getPassword());
				prof.setRole("Professor");
				prof.setProfessorID(user.getUserId());

				System.out.println("********************************");
				System.out.println("Logged In Successfully as a Professor");
				System.out.println("Welcome " + prof.getUserName() + " !!");

				CRSProfessorMenu profCrs = new CRSProfessorMenu();
				profCrs.CreateProfessorMenu(prof.getProfessorId());
				System.out.println("Welcome " + prof.getUserName() + " !!");
				break;


			case "Admin":
				Admin admin;
				admin = new Admin();
				admin.setUserId(user.getUserId());
				admin.setUserName(user.getUserName());
				System.out.println("********************************");
				System.out.println("Logged In Successfully as an Admin");
				System.out.println("Welcome " + admin.getUserName() + " !!");
				CRSAdminMenu admCrs = new CRSAdminMenu();
				admCrs.CreateAdminMenu(admin.getUserId());
				System.out.println("Welcome " + admin.getUserName() + " Sir!");
				break;

			default:
				System.out.println("Invalid Role");
				System.out.println("********************************");
				break;
		}
	}

	void courseByProfList() {
		// Retrieve the list of all courses from adminOps
		List<Course> courseCatalogue = adminOps.getCourseCatalogue();

		// Check if courseCatalogue is null or empty
		if (courseCatalogue == null || courseCatalogue.isEmpty()) {
			System.out.println("No courses available.");
			return;
		}

		// Print each course with its Course ID and Instructor ID
		System.out.println("Courses and their Instructor IDs:");
		for (Course course : courseCatalogue) {
			String courseID = course.getCourseID();
			String instructorID = course.getInstructorID(); // Assuming this is the ID of the instructor

			// Check if instructorID is null and print appropriate message
			String instructorMessage = (instructorID != null && !instructorID.isEmpty()) ? instructorID : "No instructor assigned";

			System.out.println("Course ID: " + courseID + ", Instructor ID: " + instructorMessage);
		}

		System.out.println("End of course list.");
	}




	void registerNewStudent() {
		System.out.println("enter Username");
		String username = sc.nextLine();
		System.out.println("enter Password");
		String password = sc.nextLine();
		System.out.println("enter name");
		String name = sc.nextLine();
		System.out.println("enter Department");
		String department = sc.nextLine();

		int sId = studentOps.addStudent(username,name,"Student",password,department);
		if(sId>0){
			System.out.println("Student Added Successfully \n Your Student Id is : " + sId);
			System.out.println("Welcome " + username + " !!");
		}else{
			System.out.println("Registration Failed");
		}
	}

	void updatePassword() {
		System.out.println("IN update Password Menu");

				System.out.println("Enter your Username: ");
				String username = sc.nextLine();
				System.out.println("Enter your current Password: ");
				String password = sc.nextLine();

				Boolean result = userOps.checkCredentials(username, password);
				if(result){
					System.out.println("Enter your New Password: ");
					String newPassword = sc.nextLine();

					if(userOps.updatePassword(username,newPassword)){
						System.out.println("Successfully updated password");
					}
					else{
						System.out.println("Password does not match");
					}
				}else {
					System.out.println("Invalid Credentials");
				}
		}

	}

