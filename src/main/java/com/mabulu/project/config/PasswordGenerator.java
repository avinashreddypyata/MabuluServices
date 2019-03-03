package com.mabulu.project.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;

public class PasswordGenerator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		System.out.println(bCryptPasswordEncoder.encode("admin"));
		System.out.println(
				StringUtils.cleanPath("C:\\Users\\avina\\OneDrive\\Desktop\\Personal\\Copy of OPT_EAD\\OPT_EAD"));

	}

}
