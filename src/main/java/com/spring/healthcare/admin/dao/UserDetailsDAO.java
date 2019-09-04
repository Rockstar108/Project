package com.spring.healthcare.admin.dao;

import java.util.List;

import com.spring.healthcare.admin.model.User;

public interface UserDetailsDAO {
	
	public List<User> searchUserByName(String searchName, String roleId);

	public List<User> getDoctorDetails(String roleId);
	
	public User getDoctorDetailsById(String doctorId);
	
	public List<User> getPatientDetails(String doctorId);

	public List<User> getSpecialistPatientDetails(String specialistId);
	
	public List<User> getSpecialistPatientMapDetails(String doctorName);
	
	public List<User> getPatientDetailsNotMapped();
	
	public List<String> getPatientDetailsById(List<String> patientIds);
	
	public void saveDoctorPatientMapDetails(String doctorId, String patients);
	
	public void deletePatientMapDetailsByDoctorId(String doctorId);
	
	public void saveSpecialistPatientMapDetails(String doctorName, String specialistId, String patientId);
	
	public void deletePatientMapDetailsBySpecialistId(String specialistId);
	
}
