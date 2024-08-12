package com.flipkart.business;

import com.flipkart.bean.User;
import com.flipkart.dao.UserDaoOps;


public class UserOperations implements UserOperationsInterface{

	private UserDaoOps userDaoOps;

	public UserOperations() {
		userDaoOps = new UserDaoOps();
	}
	@Override
	public void getRolebyLogin(User user) {
		userDaoOps.getRoleByLogin(user);
	}
	@Override
	public boolean isApproved(String username){
		return userDaoOps.isApproved(username);
	}
	@Override
	public Boolean updatePassword(String username, String newPassword){
		return userDaoOps.updatePassword(username, newPassword);
	}
	@Override
	public Boolean checkCredentials(String username, String password ){

		return userDaoOps.checkCredentials(username, password);
	}
}
