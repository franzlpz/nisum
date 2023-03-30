package com.nisum.api.configs.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtInfoToken {

	@Value("${security.jwt.client-secret}")
	private String secret;

	public String generateJwt(String username) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + 3600000); // 1 hour
		return Jwts.builder().setSubject(username).setIssuedAt(now).setExpiration(expiryDate).signWith(SignatureAlgorithm.HS512, secret).compact();
	}


}

