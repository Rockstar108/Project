package com.spring.healthcare.admin.service;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.healthcare.admin.dao.AWSUserTransactionDetailsDAO;
import com.spring.healthcare.admin.dao.UserTransactionDetailsDAO;
import com.spring.healthcare.admin.model.UserTransactionDetails;
import com.spring.healthcare.admin.utils.Crypto;
import com.spring.healthcare.admin.utils.CryptoUtility;

@Service
public class UserTransactionDetailsServiceImpl implements UserTransactionDetailsService {

	private Logger logger = Logger.getLogger(UserTransactionDetailsServiceImpl.class);
	
	@Autowired
	UserTransactionDetailsDAO userTransactionDAO;
	
	@Autowired
	AWSUserTransactionDetailsDAO awsUserTransactionDAO;
	
	public boolean isAWSEnabled;
	
	public UserTransactionDetailsServiceImpl() {
		
    	Properties props = new Properties();
    	try {
    		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("application.properties");
        	props.load(inputStream);
        	
    	} catch(Exception e) {
    		logger.error("Exception :: " + e.getMessage());
    	}
    	isAWSEnabled = Boolean.valueOf(props.getProperty("aws.enabled"));
	}
	

	@Override
	public void sendDetailsToPatient(String patientName, String userName,  String subject, String description) {
		logger.info("UserTransactionDetailsServiceImpl :: sendDetailsToPatient :: STARTS");
		try {
			String enCryptSubject = Crypto.encrypt(subject);
			String enCryptDescription = Crypto.encrypt(description);
			byte[] publicKey = getPublicKey();
			userTransactionDAO.sendDetailsToPatient(patientName, userName, enCryptSubject, enCryptDescription, publicKey);
			awsUserTransactionDAO.sendDetailsToPatient(patientName, userName, enCryptSubject, enCryptDescription, publicKey);
		} catch (Exception e) {
			logger.error("Exception in sendDetailsToPatient :: " + e.getMessage());
		}
	}

	@Override
	public void sendDetailsToDoctor(String docterName, String userName, String subject, String description, String transactionId) {
		
		try {			
			String publicKey = userTransactionDAO.getPublicKeyByTransactionId(transactionId);
			logger.info("transactionId :: " + transactionId + "publicKey :: " + publicKey);
			String enCryptSubject = Crypto.encrypt(subject);
			String enCryptDescription = Crypto.encrypt(description);
			userTransactionDAO.sendDetailsToDoctor(docterName, userName, enCryptSubject, enCryptDescription);
			awsUserTransactionDAO.sendDetailsToDoctor(docterName, userName, enCryptSubject, enCryptDescription);
						
		} catch (Exception e) {
			logger.error(" Exception in sendDetailsToDoctor :: " + e.getMessage());
		}
	}
	
	@Override
	public void sendSpecialistDetailsToDoctor(String doctorName, String specialistName, String patientName, String subject, String description) {
		try {
			String enCryptSubject = Crypto.encrypt(subject);
			String enCryptDescription = Crypto.encrypt(description);
			byte[] publicKey = getPublicKey();
			userTransactionDAO.sendSpecialistDetailsToDoctor(doctorName, specialistName, patientName, enCryptSubject, enCryptDescription, publicKey);
			awsUserTransactionDAO.sendSpecialistDetailsToDoctor(doctorName, specialistName, patientName, enCryptSubject, enCryptDescription, publicKey);
			
		} catch (Exception e) {
			logger.error("Exception in sendDetailsToPatient :: " + e.getMessage());
		}
	}
	
	@Override
	public List<UserTransactionDetails> getUserTransactionDetails(String userName) {
		List<UserTransactionDetails> transactionList = null;
		if(isAWSEnabled) {
			transactionList = awsUserTransactionDAO.getUserTransactionDetails(userName);
		} else {
			transactionList = userTransactionDAO.getUserTransactionDetails(userName);
		}		
		return transactionList;
	}
	
	@Override
	public List<UserTransactionDetails> getClientTransactionDetails(String userName, String patientName) {
		List<UserTransactionDetails> transactionList = null;
		if(isAWSEnabled) {
			transactionList = awsUserTransactionDAO.getClientTransactionDetails(userName, patientName);
		} else {
			transactionList = userTransactionDAO.getClientTransactionDetails(userName, patientName);
		}
		return transactionList;
	}
	
	@Override
	public List<UserTransactionDetails> getSpecialistTransactionDetails(String userName, String patientName) {
		List<UserTransactionDetails> transactionList = null;
		if(isAWSEnabled) { 
			transactionList = awsUserTransactionDAO.getSpecialistTransactionDetails(userName, patientName);
		} else {
			transactionList = userTransactionDAO.getSpecialistTransactionDetails(userName, patientName);
		}
		return transactionList;
	}

	private byte[] getPublicKey() {
		byte[] publicKey = CryptoUtility.getPublicKey();
		return publicKey;
	}
}
