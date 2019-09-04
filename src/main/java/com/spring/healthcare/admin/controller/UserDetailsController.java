package com.spring.healthcare.admin.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.spring.healthcare.admin.model.User;
import com.spring.healthcare.admin.model.UserTransactionDetails;
import com.spring.healthcare.admin.service.UserDetailsService;
import com.spring.healthcare.admin.service.UserService;
import com.spring.healthcare.admin.service.UserTransactionDetailsService;

@RestController
public class UserDetailsController {
	
	private Logger logger = Logger.getLogger(UserDetailsController.class);
	 
    @Autowired
    UserDetailsService userdetailService;  
   
    @Autowired
    UserService userService;     
    
    @Autowired
    UserTransactionDetailsService userTransactionService;  
    
    @RequestMapping(value = "/userdetails/{roleId}/{searchName}", method = RequestMethod.GET)
    public ResponseEntity<List<User>> searchByUserName(@PathVariable("searchName") String searchName, 
    		@PathVariable("roleId") String roleId) {
    	logger.info("Searching for the User Name :: " + searchName + " :: Role Id ==> " + roleId);
        List<User> users = userdetailService.searchUserByName(searchName, roleId);
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }     
    
    @RequestMapping(value = "/userdetails/generatePassword/{userName}", method = RequestMethod.GET)
    public ResponseEntity<Void> generatePassword(@PathVariable("userName") String userName) {
    	logger.info("User Generate Password :: User Name :: " + userName);
        userdetailService.generatePassword(userName);
        return new ResponseEntity<Void>(HttpStatus.OK);
    } 
    
    @RequestMapping(value = "/userdetails/generateuserauthenticate/{userName}", method = RequestMethod.GET)
    public ResponseEntity<Void> generateUserkeyAuthentication(@PathVariable("userName") String userName) {
    	logger.info("User Bluetooth Map Details :: User Name :: " + userName);
        userdetailService.generateUserKeyAuthentication(userName);
        return new ResponseEntity<Void>(HttpStatus.OK);
    } 
    
