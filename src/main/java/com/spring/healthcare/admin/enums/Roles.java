package com.spring.healthcare.admin.enums;

public enum Roles {
	
	Admin(1, "Administrator"), Patient(3, "Patient"), Doctor(4, "Doctor"), Specialist(5, "Specialist");
	
	private int roleId;
	
	private String roleCode;	
	
	public int getRoleId() {
		return roleId;
	}

	public String getRoleCode() {
		return roleCode;
	}

	private Roles (int roleId, String roleCode) {
		this.roleId = roleId;
		this.roleCode = roleCode;
	}

}
