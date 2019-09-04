package com.spring.healthcare.admin.service;

import java.util.List;

import com.spring.healthcare.admin.model.Role;
import com.spring.healthcare.admin.model.User;

public interface UserService {
	
	User findById(long id);
	
	User findByName(String name);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	public void removeUserByName(String userName);
	
	List<User> findAllUsers(); 
	
	public boolean isUserExist(User user);
	
	public boolean validateUserData(User user);
	
	public List<Role> getRoleDetails();
	
	public String getUserRoleDetail(String userName);
	
}
