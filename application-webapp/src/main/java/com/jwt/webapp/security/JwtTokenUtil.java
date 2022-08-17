package com.jwt.webapp.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jwt.domain.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {

	private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000;//24 horas

	@Value("${app.jwt.secret}")
	private String secretkey;

	public String generateAccessToken(User user) {
		return Jwts.builder().setSubject(String.format("%s,%s", user.getId(), user.getEmail())).setIssuer("daniel@gamil.com")
				.setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
				.signWith(SignatureAlgorithm.HS512, secretkey).compact();
	}

	public boolean validateAccessToken(String token) {
		try {
			Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token);
			return true;
		} catch (ExpiredJwtException ex) {
			return false;
		}
	}

	public String getSubject(String token) {
		return parseClaims(token).getSubject();
	}

	public Claims parseClaims(String token) {
		return Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token).getBody();
	}
}
