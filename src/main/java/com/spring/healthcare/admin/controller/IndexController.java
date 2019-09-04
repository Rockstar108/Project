package com.spring.healthcare.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class IndexController {
	
	private Logger logger = Logger.getLogger(IndexController.class);
	
//	@RequestMapping(value = "/login", method = RequestMethod.GET)
//	public String getIndexPage() {
//		logger.info("Inside the Index Page");
//		return "login";
//	}
	
	@RequestMapping(value = "/edituser", method = RequestMethod.GET)
	public String getEditUser() {
		logger.info("Inside the Edit UserPage");
		return "edituser";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String getHomePage() {
		logger.info("Inside the Home Page");
		return "home";
	}
	
	@RequestMapping(value = "/header", method = RequestMethod.GET)
	public String getHeaderPage() {
		logger.info("Inside the Header Page");
		return "header";
	}
	
	@RequestMapping(value = "/mapdoctorpatient", method = RequestMethod.GET)
	public String getMapDoctorPatient() {
		logger.info("Inside the Map Doctor Patient Page");
		return "mapdoctorpatient";
	}
	
	@RequestMapping(value = "/mapspecialistpatient", method = RequestMethod.GET)
	public String getMapSpecialistPatient() {
		logger.info("Inside the Map Specialist Patient Page");
		return "mapspecialistpatient";
	}	
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String getUserRegistration() {
		logger.info("Inside the Registration Page");
		return "registration";
	}
	
	@RequestMapping(value = "/adminhome", method = RequestMethod.GET)
	public String getAdminHomePage() {
		logger.info("Inside the Admin Home Page");
		return "adminhome";
	}
	
	@RequestMapping(value = "/userhome", method = RequestMethod.GET)
	public String getUserHomePage() {
		logger.info("Inside the User Home Page");
		return "userhome";
	}
	
	
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String getUserSuccessPage() {
		logger.info("Inside the Success Page");
		return "success";
	}

	@RequestMapping(value = "/authenticatesuccess", method = RequestMethod.GET)
	public String getUserAuthenticateSuccessPage() {
		logger.info("Inside the Authenticate Success Page");
		return "authenticatesuccess";
	}
	
	@RequestMapping(value = "/securityquestion", method = RequestMethod.GET)
	public String getSecurityQuestionsPage() {
		logger.info("Inside the Security Questions Page");
		return "securityquestion";
	}
	
	@RequestMapping(value = "/securityvalidate", method = RequestMethod.GET)
	public String getSecurityValidatePage() {
		logger.info("Inside the Security Details Page");
		return "securityvalidate";
	}
	
	@RequestMapping(value = "/securityvalidate/{userName}", method = RequestMethod.GET)
	public String getSecurityValidateForUploadPage(HttpServletRequest request, 
			@PathVariable("userName") String userName) {
		logger.info("Inside the Security Details Page");		
		HttpSession session = request.getSession(true);  	
		session.setAttribute("userName", userName);
		return "securityvalidate";
	}
	
	@RequestMapping(value = "/userkeyauthenticate", method = RequestMethod.GET)
	public String getUserSecuirtyAuthenticationPage() {
		logger.info("Inside the User Security Authentication Page");
		return "userkeyauthenticate";
	}
	
	@RequestMapping(value = "/mapfolder", method = RequestMethod.GET)
	public String getMapFolderPage() {
		logger.info("Inside the Map Folder Page");
		return "mapfolder";
	} 
		
	@RequestMapping(value = "/mapuserdetails", method = RequestMethod.GET)
	public String getMapUserDetailsPage() {
		logger.info("Inside the Map User Details Page");
		return "mapuserdetails";
	}
	
	@RequestMapping(value = "/generatekey", method = RequestMethod.GET)
	public String getGenerateKeyPage() {
		logger.info("Inside the Generate Key Page");
		return "generatekey";
	}
	
	@RequestMapping(value = "/clienthome", method = RequestMethod.GET)
	public String getPatientHomePage() {
		logger.info("Inside the Patient Home Page");
		return "clienthome";
	}
	
	@RequestMapping(value = "/doctorsenddata", method = RequestMethod.GET)
	public String getDoctorSendDetailsPage() {
		logger.info("Inside the Patient Details Page");
		return "doctorsenddata";
	}
	
	@RequestMapping(value = "/specialistsenddata", method = RequestMethod.GET)
	public String getSpecialistSendDetailsPage() {
		logger.info("Inside the Specialist Details Page");
		return "specialistsenddata";
	}	
	
	
	@RequestMapping(value = "/doctorhome", method = RequestMethod.GET)
	public String getDoctorHomePage() {
		logger.info("Inside the Doctor Home Page");
		return "doctorhome";
	}
	
	@RequestMapping(value = "/doctordetails", method = RequestMethod.GET)
	public String getUserDoctorDetailsPage() {
		logger.info("Inside the User Doctor Details Page");
		return "doctordetails";
	}
	
	@RequestMapping(value = "/doctorclientmap", method = RequestMethod.GET)
	public String getDoctorClientMapPage() {
		logger.info("Inside the Doctor Client Map Page");
		return "doctorclientmap";
	}	
	
	@RequestMapping(value = "/doctortransactions", method = RequestMethod.GET)
	public String getDoctorTransactionsPage() {
		logger.info("Inside the Doctor Transactions Page");
		return "doctortransactions";
	}	
	
	@RequestMapping(value = "/userdetails", method = RequestMethod.GET)
	public String getUserDetailsPage() {
		logger.info("Inside the User Details Page");
		return "userdetails";
	}
	
	@RequestMapping(value = "/clientsenddata", method = RequestMethod.GET)
	public String getClientSendDataPage() {
		logger.info("Inside the Client Send Details Page");
		return "clientsenddata";
	}
	
	@RequestMapping(value = "/doctorclienttransactions", method = RequestMethod.GET)
	public String getDoctorClientTransactionsPage() {
		logger.info("Inside the Doctor Client Transactions Page");
		return "doctorclienttransactions";
	}	
	
	
	@RequestMapping(value = "/specialisttransactions", method = RequestMethod.GET)
	public String getSpecialistTransactionsPage() {
		logger.info("Inside the Doctor Client Transactions Page");
		return "specialisttransactions";
	}	

	
	@RequestMapping(value = "/specialisthome", method = RequestMethod.GET)
	public String getSpecialistHomePage() {
		logger.info("Inside the Specialist Home Page");
		return "specialisthome";
	}
	
}