package com.mabulu.project.dao;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationService {
	@Autowired
	private MabuluDao stockDao;
	
	//@Autowired
	//private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public boolean authorizeTheUser(String username, String password) {
		
		String realPassword = stockDao.getPassword(username);
		
		if(Objects.equals(realPassword, password)) {
			return true;
		}
		
		return false;
		
	}
	
public int getUserIdByUserName(String username) {
		
		return  stockDao.getUserIdByUserName(username);
		
		
		
	}

}
