package com.spring.healthcare.admin.dao;

import java.util.List;

public interface UserMappingDAO {
	
	public void insertUserFolderData(String userName, String folderPath);
	
	public void insertUserBlueToothData(String userName, String blueToothName);
	
	public List<String> getUserFolderMapDetails(String userName);
	
	public List<String> getUserBluetoothMapDetails(String userName);
	
	public void updateBluetoothDetails(String userName, String remark);
	
	public String getBlueToothRemarkByUserName(String userName);

}
