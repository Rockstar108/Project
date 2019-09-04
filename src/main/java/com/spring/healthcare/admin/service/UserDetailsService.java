package com.spring.healthcare.admin.service;

import java.util.List;

import com.spring.healthcare.admin.model.User;

public interface UserDetailsService {
	
	public List<User> searchUserByName(String searchByName, String roleId);
	
	public void generatePassword(String userName);
	
	public void generateUserKeyAuthentication(String userName); 
	
	public List<User> getDoctorDetails();	

	public List<User> getSpecialistDetails();
	
	public User getDoctorDetailsById(String doctorId);
	
	public List<User> getPatientDetails(String doctorId);	
	
	public List<User> getSpecialistPatientDetails(String doctorId);	
	
	public List<User> getPatientDetailsNotMapped();
	
	public List<User> getSpecialistPatientMapDetails(String doctorName);
	
	public void saveDoctorPatientMapDetails(String doctorId, String patients);
	
	public void saveSpecialistPatientMapDetails(String doctorName, String specialistId, String patients);

}
