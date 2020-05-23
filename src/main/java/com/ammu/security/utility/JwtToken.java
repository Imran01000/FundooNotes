package com.ammu.security.utility;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ammu.dto.ForgetPasswordDto;
import com.ammu.dto.LoginDto;
import com.ammu.dto.RegistrationDto;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.Verification;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtToken 
{
	private static String secretKey = "SECRETKEY";
	
	 public static String generateUserToken(int id) {
		 	secretKey =  "SECRETKEY";
	        Algorithm algorithm = Algorithm.HMAC256(secretKey);
	        String token = JWT.create().withClaim("ID", id).sign(algorithm);
	        return token;
	    }

	    public static int retrieveIdFromToken(String token) {
	    	secretKey =  "SECRETKEY";
	        Verification verification;
	        verification = JWT.require(Algorithm.HMAC256(secretKey));
	        JWTVerifier jwtverifier = verification.build();
	        DecodedJWT decodedjwt = jwtverifier.verify(token);
	        Claim claim = decodedjwt.getClaim("ID");
	        int id = claim.asInt();
	        return id;
	    }
}