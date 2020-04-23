package com.ammu.security.utility;

import com.ammu.dto.RegistrationDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtToken 
{
	static String secretKey;
	
//	public static RegistrationDto parseToken(String token)
//	{
//		
//	}
	
	//GENERATES JWT TOKEN CONTAINING EMAIL AS SUBJECT.
	public static String generateToken(RegistrationDto registrationDto)
	{
		Claims claims = Jwts.claims().setSubject(registrationDto.getEmail());
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secretKey).compact();
	}
}
