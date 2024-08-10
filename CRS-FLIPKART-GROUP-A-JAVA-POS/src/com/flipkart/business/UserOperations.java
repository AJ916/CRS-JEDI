package com.flipkart.business;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.dao.UserDaoOps;


public class UserOperations {

	private AdminOperations2 adminOps;
	private ProfessorOperations professorOps;
	private StudentOperations studentOps;
	private UserDaoOps userDaoOps;

	public UserOperations() {
		adminOps = new AdminOperations2();
		professorOps = new ProfessorOperations();
		studentOps = new StudentOperations();
		userDaoOps = new UserDaoOps();
	}
	public void getRolebyLogin(User user) {
		userDaoOps.getRolebyLogin(user);
	}
	public boolean isApproved(String username){
		return userDaoOps.isApproved(username);
	}
	boolean loginUser(String userID, String password, String role) {
        return switch (role.toLowerCase()) {
            case "admin" -> {
                Admin admin = adminOps.findAdminByUsername(userID);
                yield admin != null && admin.getPassword().equals(password);
            }
            case "student" -> {
                Student student = studentOps.findStudentByUsername(userID);
                yield student != null && student.getPassword().equals(password);
            }
//            case "professor" -> {
//                Professor professor = professorOps.getProfessorIdByUsername(userID);
//                yield professor != null && professor.getPassword().equals(password);
//            }
            default -> false;
        };
	}
	
	public Boolean updatePassword(String username, String newPassword){
		return userDaoOps.updatePassword(username, newPassword);
	}
	public Boolean checkCredentials(String username, String password ){

		return userDaoOps.checkCredentials(username, password);
	}
}
