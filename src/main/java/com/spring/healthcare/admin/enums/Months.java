package com.spring.healthcare.admin.enums;

public enum Months {

	JANUARY(1, "January"), FEBRUARY(2, "February"), MARCH(3, "March"), APRIL(4, "April"), MAY(5, "May"),
	JUNE(6, "June"), JULY(7, "July"), AUGUST(8, "August"), SEPTEMBER(9, "September"), 
	OCTOBER(10, "October"), NOVEMBER(11, "November"), DECEMBER(12, "December");
	
	private int data;
	private String month;
	
	private Months(int data, String month) {
		this.data = data;
		this.month = month;
	}

	public int getData() {
		return data;
	}

	public String getMonth() {
		return month;
	}	
	
}
