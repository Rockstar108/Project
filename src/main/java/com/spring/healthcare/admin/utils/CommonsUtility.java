package com.spring.healthcare.admin.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.spring.healthcare.admin.enums.Months;

public class CommonsUtility {
	
	private static Logger logger = Logger.getLogger(CommonsUtility.class);

	public static String getCurrentDate() {
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String createdDate = now.format(formatter);
		return createdDate;
	}

	public static String getCurrentDateTimeForFileName() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String createdDate = now.format(formatter);
		return createdDate;
	}

	public static int getCurrentMonth() {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		return month;
	}

//	public static String getMonthFromDate(String date) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String monthName = null;
//		Date parse = null;
//		try {
//			Calendar c = Calendar.getInstance();
//			parse = sdf.parse(date);
//			c.setTime(parse);
//			int monthValue = c.get(Calendar.MONTH) + 1;
//
//			for (Months month : Months.values()) {
//				if (monthValue == month.getData()) {
//					monthName = month.getMonth();
//					break;
//				}
//			}
//
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		return monthName;
//	}
	
	public static Date getFormattedDateTime(String inputDate) {
		Date date = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
			date = new Date(formatter.parse(inputDate).getTime());	
			
		} catch (Exception e) {
			logger.error("Error :: " + e.getMessage());
		}
		return date;
	}
	
	public static String generateRandomPassword() {
		// chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz";
        
        int n = 6;
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) {   
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index = (int)(AlphaNumericString.length() 
                        * Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        }   
        return sb.toString(); 		
	}
	


	public static String getOnlyDigits(String value) {
		Pattern pattern = Pattern.compile("[^0-9]");
		Matcher matcher = pattern.matcher(value);
		String number = matcher.replaceAll("");
		return number;
	}

	public static String getOnlyStringsDigits(String value) {
		Pattern pattern = Pattern.compile("[^a-zA-Z0-9_ ]+");
		Matcher matcher = pattern.matcher(value);
		String number = matcher.replaceAll("");
		return number;
	}

	public static int getRandomNumberInRange(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	public static String getCurrentDateTime() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String createdDate = now.format(formatter);
		return createdDate;
	}

	public static String convertDateFormat(String dateValue, String dtFromFormat, String dtToFormat) {

		DateTimeFormatter oldPattern = DateTimeFormatter.ofPattern(dtFromFormat);
		DateTimeFormatter newPattern = DateTimeFormatter.ofPattern(dtToFormat);

		LocalDate datetime = LocalDate.parse(dateValue, oldPattern);
		String output = datetime.format(newPattern);
		return output;
	}

	public static void main(String[] args) {
		// try {
		// String monthValue = getMonthFromDate("2011-11-05");
		// System.out.println(monthValue);
		// } catch (ParseException e) {
		// e.printStackTrace();
		// }
	}

}
