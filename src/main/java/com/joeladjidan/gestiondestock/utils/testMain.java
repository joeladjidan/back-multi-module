package com.joeladjidan.gestiondestock.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.joeladjidan.gestiondestock.model.auth.ExtendedUser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class testMain {

	public static void main(String[] args) {
		JwtUtil jwtUtil = new JwtUtil();
	
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
	    authorities.add(new SimpleGrantedAuthority("ADMIN"));
	    
		ExtendedUser userDetails = new ExtendedUser("joeladjidan@gmail.com", "Ines", 3, authorities);
		jwtUtil.generateToken(userDetails);
	}
}
