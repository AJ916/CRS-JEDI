package com.flipkart.client;

import com.flipkart.business.AdminOperations2;

import java.util.Scanner;

public class CRSAdminMenu {

    AdminOperations2 adminOps;

    public void CreateAdminMenu(Integer AdminId) {
        adminOps = new AdminOperations2();
        // TODO Auto-generated method stub
        System.out.println("in admin menu!");
        Scanner sc = new Scanner(System.in);

        int input = 0;
        while (true) {
            System.out.println("\n************* Welcome Admin *************\n");
            System.out.println("\nChoose an option from the menu: ");
            System.out.println("---------------------------------------");
            System.out.println("Press 1: Approve Student Registration");
            System.out.println("Press 2: Add Professor");
            System.out.println("Press 3: Add Course");
            System.out.println("Press 4: Remove Professor");
            System.out.println("Press 5: Remove Course");
            System.out.println("Press 6: View approved Students");
            System.out.println("Press 7: Send Payment Notification");
            System.out.println("Press 8: Generate Report Cards");
            System.out.println("Press 9: Logout");
            System.out.println("*********************************************************");
            input = sc.nextInt();
            switch (input) {
                case 1:
                    approveStudentRegistration();
                    break;
                case 2:
                    addProfessor();
                    break;
                case 3:
                    addCourse(AdminId);
                    break;
                case 4:
                    removeCourse(AdminId);
                    break;
                case 5:
                    removeProfessor(AdminId);
                    break;
                case 6:
                    sendFeePayNotification(AdminId);
                    break;
                case 7:
                    viewApprovedStudents(AdminId);
                    break;
                case 8:
                    return;
//				System.exit(0);
                default:
                    System.out.println("***** Wrong Choice *****");
            }
        }
    }

    private void viewApprovedStudents(Integer adminId) {
        adminOps.viewApprovedStudents();
    }

    private void sendFeePayNotification(Integer adminId) {
        // TODO Auto-generated method stub
        adminOps.sendFeePayNotification();
        System.out.println("Fee payment notifications sent.");

    }

//	private void viewReportCard(String adminId) {
//
//
//	}

    private void removeProfessor(Integer adminId) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Professor ID to remove: ");
        int professorId = sc.nextInt();
        adminOps.removeProfessor(professorId);
    }

    private void addProfessor() {

        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Professor Username: ");
        String username = sc.nextLine();
        System.out.println("\nEnter Professor name");
        String profName = sc.nextLine();
        System.out.println("\nEnter Professor Password");
        String profPass = sc.nextLine();
        System.out.println("\nEnter Professor Department");
        String profDept = sc.nextLine();
        System.out.println("\nEnter Professor Designation");
        String profDes = sc.nextLine();
        Integer userId = 0;

        int profId= adminOps.addProfessor(username, profName, "professor", profPass, profDept, profDes);
        if (profId >0) {
            System.out.println("Professor added successfully."+ "Professor ID: " + profId);
        } else {
            System.out.println("Professor already exists.");
        }
    }

    private void removeCourse(Integer adminId) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Course ID to remove: ");
        String courseId = sc.nextLine();
        adminOps.removeCourse(courseId);
    }

    private void addCourse(Integer adminId) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Course ID to add: ");
        String courseId = sc.nextLine();
        System.out.println("Enter Course Name: ");
        String courseName = sc.nextLine();

        adminOps.addCourse(courseName, courseId);

    }


    private void approveStudentRegistration() {
        // TODO Auto-generated method stub
        adminOps.showUnapprovedStudents();
    }
}
