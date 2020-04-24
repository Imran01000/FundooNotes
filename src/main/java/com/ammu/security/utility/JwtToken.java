package com.ammu.security.utility;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ammu.dto.ForgetPasswordDto;
import com.ammu.dto.LoginDto;
import com.ammu.dto.RegistrationDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtToken 
{
	private static String secretKey;
	
	public static final long JWT_TOKEN_VALIDITY = 1000*60*5;

	public static String generateToken(RegistrationDto registrationDto)
	{
		Map<String , Object> claims = new HashMap<>();
		return doGenerateToken(claims , registrationDto.getEmail());
	}
	
	public static String generateToken(ForgetPasswordDto forgetPasswordDto)
	{
		Map<String , Object> claims = new HashMap<>();
		return doGenerateToken(claims , forgetPasswordDto.getEmail());
	}
	
	public static String generateToken(LoginDto loginDto)
	{
		Map<String , Object> claims = new HashMap<>();
		return doGenerateToken(claims , loginDto.getEmail());
	}
	
	public static String doGenerateToken(Map<String, Object> claims, String email) 
	{
		return Jwts.builder().setClaims(claims).setSubject(email).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY)).signWith(SignatureAlgorithm.HS512, secretKey)
				.compact();
	}
}
