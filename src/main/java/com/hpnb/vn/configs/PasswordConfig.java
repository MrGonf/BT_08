package com.hpnb.vn.configs;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordConfig {
	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("admin"));
	}
}
