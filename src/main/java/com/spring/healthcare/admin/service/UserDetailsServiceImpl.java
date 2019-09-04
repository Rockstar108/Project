package com.spring.healthcare.admin.service;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.healthcare.admin.dao.UserDAO;
import com.spring.healthcare.admin.dao.UserDetailsDAO;
import com.spring.healthcare.admin.model.User;
import com.spring.healthcare.admin.utils.CommonsUtility;
import com.spring.healthcare.admin.utils.EmailUtility;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private Logger logger = Logger.getLogger(UserDetailsServiceImpl.class);
	
	@Autowired
	UserDetailsDAO userdetailsDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Override
	public List<User> searchUserByName(String searchByName, String roleId) {
		List<User> userList = userdetailsDAO.searchUserByName(searchByName, roleId);
		return userList;
	}
	
	@Override
	public void generateUserKeyAuthentication(String userName) {
		
//		User user = userdetailsDAO.getUserDetails(userName);
//		String subject = "Notification for User Authentication to Enable/Disable Folder access";
//
//		StringBuffer stbBody = new StringBuffer();
//		stbBody.append("Hi " + userName + "\n" );
//		stbBody.append("Please use the URL below to Authenticate the Transaction \n\n" );
//		stbBody.append("<a href='http://localhost:8080/springbluetooth/securityvalidate/"+ userName + "'> Click Here <a> \n\n\n" );
//		stbBody.append("Thanks, \n" );
//		stbBody.append("Administrator\n" );
//		String body = stbBody.toString();
//		EmailUtility email = new EmailUtility();
//		email.sendEmail(user.getEmail(), subject, body);	
	}

	@Override
	public void generatePassword(String userName) {
		User user = userDAO.getUserDetails(userName);
		String subject = "Notification for User Generated Passward for - " + userName.toUpperCase();
		
		String password = CommonsUtility.generateRandomPassword();

		StringBuffer stbBody = new StringBuffer();
		stbBody.append("Hi " + userName.toUpperCase() + "\n\n" );
		stbBody.append("Please use the below User Generated Password to login to the Application \n\n" );
		stbBody.append("New Password - " + password  + " \n\n" );
		stbBody.append("Thanks, \n" );
		stbBody.append("Administrator\n" );
		String body = stbBody.toString();
		
		EmailUtility email = new EmailUtility();
		email.sendEmail(user.getEmail(), subject, body);			
	}
	
	@Override
	public List<User> getDoctorDetails() {
		logger.info("UserDetailsServiceImpl :: getDoctorDetails :: STARTS");
		List<User> userList = userdetailsDAO.getDoctorDetails("4");
		User userLabel = new User();
		userLabel.setUserId(-1);
		userLabel.setUserName(" ---- Select Doctors ----");
		userList.add(0, userLabel);
		logger.info("Doctors :: size :: " + userList.size());
		return userList;
	}

	@Override
	public List<User> getSpecialistDetails() {
		logger.info("UserDetailsServiceImpl :: getSpecialistDetails :: STARTS");
		List<User> userList = userdetailsDAO.getDoctorDetails("5");
		User userLabel = new User();
		userLabel.setUserId(-1);
		userLabel.setUserName(" ---- Select Specialist ----");
		userList.add(0, userLabel);
		logger.info("Specialist :: size :: " + userList.size());
		return userList;
	}

	
	@Override
	public User getDoctorDetailsById(String doctorId) {
		logger.info("UserDetailsServiceImpl :: getDoctorDetailsById :: STARTS");
		User user = userdetailsDAO.getDoctorDetailsById(doctorId);
		return user;
	}

	@Override
	public List<User> getPatientDetails(String doctorId) {
		logger.info("UserDetailsServiceImpl :: getDoctorDetails :: STARTS");
		List<User> userList = userdetailsDAO.getPatientDetails(doctorId);
		logger.info("Patient Details :: size :: " + userList.size());
//		User userLabel = new User();
//		userLabel.setUserId(-1);
//		userLabel.setUserName(" ---- Select Patients ----");
//		userList.add(0, userLabel);
		return userList;
	}
	
	@Override
	public List<User> getSpecialistPatientDetails(String specialistId) {
		logger.info("UserDetailsServiceImpl :: getDoctorDetails :: STARTS");
		List<User> userList = userdetailsDAO.getSpecialistPatientDetails(specialistId);
		logger.info("Patient Details :: size :: " + userList.size());
		return userList;
	}
	
	@Override
	public List<User> getPatientDetailsNotMapped() {
		logger.info("UserDetailsServiceImpl :: getPatientDetailsNotMapped :: STARTS");
		List<User> userList = userdetailsDAO.getPatientDetailsNotMapped();
		logger.info("Patient Details Not Mapped :: size :: " + userList.size());
		return userList;
	}

	@Override
	public void saveDoctorPatientMapDetails(String doctorId, String patients) {
		logger.info("UserDetailsServiceImpl :: saveDoctorPatientMapDetails :: STARTS");
		List<String> patientList = Arrays.asList(patients.split("\\s*,\\s*"));
		List<String> patientIds = userdetailsDAO.getPatientDetailsById(patientList);
		userdetailsDAO.deletePatientMapDetailsByDoctorId(doctorId);
		
		patientIds.forEach(p -> {
			userdetailsDAO.saveDoctorPatientMapDetails(doctorId, p);
		});
	}
	
	@Override
	public void saveSpecialistPatientMapDetails(String doctorName, String specialistId, String patientId) {
		logger.info("UserDetailsServiceImpl :: saveDoctorPatientMapDetails :: STARTS");
		userdetailsDAO.saveSpecialistPatientMapDetails(doctorName, specialistId, patientId);
	}

	@Override
	public List<User> getSpecialistPatientMapDetails(String doctorName) {
		List<User> userMapList = userdetailsDAO.getSpecialistPatientMapDetails(doctorName);
		return userMapList;
	}

}
