package com.example.demo.daos;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Service;
import com.example.demo.entities.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	
	 private static final String SECRET_KEY =
	            "mysecretkeymysecretkeymysecretkeymysecretkey";

	    private SecretKey getSignKey() {
	        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	    }

	    // Generate Token
	    public String generateToken(String username, Role role) {

	        Map<String, Object> claims = new HashMap<>();
	        claims.put("role", role.name());

	        return Jwts.builder()
	                .claims(claims)
	                .subject(username)
	                .issuedAt(new Date(System.currentTimeMillis()))
	                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
	                .signWith(getSignKey())
	                .compact();
	    }

	    // Extract Username
	    public String extractUsername(String token) {
	        return extractClaims(token).getSubject();
	    }

	    // Extract Role
	    public String extractRole(String token) {
	        return extractClaims(token).get("role", String.class);
	    }

	    // Extract Claims
	    public Claims extractClaims(String token) {

	        return Jwts.parser()
	                .verifyWith(getSignKey())
	                .build()
	                .parseSignedClaims(token)
	                .getPayload();
	    }

	    // Validate Token
	    public boolean validateToken(String token, String username) {

	        final String extractedUsername = extractUsername(token);

	        return extractedUsername.equals(username) && !isTokenExpired(token);
	    }

	    private boolean isTokenExpired(String token) {

	        return extractClaims(token)
	                .getExpiration()
	                .before(new Date());
	    }
		
}
