package com.brhmkskn.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

		public static final String SECRET_KEY="5ad6jrX47K5sF8G4rwgkt89PDvxe5TwKBLPG6lC2esv";
		
		public String generateToken(UserDetails userDetails) {
			
			Map<String, Object> claimsMap=new HashMap<>();
			claimsMap.put("role", "ADMIN");
			
			return Jwts.builder()
					.setSubject(userDetails.getUsername())
					.addClaims(claimsMap)
					.setIssuedAt(new Date())
					.setExpiration(new Date(System.currentTimeMillis()+1000*60*60*2))// 2 Hours life
					.signWith(getKey(),SignatureAlgorithm.HS256)
					.compact();
		}		
		
		public Object getClaimsByKey(String token,String key) {
			Claims claims=getClaims(token);
			return claims.get(key);
		}
		
		public Claims getClaims(String token) {
			
			Claims claims=Jwts
					.parserBuilder()
					.setSigningKey(getKey())
					.build()
					.parseClaimsJws(token).getBody();
			return claims;
		}
		
		public <T> T exportToken(String token, Function<Claims, T> claimsFuncitons) {
			
			Claims claims=getClaims(token);			
			return claimsFuncitons.apply(claims);
		}
		
		public String getUsernameByToken(String token)
		{
			return exportToken(token, Claims::getSubject);		
		}
		
		public boolean isTokenExpired(String token)
		{
			Date expiredDate=exportToken(token, Claims::getExpiration);
			return new Date().before(expiredDate);
		}
		
		public Key getKey() {
			byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
			return Keys.hmacShaKeyFor(keyBytes);
		}

	
}
