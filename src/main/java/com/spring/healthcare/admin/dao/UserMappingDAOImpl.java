package com.spring.healthcare.admin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.healthcare.admin.utils.CommonsUtility;

@Repository
public class UserMappingDAOImpl implements UserMappingDAO {
	
	private Logger logger = Logger.getLogger(UserDetailsDAOImpl.class);
	
	private static final String GET_FOLDER_MAP_BYUSER = "select FOLDER_PATH from USER_FOLDER_MAP where USER_NAME = ?";
	
	private static final String GET_BLUETOOTH_MAP_BYUSER = "select BLUETOOTH_NAME from USER_BLUETOOTH_MAP where USER_NAME = ?";
	
	private static final String UPDATE_BLUETOOTH_REMARK = "update user_bluetooth_map set remark = ? where user_name = ?";

	private static final String SELECT_BLUETOOTH_REMARK = "select remark from user_bluetooth_map where user_name = ?";
	
	private static final String INSERT_BLUETOOTH = "insert into USER_BLUETOOTH_MAP (USER_NAME, BLUETOOTH_NAME, STATUS, CREATED_BY, CREATED_DATE)"
			+ "	 values (?,?,?,?,?)";
	
	private static final String INSERT_FOLDER = "insert into USER_FOLDER_MAP (USER_NAME, FOLDER_PATH, STATUS, CREATED_BY, CREATED_DATE)"
			+ "	 values (?,?,?,?,?)";
	
	
	@Autowired
	@Qualifier("jdbcTemplate")
    JdbcTemplate jdbcTemplate;

	@Override
	public void insertUserFolderData(String userName, String folderPath) {
		logger.info("UserMappingDAOImpl :: insertUserFolderData :: STARTS ");
		String currDateTime = CommonsUtility.getCurrentDateTime();
		jdbcTemplate.update(INSERT_FOLDER, userName, folderPath, 1, userName, currDateTime);
	}
	
	@Override
	public void insertUserBlueToothData(String userName, String blueToothName) {
		logger.info("UserTransactionDetailsDAOImpl :: insertUserBlueToothData :: STARTS ");
		String currDateTime = CommonsUtility.getCurrentDateTime();
		jdbcTemplate.update(INSERT_BLUETOOTH, userName, blueToothName, 1, userName, currDateTime);
	}
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<String> getUserFolderMapDetails(String userName) {
		logger.info("UserDetailsDAOImpl :: getUserFolderMapDetails :: STARTS");
		List<String> folderList = jdbcTemplate.query(GET_FOLDER_MAP_BYUSER, new Object[] { userName }, new RowMapper() {
			
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {

				String folderPath = rs.getString("FOLDER_PATH");
				return folderPath;
			}
		});		
		logger.info("UserDetailsDAOImpl :: getUserFolderMapDetails :: ENDS");
		return folderList;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<String> getUserBluetoothMapDetails(String userName) {
		logger.info("UserDetailsDAOImpl :: getUserBluetoothMapDetails :: STARTS");
		List<String> bluetoothList = jdbcTemplate.query(GET_BLUETOOTH_MAP_BYUSER, new Object[] { userName }, new RowMapper() {
			
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {

				String bluetoothName = rs.getString("BLUETOOTH_NAME");
				return bluetoothName;
			}
		});		
		logger.info("UserDetailsDAOImpl :: getUserBluetoothMapDetails :: ENDS");
		return bluetoothList;
	}
	
	@Override
	public void updateBluetoothDetails(String userName, String remark) {
		logger.info("UserDetailsDAOImpl :: updateBluetoothDetails :: STARTS");
		jdbcTemplate.update(UPDATE_BLUETOOTH_REMARK, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
		        ps.setString(1, remark);
		        ps.setString(2, userName);
		      }
		});
		logger.info("UserDetailsDAOImpl :: updateBluetoothDetails :: ENDS");
	}
	
	
	@Override
	public String getBlueToothRemarkByUserName(String userName) {
		logger.debug("UserDetailsDAOImpl :: getBlueToothRemarkByUserName :: STARTS ");
		String remark = jdbcTemplate.queryForObject(SELECT_BLUETOOTH_REMARK, new Object[] { userName}, String.class);
		logger.debug("UserDetailsDAOImpl :: validateUserCredentials :: ENDS ");
		return remark;
	}
	


}
