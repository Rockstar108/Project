package com.spring.healthcare.admin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.healthcare.admin.model.User;

@Repository
public class UserDetailsDAOImpl implements UserDetailsDAO {
	
	private Logger logger = Logger.getLogger(UserDetailsDAOImpl.class);

	private static final String SEARCH_USER_BYNAME = "select u.*, r.ROLE_NAME from USER u, ROLE r where "
			+ " u.ROLE_ID = r.ROLE_ID and upper(u.USER_NAME) like ? and u.USER_STATUS = 1";
	
	private static final String GET_DOCTOR_DETAILS = "select * from USER where ROLE_ID = ?";
	
	private static final String GET_DOCTOR_DETAILS_BYID = "select * from USER where USER_ID = ?";
	
	private static final String GET_PATIENT_DETAILS_MAPPED = "select u.* from MAP_DOCTOR_PATIENT m, USER u "
					+ " where u.USER_ID = m.PATIENT_ID and m.DOCTOR_ID = ? ";
	
	private static final String GET_PATIENT_DETAILS_UNMAPPED = "select * from USER where ROLE_ID = 3 and USER_ID not in "
					+ " (select PATIENT_ID from MAP_DOCTOR_PATIENT) ";
	
	private static final String GET_PATIENT_DETAILS_BYNAME = "select USER_ID from USER where USER_NAME in ( :patientIds )";
	
	private static final String INSERT_DOCTOR_PATIENT_MAP = "insert into MAP_DOCTOR_PATIENT (DOCTOR_ID, PATIENT_ID, "
					+ " MAPPING_STATUS) values (?, ?, ?) ";
	
	private static final String DELETE_DOCTOR_PATIENT_MAP = "delete from MAP_DOCTOR_PATIENT where DOCTOR_ID = ?";
	
	private static final String INSERT_SPECIALIST_PATIENT_MAP = "insert into MAP_SPECIALIST_PATIENT (SPECIALIST_ID, PATIENT_ID, "
			+ " MAPPING_STATUS, CREATED_BY) values (?, ?, ?, ?) ";

	private static final String DELETE_SPECIALIST_PATIENT_MAP = "delete from MAP_SPECIALIST_PATIENT where SPECIALIST_ID = ?";
	
	private static final String GET_SPECIALIST_PATIENT_MAP = "select s.user_name sp_name, p.user_id, p.user_name, p.date_of_birth,  p.email, p.phone_number, msp.CREATED_BY "
			+ " from user p, user s, map_specialist_patient msp where p.user_id = msp.patient_id and msp.specialist_id = s.user_id and msp.created_by = ?";
	
	private static final String GET_SPECIALIST_PATIENT_DETAILS_MAPPED = "select m.CREATED_BY, u.* from MAP_SPECIALIST_PATIENT m, USER u "
			+ " where u.USER_ID = m.PATIENT_ID and m.SPECIALIST_ID = ? ";
	
	@Autowired
	@Qualifier("jdbcTemplate")
    JdbcTemplate jdbcTemplate;


