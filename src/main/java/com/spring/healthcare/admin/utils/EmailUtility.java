package com.spring.healthcare.admin.utils;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class EmailUtility {
	
	private Logger logger = Logger.getLogger(EmailUtility.class);

    public void sendEmail(final String emailAddress, String subject, String body) {
    	
    	Properties props = new Properties();
    	try {
    		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("application.properties");
        	props.load(inputStream);
        	
    	} catch(Exception e) {
    		logger.error("Exception :: " + e.getMessage());
    	}
    	
    	final String userName = props.getProperty("email.userId");
    	final String password = props.getProperty("email.password");
    	
    	logger.info("EmailService :: Sending an Email for the User");

        Thread thread = new Thread() {
        	
            @Override
            public void run() {
            	logger.info("EmailService :: Inside the Thread Call :: Starts");
                SendMail m = new SendMail(userName, password);

                String[] toArr = {emailAddress};
                m.setTo(toArr);
                m.setFrom("Administrator");
                m.setSubject(subject);
                m.setBody(body);

                try {
                    if (m.send()) {
                    	logger.info("EmailService :: Email was sent successfully.");
                    } else {
                    	logger.info("EmailService :: Email was not sent.");
                    }
                } catch (Exception e) {
                	logger.error("EmailService :: Could not send email", e);
                }
                logger.info("EmailService :: End of the Thread Call :: Ends ");
            }
        };
        thread.start();
    }
}
