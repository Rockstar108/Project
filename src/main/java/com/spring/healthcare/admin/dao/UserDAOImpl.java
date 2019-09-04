package com.spring.healthcare.admin.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.healthcare.admin.model.Role;
import com.spring.healthcare.admin.model.User;
import com.spring.healthcare.admin.utils.CommonsUtility;

@Repository
public class UserDAOImpl implements UserDAO {
	
	private Logger logger = Logger.getLogger(UserDAOImpl.class);
	
	private static final String INSERT_USER = "insert into USER (USER_NAME, FIRST_NAME, LAST_NAME, DATE_OF_BIRTH, "  
			+ " EMAIL, PHONE_NUMBER, MOBILE_NUMBER, ROLE_ID, PASSWORD, CITY, STATE, COUNTRY, PINCODE) " 
			+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
	
	private static final String UPDATE_USER = "update USER set USER_NAME = ?, FIRST_NAME = ?, LAST_NAME = ?, "
			+ " EMAIL = ?, PHONE_NUMBER = ?, MOBILE_NUMBER = ?, CITY = ?, STATE = ?, COUNTRY = ?, PINCODE = ? where USER_ID = ? ";
	
	private static final String DELETE_USER = "update USER set USER_STATUS = ? where USER_NAME = ? ";
	
	private static final String GET_SECURITYKEY_BYUSER = "select SECURITY_KEY from USER where  USER_NAME = ?";
	
	private static final String UPDATE_SECURITY_KEY = "update USER set SECURITY_KEY = ? where USER_NAME = ? ";
	
	private static final String SELECT_USER = "select * from USER where USER_NAME = ? and USER_STATUS = 1";
	
	private static final String CHECK_USER_EXIST = "select count(*) from USER where UPPER(USER_NAME) = ?";
	
	private static final String VALIDATE_USER_DATA = "select count(*) from USER where UPPER(USER_NAME) = ? and UPPER(PASSWORD) = ?";
	
	private static final String SELECT_ROLES = "select * from ROLE where ROLE_STATUS = 1";
	
	@Autowired
	@Qualifier("jdbcTemplate")
    JdbcTemplate jdbcTemplate;
	
	@Override
	public void batchUserInsert(List<User> userList) {
		logger.info("UserDetailsDAOImpl :: batchUserInsert :: STARTS");		
		
		try {
			Stream<User> userDetailsStream = userList.parallelStream();		
			userDetailsStream.forEach(user -> {
				jdbcTemplate.update(INSERT_USER, new PreparedStatementSetter() {
					
					public void setValues(PreparedStatement ps) throws SQLException {
				        ps.setString(1, user.getUserName());
				        ps.setString(2, user.getFirstName());
				        ps.setString(3, user.getLastName());
				        ps.setDate(4, CommonsUtility.getFormattedDateTime(user.getDateOfBirth()));  
				        ps.setString(5, user.getEmail());
				        ps.setString(6, user.getPhoneNumber());
				        ps.setString(7, user.getMobileNumber());
				        ps.setInt(8, user.getRoleId());
				        ps.setString(9, user.getPassword());
				        ps.setString(10, user.getCity());
				        ps.setString(11, user.getState());
				        ps.setString(12, user.getCountry());
				        ps.setString(13, user.getPinCode());
				        
				      }
				});
			});
			
		} catch (Exception e) {
			logger.error("Exception :: " + e.getMessage());
		}
		logger.info("UserDetailsDAOImpl :: batchUserInsert :: ENDS");		
	}

