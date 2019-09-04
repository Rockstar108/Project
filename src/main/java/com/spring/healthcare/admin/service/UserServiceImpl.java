package com.spring.healthcare.admin.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.healthcare.admin.dao.UserDAO;
import com.spring.healthcare.admin.enums.Roles;
import com.spring.healthcare.admin.model.Role;
import com.spring.healthcare.admin.model.User;
import com.spring.healthcare.admin.utils.CommonsUtility;

@Service
public class UserServiceImpl implements UserService {

	private Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDAO userDAO;	

	private static List<User> users;

	public List<User> findAllUsers() {
		return users;
	}

	public User findById(long id) {
		for (User user : users) {
			if (user.getUserId() == id) {
				return user;
			}
		}
		return null;
	}

	public User findByName(String userName) {
		logger.info("findByName :: " + userName);
		User user = userDAO.getUserDetails(userName);
		return user;
	}
	
	public String getUserRoleDetail(String userName) {
		logger.info("findByName :: " + userName);
		String roleCode = null;
		User user = findByName(userName);
		int roleId = user.getRoleId();
		for(Roles role: Roles.values()){
			if(role.getRoleId() == roleId) {
				roleCode = role.getRoleCode();
				break;
			}
		}
		return roleCode;		
	}

	public void saveUser(User user) {
		logger.info("Creating User :: " + user.getUserName());
		userDAO.createUser(user);
	}

	@Override
	public void updateUser(User user) {
		logger.info("Updating User :: " + user.getUserName());
//		String doBirth = CommonsUtility.convertDateFormat(user.getDateOfBirth(), "dd-MM-yyyy", "yyyy-MM-dd"); 
//		user.setDateOfBirth(doBirth);
		userDAO.modifyUser(user);
	}
	
	@Override
	public void removeUserByName(String userName) {
		userDAO.deleteUser(userName);		
	}
	
	@Override
	public boolean isUserExist(User user) {
		logger.info("Check User Exists :: " + user.getUserName());
		boolean flag = false;
		flag = userDAO.checkUserExists(user.getUserName());
		return flag;
	}
	
	@Override
	public boolean validateUserData(User user) {
		logger.info("Validate User Credentials :: " + user.getUserName());
		boolean flag = false;
		flag = userDAO.validateUserCredentials(user.getUserName(), user.getPassword());
		return flag;
	}

	@Override
	public List<Role> getRoleDetails() {
		List<Role> roles = userDAO.getRoleDetails();
		Role roleLabel = new Role();
		roleLabel.setRoleId(-1);
		roleLabel.setRoleDescription(" ---- Select Roles ----");
		roles.add(0, roleLabel);
		int count = 0;		
		for(Role role: roles) {
			if(role.getRoleId() == Roles.Admin.getRoleId()) {
				roles.remove(count);
				break;
			}
			count++;
		}
				logger.info("Roles :: size :: " + roles.size());
		return roles;
	}
}
