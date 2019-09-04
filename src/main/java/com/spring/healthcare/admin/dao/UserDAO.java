package com.spring.healthcare.admin.dao;

import java.util.List;

import com.spring.healthcare.admin.model.Role;
import com.spring.healthcare.admin.model.User;

public interface UserDAO {
	
	public void batchUserInsert(List<User> userList);
	
	public User getUserDetails(String userName);
	
	public void createUser(User user);
	
	public void modifyUser(User user);
	
	public void deleteUser(Integer userId);
	
	public void deleteUser(String userName);
	
	public boolean checkUserExists(String userName);
	
	public boolean validateUserCredentials(String userName, String password);
	
	public List<Role> getRoleDetails();
	
	public void updateSecurityKey(String userName, String securityKey);
	
	public String getUserSecurityKey(String userName);
	

}
