package com.spring.healthcare.admin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.healthcare.admin.model.UserTransactionDetails;
import com.spring.healthcare.admin.utils.CommonsUtility;
import com.spring.healthcare.admin.utils.Crypto;

@Repository
public class UserTransactionDetailsDAOimpl implements UserTransactionDetailsDAO {
	
	private Logger logger = Logger.getLogger(UserTransactionDetailsDAOimpl.class);
	
	private static final String INSERT_TRANSACTION_DETAILS = "insert into DOCTOR_PATIENT_TRANSACTION_DETAILS "
			+ " (USER_NAME, TRANSACTION_TYPE, SUBJECT, DESCRIPTION, PUBLIC_KEY, CREATED_BY, CREATED_DATE) values (?,?,?,?,?,?,?)";
	
	private static final String INSERT_SPECIALIST_TRANSACTION_DETAILS = "insert into DOCTOR_PATIENT_TRANSACTION_DETAILS "
			+ " (USER_NAME, TRANSACTION_TYPE, SUBJECT, DESCRIPTION, PUBLIC_KEY, CREATED_BY, CREATED_DATE, PATIENT_NAME )"
			+ " values (?,?,?,?,?,?,?,?)";
	
	private static final String SELECT_TRANSACTION_DETAILS = "select * from DOCTOR_PATIENT_TRANSACTION_DETAILS where USER_NAME = ?";
	
	private static final String SELECT_CLIENT_TRANSACTION_DETAILS = "select * from DOCTOR_PATIENT_TRANSACTION_DETAILS "
			+ " where USER_NAME = ? and CREATED_BY = ?";
	
	private static final String SELECT_SPECIALIST_TRANSACTION_DETAILS = "select * from DOCTOR_PATIENT_TRANSACTION_DETAILS "
			+ " where USER_NAME = ? and PATIENT_NAME = ?";
	
	private static final String GET_PUBLICKEY_BYTRASACTION_ID = "select PUBLIC_KEY from DOCTOR_PATIENT_TRANSACTION_DETAILS where "
			+ " TRANSACTION_DETAILS_ID = ?";
	
	@Autowired
	@Qualifier("jdbcTemplate")
	JdbcTemplate jdbcTemplate;

	@Override
	public void sendDetailsToPatient(String patientName, String userName, String subject, String description, byte[] publicKey) {
		logger.debug("UserTransactionDetailsDAOimpl :: sendDetailsToPatient :: STARTS ");
		String createdDateTime = CommonsUtility.getCurrentDateTime();
		jdbcTemplate.update(INSERT_TRANSACTION_DETAILS, patientName, "DOCTOR_TO_PATIENT", subject, description, publicKey, userName, createdDateTime);
	}

	@Override
	public void sendDetailsToDoctor(String doctorName, String userName, String subject, String description) {
		logger.debug("UserTransactionDetailsDAOimpl :: sendDetailsToDoctor :: STARTS ");
		String createdDateTime = CommonsUtility.getCurrentDateTime();
		jdbcTemplate.update(INSERT_TRANSACTION_DETAILS, doctorName, "PATIENT_TO_DOCTOR", subject, description, 
				null, userName, createdDateTime);		
	}
	
