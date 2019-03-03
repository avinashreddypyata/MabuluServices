package com.mabulu.project.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mabulu.project.dao.AuthenticationService;
import com.mabulu.project.domain.User;

@RestController

public class LoginController {
	@Autowired
	private AuthenticationService authenticationService;
	
	private  Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ResponseEntity<User> login(@RequestParam("username") String username,
			@RequestParam("password") String password) {
	
		if (authenticationService.authorizeTheUser(username, password)) {
			int userid = authenticationService.getUserIdByUserName(username);
			User user = new User();
			user.setId(userid);
			user.setRandId(UUID.randomUUID().toString());
			log.debug("Login Sucessful");
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(new User(), HttpStatus.FORBIDDEN);
		}
	}

}
