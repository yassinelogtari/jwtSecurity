package com.example.demo.configuration;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class JwtService  {
	
	//90ff9b1fc4c7deca4b70879834b518642abe0603d9b00e64eee540a4cdb8b196

	private static final String SECRET_KEY="90ff9b1fc4c7deca4b70879834b518642abe0603d9b00e64eee540a4cdb8b196";
	
	public String extractUsername(String token) {
		return null;
	}
	
	
	public <T> T extractClaim(String token ,Function <Claims,T> claimResolver) {
		final Claims claims= extractAllClaims(token);
		return claimResolver.apply(claims);
	}
	
	
	public String generateToken(Map<String, Object> extraClaims ,UserDetails userDetails) {
		
		return Jwts.
				builder()
				.setClaims(extraClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
				.signWith(getSignInKey(),SignatureAlgorithm.HS256)
				.compact();
	}
	
	
	public boolean isTokenValid(String token,UserDetails userDetails) {
		final String username=extractUsername(token);
		return(username.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}
	
	
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}


	private Date extractExpiration(String token) {
		
		return extractClaim(token, Claims :: getExpiration);
	}


	private Claims extractAllClaims(String token) {
	    return Jwts.parserBuilder()
	            .setSigningKey(getSignInKey())
	            .build()
	            .parseClaimsJws(token)
	            .getBody();
	}

	private Key getSignInKey() {
	    byte[] keysBytes = Decoders.BASE64.decode(SECRET_KEY);
	    return Keys.hmacShaKeyFor(keysBytes);
	}


}