	@Override
	public void sendSpecialistDetailsToDoctor(String doctorName, String specialistName, String patientName, String subject, String description, byte[] publicKey) {
		logger.debug("UserTransactionDetailsDAOimpl :: sendSpecialistDetailsToDoctor :: STARTS ");
		String createdDateTime = CommonsUtility.getCurrentDateTime();
		jdbcTemplate.update(INSERT_SPECIALIST_TRANSACTION_DETAILS, doctorName, "SPECIALIST_TO_DOCTOR", subject, description, 
				null, specialistName, createdDateTime, patientName);		
	}
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })	
	public List<UserTransactionDetails> getUserTransactionDetails(String userName) {
		logger.info("UserTransactionDetailsDAOImpl :: getUserTransactionDetails :: STARTS ");
		List<UserTransactionDetails> transactionList = jdbcTemplate.query(SELECT_TRANSACTION_DETAILS, new Object[] { userName },  new RowMapper() {
			
			public UserTransactionDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				UserTransactionDetails userTransaction = new UserTransactionDetails();
				userTransaction.setTransactionDetailsId(rs.getInt("TRANSACTION_DETAILS_ID"));
				userTransaction.setUserName(rs.getString("USER_NAME"));
				userTransaction.setTransactionType(rs.getString("TRANSACTION_TYPE"));
				userTransaction.setSubject(Crypto.decrypt(rs.getString("SUBJECT")));
				userTransaction.setDescription(Crypto.decrypt(rs.getString("DESCRIPTION")));
				userTransaction.setPublicKey(rs.getString("PUBLIC_KEY"));
				userTransaction.setCreatedBy(rs.getString("CREATED_BY"));
				userTransaction.setCreatedDate(rs.getString("CREATED_DATE"));
				return userTransaction;
			}
		});	 		
		logger.info("UserTransactionDetailsDAOImpl :: getUserTransactionDetails :: ENDS ");
		return transactionList;	
	}
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })	
	public List<UserTransactionDetails> getClientTransactionDetails(String userName, String patientName) {
		logger.info("UserTransactionDetailsDAOImpl :: getClientTransactionDetails :: STARTS ");
		List<UserTransactionDetails> transactionList = jdbcTemplate.query(SELECT_CLIENT_TRANSACTION_DETAILS, new Object[] { userName, patientName },  new RowMapper() {
			
			public UserTransactionDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				UserTransactionDetails userTransaction = new UserTransactionDetails();
				userTransaction.setTransactionDetailsId(rs.getInt("TRANSACTION_DETAILS_ID"));
				userTransaction.setUserName(rs.getString("USER_NAME"));
				userTransaction.setTransactionType(rs.getString("TRANSACTION_TYPE"));
				userTransaction.setSubject(Crypto.decrypt(rs.getString("SUBJECT")));
				userTransaction.setDescription(Crypto.decrypt(rs.getString("DESCRIPTION")));
				userTransaction.setPublicKey(rs.getString("PUBLIC_KEY"));
				userTransaction.setCreatedBy(rs.getString("CREATED_BY"));
				userTransaction.setCreatedDate(rs.getString("CREATED_DATE"));
				return userTransaction;
			}
		});	 		
		logger.info("UserTransactionDetailsDAOImpl :: getClientTransactionDetails :: ENDS ");
		return transactionList;	
	}
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })	
	public List<UserTransactionDetails> getSpecialistTransactionDetails(String userName, String patientName) {
		logger.info("UserTransactionDetailsDAOImpl :: getClientTransactionDetails :: STARTS ");
		List<UserTransactionDetails> transactionList = jdbcTemplate.query(SELECT_SPECIALIST_TRANSACTION_DETAILS, new Object[] { userName, patientName },  new RowMapper() {
			
			public UserTransactionDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				UserTransactionDetails userTransaction = new UserTransactionDetails();
				userTransaction.setTransactionDetailsId(rs.getInt("TRANSACTION_DETAILS_ID"));
				userTransaction.setUserName(rs.getString("USER_NAME"));
				userTransaction.setTransactionType(rs.getString("TRANSACTION_TYPE"));
				userTransaction.setSubject(Crypto.decrypt(rs.getString("SUBJECT")));
				userTransaction.setDescription(Crypto.decrypt(rs.getString("DESCRIPTION")));
				userTransaction.setPublicKey(rs.getString("PUBLIC_KEY"));
				userTransaction.setCreatedBy(rs.getString("CREATED_BY"));
				userTransaction.setCreatedDate(rs.getString("CREATED_DATE"));
				return userTransaction;
			}
		});	 		
		logger.info("UserTransactionDetailsDAOImpl :: getClientTransactionDetails :: ENDS ");
		return transactionList;	
	}

	@Override
	public String getPublicKeyByTransactionId(String transactionId) {
		logger.info("UserTransactionDetailsDAOImpl :: getPublicKeyByTransactionId :: STARTS ");
		String publicKey = (String) jdbcTemplate.queryForObject(GET_PUBLICKEY_BYTRASACTION_ID, new Object[] { transactionId },  String.class); 		
		logger.info("UserTransactionDetailsDAOImpl :: getPublicKeyByTransactionId :: ENDS ");
		return publicKey;	
	}	
}
