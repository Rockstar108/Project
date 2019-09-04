package com.spring.healthcare.admin.controller;
 
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.spring.healthcare.admin.model.Role;
import com.spring.healthcare.admin.model.User;
import com.spring.healthcare.admin.service.UserService;
 
@RestController
public class UserController {
	
	private Logger logger = Logger.getLogger(UserController.class);
 
    @Autowired
    UserService userService;  
    
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
    	
        List<User> users = userService.findAllUsers();
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    } 

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        logger.info("Fetching User with id " + id);
        User user = userService.findById(id);
        if (user == null) {
        	logger.info("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/user/userName/{userName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserByName(@PathVariable("userName") String userName) {
        logger.info("Fetching User with userName" + userName);
        User user = userService.findByName(userName);
        if (user == null) {
        	logger.info("User with userName :: " + userName + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/user/getuserdetailbyid/{userName}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserDetailsByName(@PathVariable("userName") String userName) {
    	logger.info("Getting the User Name :: " + userName);
        User user = userService.findByName(userName);
        if(user == null){
            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }        
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
     
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody User user,    UriComponentsBuilder ucBuilder) {
    	logger.info("Creating User " + user.getUserName());
        if (userService.isUserExist(user)) {
        	logger.info("A User with name " + user.getUserName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        userService.saveUser(user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
    @RequestMapping(value = "/user/edit", method = RequestMethod.POST)
    public ResponseEntity<Void> updateUser(@RequestBody User user,    UriComponentsBuilder ucBuilder) {
    	logger.info("Updating the   User " + user.getUserName());
        userService.updateUser(user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
    @RequestMapping(value = "/userdetails/remove/{userName}", method = RequestMethod.GET)
    public ResponseEntity<Void> removeByUserName(@PathVariable("userName") String userName) {
    	logger.info("Removing the User Name :: " + userName);
        userService.removeUserByName(userName);
        return new ResponseEntity<Void>(HttpStatus.OK);
    } 
 
    @RequestMapping(value = "/user/validateuser", method = RequestMethod.POST)
    public ResponseEntity<Void> validateUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
    	logger.info("UserController ::  validateUser :: STARTS " );
        if (userService.validateUserData(user)) {
        	logger.info("A User with name " + user.getUserName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } 
        return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }
    
    @RequestMapping(value = "/user/roles", method = RequestMethod.GET)
    public ResponseEntity<List<Role>> listAllRoles() {
    	logger.info("UserController ::  listAllRoles :: STARTS " );
        List<Role> roles = userService.getRoleDetails();
        if(roles.isEmpty()){
            return new ResponseEntity<List<Role>>(HttpStatus.NO_CONTENT); 
        }
        return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
    } 
    
    @RequestMapping(value = "/user/userrole/{userName}", method = RequestMethod.GET)
    public ResponseEntity<String> getuserRoleDetails(@PathVariable("userName") String userName) {
    	logger.info("UserController ::  getuserRoleDetails :: STARTS " );
    	Gson gson = new Gson();
        String roleCode = userService.getUserRoleDetail(userName);
        if(roleCode == null || "".equals(roleCode)){
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT); 
        }
        return new ResponseEntity<String>(gson.toJson(roleCode), HttpStatus.OK);
    } 
     
}