    @RequestMapping(value = "/userdetails/doctordetails", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllDoctors() {
    	logger.info(" UserDetailsController :: listAllDoctors :: STARTS ");
        List<User> users = userdetailService.getDoctorDetails();
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    } 
    
    @RequestMapping(value = "/userdetails/specialistdetails", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllSpecialists() {
    	logger.info(" UserDetailsController :: listAllSpecialists :: STARTS ");
        List<User> users = userdetailService.getSpecialistDetails();
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    } 
    
    @RequestMapping(value = "/userdetails/doctordetailsbyid/{doctorId}", method = RequestMethod.GET)
    public ResponseEntity<String> getDoctorsDetailsById(@PathVariable("doctorId") String doctorId) {
    	logger.info(" UserDetailsController :: getDoctorsDetailsById :: STARTS ");
    	Gson gson = new Gson();
        User user = userdetailService.getDoctorDetailsById(doctorId);
        if(StringUtils.isBlank(user.getUserName())){
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<String>(gson.toJson(user.getUserName()), HttpStatus.OK);
    } 

    @RequestMapping(value = "/userdetails/patientdetails/{doctorId}", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllPatients(@PathVariable("doctorId") String doctorId) {
    	logger.info(" UserDetailsController :: listAllDoctors :: STARTS ");
    	// String doctorId = userService.findByName(doctorName).getUserId().toString();
        List<User> users = userdetailService.getPatientDetails(doctorId);
        
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/userdetails/specialistpatientdetails/{specialistId}", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllSpecialistPatients(@PathVariable("specialistId") String specialistId) {
    	logger.info(" UserDetailsController :: listAllSpecialistPatients :: STARTS ");
    	// String doctorId = userService.findByName(doctorName).getUserId().toString();
        List<User> users = userdetailService.getSpecialistPatientDetails(specialistId);
        
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    
    @RequestMapping(value = "/userdetails/patientdetailsnotmapped", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllPatientsNotMapped() {
    	logger.info(" UserDetailsController :: patientdetailsnotmapped :: STARTS ");
        List<User> users = userdetailService.getPatientDetailsNotMapped();
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/userdetails/savedoctorpatientmapdata/{doctorId}/{patients}", method = RequestMethod.GET)
    public ResponseEntity<Void> saveDoctorPatientMapDetails(@PathVariable("doctorId") String doctorId, 
    		@PathVariable("patients") String patients) {
    	logger.info(" UserDetailsController :: saveDoctorPatientMapDetails :: STARTS ");
    	userdetailService.saveDoctorPatientMapDetails(doctorId, patients);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
    @RequestMapping(value = "/userdetails/getspecialistpatientmapdata/{doctorName}", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getSpecialistPatientMapByDoctorName(@PathVariable("doctorName") String doctorName) {
    	logger.info(" UserDetailsController :: getSpecialistPatientMapByDoctorName :: STARTS ");
        List<User> users = userdetailService.getSpecialistPatientMapDetails(doctorName);
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/userdetails/savespecialistpatientmapdata/{doctorName}/{specialistId}/{patientId}", method = RequestMethod.GET)
    public ResponseEntity<Void> saveSpecialistPatientMapDetails(@PathVariable("doctorName") String doctorName,
    		@PathVariable("specialistId") String specialistId, @PathVariable("patientId") String patientId) {
    	logger.info(" UserDetailsController :: saveDoctorPatientMapDetails :: STARTS ");
    	userdetailService.saveSpecialistPatientMapDetails(doctorName, specialistId, patientId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
    @RequestMapping(value = "/user/senddetailstopatient/{description}/{subject}/{userName}/{patientName}", method = RequestMethod.GET)
    public ResponseEntity<Void> sendDetailsToPatient(@PathVariable("userName") String userName, 
    		@PathVariable("patientName") String patientName, @PathVariable("subject") String subject, 
    		@PathVariable("description") String description) {
    	logger.info(" UserDetailsController :: sendDetailsToPatient :: STARTS ");
    	userTransactionService.sendDetailsToPatient(patientName, userName, subject, description);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
    @RequestMapping(value = "/user/senddetailstodoctor/{description}/{subject}/{userName}/{doctorName}/{transactionId}", method = RequestMethod.GET)
    public ResponseEntity<Void> sendDetailsToDoctor(@PathVariable("userName") String userName, 
    		@PathVariable("doctorName") String doctorName, @PathVariable("subject") String subject, 
    		@PathVariable("description") String description, @PathVariable("transactionId") String transactionId) {
    	logger.info(" UserDetailsController :: sendDetailsToDoctor :: STARTS ");
    	userTransactionService.sendDetailsToDoctor(doctorName, userName, subject, description, transactionId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "/user/sendspecialistdetailstodoctor/{description}/{subject}/{specialistName}/{doctorName}/{patientName}", method = RequestMethod.GET)
    public ResponseEntity<Void> sendSpecialistDetailsToDoctor(@PathVariable("specialistName") String specialistName, 
    		@PathVariable("doctorName") String doctorName, @PathVariable("patientName") String patientName, 
    		@PathVariable("subject") String subject, @PathVariable("description") String description) {
    	logger.info(" UserDetailsController :: sendDetailsToDoctor :: STARTS ");
    	userTransactionService.sendSpecialistDetailsToDoctor(doctorName, specialistName, patientName, subject, description);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
    @RequestMapping(value = "/user/getdoctortransactiondetails/{userName}", method = RequestMethod.GET)
    public ResponseEntity<List<UserTransactionDetails>> getUserTransactionDetails(@PathVariable("userName") String userName) {
    	logger.info(" UserDetailsController :: getUserTransactionDetails :: STARTS ");
    	List<UserTransactionDetails> transactionList = userTransactionService.getUserTransactionDetails(userName);
    	if(transactionList.isEmpty()){
            return new ResponseEntity<List<UserTransactionDetails>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<UserTransactionDetails>>(transactionList, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/user/getclienttransactiondetails/{userName}/{patientName}", method = RequestMethod.GET)
    public ResponseEntity<List<UserTransactionDetails>> getClientTransactionDetails(
    		@PathVariable("userName") String userName, @PathVariable("patientName") String patientName) {
    	logger.info(" UserDetailsController :: getClientTransactionDetails :: STARTS ");
    	List<UserTransactionDetails> transactionList = userTransactionService.getClientTransactionDetails(userName, patientName);
    	if(transactionList.isEmpty()){
            return new ResponseEntity<List<UserTransactionDetails>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<UserTransactionDetails>>(transactionList, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/user/getspecialisttransactiondetails/{userName}/{patientName}", method = RequestMethod.GET)
    public ResponseEntity<List<UserTransactionDetails>> getSpecialistTransactionDetails(
    		@PathVariable("userName") String userName, @PathVariable("patientName") String patientName) {
    	logger.info(" UserDetailsController :: getClientTransactionDetails :: STARTS ");
    	List<UserTransactionDetails> transactionList = userTransactionService.getSpecialistTransactionDetails(userName, patientName);
    	if(transactionList.isEmpty()){
            return new ResponseEntity<List<UserTransactionDetails>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<UserTransactionDetails>>(transactionList, HttpStatus.OK);
    }
}
