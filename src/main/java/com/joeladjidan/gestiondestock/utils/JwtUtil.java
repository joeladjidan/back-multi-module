package com.joeladjidan.gestiondestock.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.joeladjidan.gestiondestock.model.auth.ExtendedUser;


@Service
@Slf4j
public class JwtUtil {
	
	private String secret;
	private int jwtExpirationInMs;
	private int refreshExpirationDateInMs;

	@Value("${jwt.secret}")
	public void setSecret(String secret) {
		this.secret = secret;
	}
	
	@Value("${jwt.expirationDateInMs}")
	public void setJwtExpirationInMs(int jwtExpirationInMs) {
		this.jwtExpirationInMs = jwtExpirationInMs;
	}

	@Value("${jwt.refreshExpirationDateInMs}")
	public void setRefreshExpirationDateInMs(int refreshExpirationDateInMs) {
		this.refreshExpirationDateInMs = refreshExpirationDateInMs;
	}

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public String extractIdEntreprise(String token) {
		final Claims claims = extractAllClaims(token);
		return claims.get("idEntreprise", String.class);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	//check if the token has expired
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	//generate token for user
	public String generateToken(ExtendedUser userDetails) {
		log.info("generateToken is username {}", userDetails.getUsername());
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails);
	}

	//while creating the token -
	//1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
	//2. Sign the JWT using the HS256 algorithm and secret key.
	//3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
	//   compaction of the JWT to a URL-safe string 
	private String doGenerateToken(Map<String, Object> claims, ExtendedUser userDetails) {
		 log.info("doGenerateToken is username {}", userDetails.getUsername());
		 
		 String result = Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
					.claim("idEntreprise", userDetails.getIdEntreprise().toString())
					.signWith(SignatureAlgorithm.HS256, secret).compact();
		 
		 log.info("end doGenerateToken is result {}", result);
		 log.info("end doGenerateToken is username {}", userDetails.getUsername());
		 log.info("end doGenerateToken is id enterprise {}", userDetails.getIdEntreprise().toString());
		 
		return result;
	}
	

	public String doGenerateRefreshToken(Map<String, Object> claims, ExtendedUser userDetails) {
		return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + refreshExpirationDateInMs))
				.claim("idEntreprise", userDetails.getIdEntreprise().toString())
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	//validate token
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

}
