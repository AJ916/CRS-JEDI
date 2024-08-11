package com.flipkart.business;

import com.flipkart.bean.User;
import com.flipkart.dao.UserDaoOps;


public class UserOperations {

	private UserDaoOps userDaoOps;

	public UserOperations() {
		userDaoOps = new UserDaoOps();
	}
	public void getRolebyLogin(User user) {
		userDaoOps.getRolebyLogin(user);
	}
	public boolean isApproved(String username){
		return userDaoOps.isApproved(username);
	}
	
	public Boolean updatePassword(String username, String newPassword){
		return userDaoOps.updatePassword(username, newPassword);
	}
	public Boolean checkCredentials(String username, String password ){

		return userDaoOps.checkCredentials(username, password);
	}
}
