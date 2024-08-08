package com.flipkart.client;

import java.util.Scanner;

public class CRSProfessorMenu {

	public void CreateProfessorMenu(String profId) {
		// TODO Auto-generated method stub
		System.out.println("in professor menu!");
		Scanner sc=new Scanner(System.in);
		
		int input = 0;
		while(true)
		{	
			
			System.out.println("\n************* Welcome Professor *************\n");
			System.out.println("\nChoose an option from the menu: ");
			System.out.println("---------------------------------------");
			System.out.println("Press 1: Course Selection");
			System.out.println("Press 2: Add Grade");
			System.out.println("Press 3: View Enrolled Students");
			System.out.println("Press 4: Logout ");
			System.out.println("*********************************************************");
			input =sc.nextInt();
			switch(input)
			{
				case 1:
					//view all the courses taught by the professor
					courseSelection(profId);
					break;
				case 2:
					//view all the enrolled students for the course
					addGrade(profId);
					break;
					
				case 3:
					//add grade for a student
					viewEnrolledStudents(profId);
					break;
				case 4:
					//logout from the system
					return;
			default:
					System.out.println("***** Wrong Choice *****");
			}
		}

	}
		public void viewEnrolledStudents(String profId) {
			System.out.println("Viewing enrolled students");
		}
		public void addGrade(String profId) {
			System.out.println("Adding grades");
		}
		public void courseSelection(String profId) {
			System.out.println("Viewing courses for selection");
		}
}

