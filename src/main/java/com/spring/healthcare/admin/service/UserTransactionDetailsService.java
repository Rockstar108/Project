package com.spring.healthcare.admin.service;

import java.util.List;

import com.spring.healthcare.admin.model.UserTransactionDetails;

public interface UserTransactionDetailsService {
	
	public void sendDetailsToPatient(String patientName, String userName,  String subject, String description);
	
	public void sendDetailsToDoctor(String docterName, String userName, String subject, String description, String transactionId);

	public void sendSpecialistDetailsToDoctor(String docterName, String specialistName, String patientName, String subject, String description);
	
	public List<UserTransactionDetails> getUserTransactionDetails(String userName);
	
	public List<UserTransactionDetails> getClientTransactionDetails(String userName, String patientName);
	
	public List<UserTransactionDetails> getSpecialistTransactionDetails(String userName, String patientName);
	
}