	@Override
	public List<User> searchUserByName(String searchName, String roleId) {
		logger.info("UserDetailsDAOImpl :: searchUserByName :: List :: STARTS");
		Map<String,Object> params = new HashMap<String,Object>();
	    params.put("name", searchName.toUpperCase()+"%");
		String sqlQuery = SEARCH_USER_BYNAME;
		
		if(Integer.parseInt(roleId) != -1) sqlQuery += " and u.ROLE_ID = " + roleId;
		logger.info("SQL :: " + sqlQuery);
			
	    List<User> userList = jdbcTemplate.query(sqlQuery, new Object[] { searchName.toUpperCase() + "%" }, new RowMapper<User>() {
			
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				User user = new User();
				user.setUserId(rs.getInt("USER_ID"));
				user.setUserName(rs.getString("USER_NAME"));
				user.setFirstName(rs.getString("FIRST_NAME"));
				user.setLastName(rs.getString("LAST_NAME"));
				user.setDateOfBirth(rs.getString("DATE_OF_BIRTH"));
				user.setEmail(rs.getString("EMAIL"));
				user.setPhoneNumber(rs.getString("PHONE_NUMBER"));
				user.setMobileNumber(rs.getString("MOBILE_NUMBER"));
				user.setRoleId(rs.getInt("ROLE_ID"));
				user.setRoleName(rs.getString("ROLE_NAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setCity(rs.getString("CITY"));
				user.setState(rs.getString("STATE"));
				user.setCountry(rs.getString("COUNTRY"));
				user.setPinCode(rs.getString("PINCODE"));
				return user;
			}
		});		
	    logger.info("Result :: " + userList.size());
		logger.info("UserDetailsDAOImpl :: searchUserByName :: List :: ENDS");
		return userList;
	}
	

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<User> getDoctorDetails(String roleId) {
		logger.info("UserDetailsDAOImpl :: getDoctorDetails :: STARTS");
		List<User> userList = jdbcTemplate.query(GET_DOCTOR_DETAILS, new Object[] { roleId }, new RowMapper() {
			
			public User mapRow(ResultSet rs,  int rowNum) throws SQLException {
				
				User user = new User();
				user.setUserId(rs.getInt("USER_ID"));
				user.setUserName(rs.getString("USER_NAME"));
				user.setFirstName(rs.getString("FIRST_NAME"));
				user.setLastName(rs.getString("LAST_NAME"));
				user.setDateOfBirth(rs.getString("DATE_OF_BIRTH"));
				user.setEmail(rs.getString("EMAIL"));
				user.setPhoneNumber(rs.getString("PHONE_NUMBER"));
				user.setMobileNumber(rs.getString("MOBILE_NUMBER"));
				user.setRoleId(rs.getInt("ROLE_ID"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setCity(rs.getString("CITY"));
				user.setState(rs.getString("STATE"));
				user.setCountry(rs.getString("COUNTRY"));
				user.setPinCode(rs.getString("PINCODE"));
				return user;
			}
			
		});		
		logger.info("UserDetailsDAOImpl :: getDoctorDetails :: List :: ENDS");
		return userList;
	}
	
	@Override
	public User getDoctorDetailsById(String doctorId) {
		logger.info("UserDetailsDAOImpl :: getDoctorDetailsById :: STARTS");
		User user = jdbcTemplate.queryForObject(GET_DOCTOR_DETAILS_BYID, new Object[] { doctorId }, new RowMapper<User>() {
			
			public User mapRow(ResultSet rs,  int rowNum) throws SQLException {
				
				User user = new User();
				user.setUserId(rs.getInt("USER_ID"));
				user.setUserName(rs.getString("USER_NAME"));
				user.setFirstName(rs.getString("FIRST_NAME"));
				user.setLastName(rs.getString("LAST_NAME"));
				user.setDateOfBirth(rs.getString("DATE_OF_BIRTH"));
				user.setEmail(rs.getString("EMAIL"));
				user.setPhoneNumber(rs.getString("PHONE_NUMBER"));
				user.setMobileNumber(rs.getString("MOBILE_NUMBER"));
				user.setRoleId(rs.getInt("ROLE_ID"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setCity(rs.getString("CITY"));
				user.setState(rs.getString("STATE"));
				user.setCountry(rs.getString("COUNTRY"));
				user.setPinCode(rs.getString("PINCODE"));
				return user;
			}
			
		});		
		logger.info("UserDetailsDAOImpl :: getDoctorDetailsById :: List :: ENDS");
		return user;
	}
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<User> getPatientDetails(String doctorId) {
		logger.info("UserDetailsDAOImpl :: getPatientDetails :: STARTS");
		List<User> userList = jdbcTemplate.query(GET_PATIENT_DETAILS_MAPPED, new Object[] { doctorId }, new RowMapper() {
			
			public User mapRow(ResultSet rs,  int rowNum) throws SQLException {
				
				User user = new User();
				user.setUserId(rs.getInt("USER_ID"));
				user.setUserName(rs.getString("USER_NAME"));
				user.setFirstName(rs.getString("FIRST_NAME"));
				user.setLastName(rs.getString("LAST_NAME"));
				user.setDateOfBirth(rs.getString("DATE_OF_BIRTH"));
				user.setEmail(rs.getString("EMAIL"));
				user.setPhoneNumber(rs.getString("PHONE_NUMBER"));
				user.setMobileNumber(rs.getString("MOBILE_NUMBER"));
				user.setRoleId(rs.getInt("ROLE_ID"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setCity(rs.getString("CITY"));
				user.setState(rs.getString("STATE"));
				user.setCountry(rs.getString("COUNTRY"));
				user.setPinCode(rs.getString("PINCODE"));
				return user;
			}
			
		});		
		logger.info("UserDetailsDAOImpl :: getPatientDetails :: List :: ENDS");
		return userList;
	}
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<User> getSpecialistPatientDetails(String specialistId) {
		logger.info("UserDetailsDAOImpl :: getSpecialistPatientDetails :: STARTS");
		List<User> userList = jdbcTemplate.query(GET_SPECIALIST_PATIENT_DETAILS_MAPPED, new Object[] { specialistId }, new RowMapper() {
		
		public User mapRow(ResultSet rs,  int rowNum) throws SQLException {
			
			User user = new User();
			user.setUserId(rs.getInt("USER_ID"));
			user.setUserName(rs.getString("USER_NAME"));
			user.setFirstName(rs.getString("FIRST_NAME"));
			user.setLastName(rs.getString("LAST_NAME"));
			user.setDateOfBirth(rs.getString("DATE_OF_BIRTH"));
			user.setEmail(rs.getString("EMAIL"));
			user.setPhoneNumber(rs.getString("PHONE_NUMBER"));
			user.setMobileNumber(rs.getString("MOBILE_NUMBER"));
			user.setRoleId(rs.getInt("ROLE_ID"));
			user.setPassword(rs.getString("PASSWORD"));
			user.setCity(rs.getString("CITY"));
			user.setState(rs.getString("STATE"));
			user.setCountry(rs.getString("COUNTRY"));
			user.setPinCode(rs.getString("PINCODE"));
			user.setDoctorName(rs.getString("CREATED_BY"));
			return user;
		}
		
	});		
	logger.info("UserDetailsDAOImpl :: getSpecialistPatientDetails :: List :: ENDS");
	return userList;
}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<User> getSpecialistPatientMapDetails(String doctorName) {
		logger.info("UserDetailsDAOImpl :: getSpecialistPatientMapDetails :: STARTS");
		List<User> userList = jdbcTemplate.query(GET_SPECIALIST_PATIENT_MAP, new Object[] { doctorName }, new RowMapper() {
			
			public User mapRow(ResultSet rs,  int rowNum) throws SQLException {
				
				User user = new User();
				user.setSpecialistName(rs.getString("SP_NAME"));
				user.setDoctorName(rs.getString("CREATED_BY"));
				user.setUserId(rs.getInt("USER_ID"));
				user.setUserName(rs.getString("USER_NAME"));
				user.setDateOfBirth(rs.getString("DATE_OF_BIRTH"));
				user.setEmail(rs.getString("EMAIL"));
				user.setPhoneNumber(rs.getString("PHONE_NUMBER"));
				return user;
			}
			
		});		
		logger.info("UserDetailsDAOImpl :: getPatientDetailsNotMapped :: List :: ENDS");
		return userList;
	}
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<User> getPatientDetailsNotMapped() {
		logger.info("UserDetailsDAOImpl :: getPatientDetailsNotMapped :: STARTS");
		List<User> userList = jdbcTemplate.query(GET_PATIENT_DETAILS_UNMAPPED, new RowMapper() {
			
			public User mapRow(ResultSet rs,  int rowNum) throws SQLException {
				
				User user = new User();
				user.setUserId(rs.getInt("USER_ID"));
				user.setUserName(rs.getString("USER_NAME"));
				user.setFirstName(rs.getString("FIRST_NAME"));
				user.setLastName(rs.getString("LAST_NAME"));
				user.setDateOfBirth(rs.getString("DATE_OF_BIRTH"));
				user.setEmail(rs.getString("EMAIL"));
				user.setPhoneNumber(rs.getString("PHONE_NUMBER"));
				user.setMobileNumber(rs.getString("MOBILE_NUMBER"));
				user.setRoleId(rs.getInt("ROLE_ID"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setCity(rs.getString("CITY"));
				user.setState(rs.getString("STATE"));
				user.setCountry(rs.getString("COUNTRY"));
				user.setPinCode(rs.getString("PINCODE"));
				return user;
			}
			
		});		
		logger.info("UserDetailsDAOImpl :: getPatientDetailsNotMapped :: List :: ENDS");
		return userList;
	}

	@Override
	public List<String> getPatientDetailsById(List<String> patientIds) { 
		logger.info("UserDetailsDAOImpl :: getPatientDetailsById :: STARTS ");
		NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
		// Map namedParameters = Collections.singletonMap("patientIds", patientIds);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("patientIds", patientIds);
		
		List<String> userIds = namedJdbcTemplate.query(GET_PATIENT_DETAILS_BYNAME, parameters, new RowMapper<String>() {

				public String mapRow(ResultSet rs,  int rowNum) throws SQLException {
					String userId = rs.getString("USER_ID");
					return userId;
				}
		});
		
		logger.info("UserDetailsDAOImpl :: getPatientDetailsById :: ENDS ");
		return userIds;
	}

	@Override
	public void saveDoctorPatientMapDetails(String doctorId, String patientId) {
		jdbcTemplate.update(INSERT_DOCTOR_PATIENT_MAP, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
		        ps.setString(1, doctorId);
		        ps.setString(2, patientId);
		        ps.setInt(3, 1);		      }
		});						
	}
	
	@Override
	public void deletePatientMapDetailsByDoctorId(String doctorId) {
		jdbcTemplate.update(DELETE_DOCTOR_PATIENT_MAP, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
		        ps.setString(1, doctorId);    }
		});						
	}
	
	@Override
	public void saveSpecialistPatientMapDetails(String doctorName, String specialistId, String patientId) {
		jdbcTemplate.update(INSERT_SPECIALIST_PATIENT_MAP, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
		        ps.setString(1, specialistId);
		        ps.setString(2, patientId);
		        ps.setInt(3, 1);
		        ps.setString(4, doctorName);
		        
		    }
		});						
	}
	
	@Override
	public void deletePatientMapDetailsBySpecialistId(String specialistId) {
		jdbcTemplate.update(DELETE_SPECIALIST_PATIENT_MAP, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
		        ps.setString(1, specialistId);    }
		});						
	}

}
