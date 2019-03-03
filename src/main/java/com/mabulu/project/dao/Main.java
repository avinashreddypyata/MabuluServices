package com.mabulu.project.dao;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Main {

	public static void main(String[] args) {
		 PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		 System.out.println(passwordEncoder.encode("admin"));
	

	}

}
