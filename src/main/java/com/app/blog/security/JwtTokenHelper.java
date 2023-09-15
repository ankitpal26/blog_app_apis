package com.app.blog.security;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;


@Component
public class JwtTokenHelper implements Serializable{
	
	public static final long JWT_TOKEN_VALIDITY= 5 * 60 * 60;
	
	private String secret = "jwtTokenKey";
	
	//retrieve username from jwt token
	public String getUsernameFromToken(String token) {
	 return getClaimFromToken(token,Claims::getSubject);
			  
	}

}
