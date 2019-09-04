package com.spring.healthcare.admin.dao;

import java.util.List;

import com.spring.healthcare.admin.model.UserTransactionDetails;

public interface AWSUserTransactionDetailsDAO {
	
	public void sendDetailsToPatient(String patientName, String userName, String subject, String description, byte[] publicKey);

	public void sendDetailsToDoctor(String patientName, String userName, String subject, String description);

	public void sendSpecialistDetailsToDoctor(String doctorName, String specialistName, String patientName, String subject, String description, byte[] publicKey);

	public List<UserTransactionDetails> getUserTransactionDetails(String userName);
	
	public List<UserTransactionDetails> getClientTransactionDetails(String userName, String patientName);
	
	public List<UserTransactionDetails> getSpecialistTransactionDetails(String userName, String patientName);
	
	public String getPublicKeyByTransactionId(String transactionId); 

}