	@Override
	public void createUser(User user) {
		logger.info("UserDetailsDAOImpl :: createUser :: STARTS");		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = new Date(formatter.parse(user.getDateOfBirth()).getTime());			
			jdbcTemplate.update(INSERT_USER, new PreparedStatementSetter() {
				public void setValues(PreparedStatement ps) throws SQLException {
			        ps.setString(1, user.getUserName());
			        ps.setString(2, user.getFirstName());
			        ps.setString(3, user.getLastName());
			        ps.setDate(4, date);
			        ps.setString(5, user.getEmail());
			        ps.setString(6, user.getPhoneNumber());
			        ps.setString(7, user.getMobileNumber());
			        ps.setInt(8, user.getRoleId());
			        ps.setString(9, user.getPassword());
			        ps.setString(10, user.getCity());
			        ps.setString(11, user.getState());
			        ps.setString(12, user.getCountry());
			        ps.setString(13, user.getPinCode());
			      }
			});						
			
		} catch (Exception e) {
			logger.error("Exception :: " + e.getMessage());
		}
		logger.info("UserDetailsDAOImpl :: createUser :: ENDS");		
	}

	@Override
	public void modifyUser(User user) {
		logger.info("UserDetailsDAOImpl :: modifyUser :: STARTS");
		jdbcTemplate.update(UPDATE_USER, new PreparedStatementSetter() {
			
			public void setValues(PreparedStatement ps) throws SQLException {
		        ps.setString(1, user.getUserName());
		        ps.setString(2, user.getFirstName());
		        ps.setString(3, user.getLastName());
		        ps.setString(4, user.getEmail());
		        ps.setString(5, user.getPhoneNumber());
		        ps.setString(6, user.getMobileNumber());
		        ps.setString(7, user.getCity());
		        ps.setString(8, user.getState());
		        ps.setString(9, user.getCountry());
		        ps.setString(10, user.getPinCode());
		        ps.setInt(11, user.getUserId());
		      }
		});
		logger.info("UserDetailsDAOImpl :: modifyUser :: ENDS");		
	}
	
	@Override
	public void updateSecurityKey(String userName, String securityKey) {
		logger.info("UserDetailsDAOImpl :: updateSecurityKey :: STARTS");
		jdbcTemplate.update(UPDATE_SECURITY_KEY, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
		        ps.setString(1, securityKey);
		        ps.setString(2, userName);
		      }
		});
		logger.info("UserDetailsDAOImpl :: updateSecurityKey :: ENDS");
	}

	@Override
	public User getUserDetails(String userName) {
		logger.info("UserDetailsDAOImpl :: getUserDetails :: List :: STARTS");
		User user = jdbcTemplate.queryForObject(SELECT_USER, new Object[] { userName }, new RowMapper<User>() {
			
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
				user.setPassword(rs.getString("PASSWORD"));
				user.setCity(rs.getString("CITY"));
				user.setState(rs.getString("STATE"));
				user.setCountry(rs.getString("COUNTRY"));
				user.setPinCode(rs.getString("PINCODE"));
				return user;
			}
		});		
		logger.info("UserDetailsDAOImpl :: getUserDetails :: List :: ENDS");
		return user;
	}
	
	@Override
	public void deleteUser(Integer userId) {
		logger.info("UserDetailsDAOImpl :: deleteUser :: STARTS");
		jdbcTemplate.update(DELETE_USER, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
		        ps.setInt(1, 0);
		        ps.setInt(2, userId);
		      }
		});
		logger.info("UserDetailsDAOImpl :: deleteUser :: ENDS");
	}
	
	@Override
	public void deleteUser(String userName) {
		logger.info("UserDetailsDAOImpl :: deleteUser :: STARTS");
		jdbcTemplate.update(DELETE_USER, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
		        ps.setInt(1, 0);
		        ps.setString(2, userName);
		      }
		});
		logger.info("UserDetailsDAOImpl :: deleteUser :: ENDS");
	}



	@Override
	public boolean checkUserExists(String userName) {
		logger.info("UserDetailsDAOImpl :: checkUserExists :: STARTS ");
		boolean flag = false;
		Integer count = jdbcTemplate.queryForObject( CHECK_USER_EXIST, new Object[] { userName.toUpperCase() }, Integer.class);
		if(count > 0) flag = true;
		logger.info("UserDetailsDAOImpl :: checkUserExists :: ENDS ");
		return flag;
	}
	
	@Override
	public boolean validateUserCredentials(String userName, String password) {
		logger.info("UserDetailsDAOImpl :: validateUserCredentials :: STARTS ");
		boolean flag = false;
		Integer count = jdbcTemplate.queryForObject( VALIDATE_USER_DATA, new Object[] { userName.toUpperCase(), password.toUpperCase() }, Integer.class);
		if(count > 0) flag = true;
		logger.info("UserDetailsDAOImpl :: validateUserCredentials :: ENDS ");
		return flag;
	}
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })	
	public List<Role> getRoleDetails() {
		logger.info("UserDetailsDAOImpl :: getRoleDetails :: STARTS ");
		List<Role> roles = jdbcTemplate.query(SELECT_ROLES, new RowMapper() {
			
			public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Role role = new Role();
				role.setRoleId(rs.getInt("ROLE_ID"));
				role.setRoleName(rs.getString("ROLE_NAME"));
				role.setRoleDescription(rs.getString("ROLE_DESCRIPTION"));
				return role;
			}
		});	 		
		logger.info("UserDetailsDAOImpl :: getRoleDetails :: ENDS ");
		return roles;
	}

	@Override
	public String getUserSecurityKey(String userName) {
		logger.info("UserDAOImpl :: getUserSecurityKey :: STARTS ");
		String securityKey = jdbcTemplate.queryForObject(GET_SECURITYKEY_BYUSER, new Object[] { userName }, String.class);
		logger.info("UserDAOImpl :: getUserSecurityKey :: ENDS ");
		return securityKey;
	}
}
