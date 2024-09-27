package com.icebear2n2.lotto.service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.icebear2n2.lotto.model.entity.RefreshToken;
import com.icebear2n2.lotto.model.entity.User;
import com.icebear2n2.lotto.repository.RefreshTokenRepository;
import com.icebear2n2.lotto.repository.UserRepository;

import javax.crypto.SecretKey;

import java.time.ZonedDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {
    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    private static final SecretKey key = Jwts.SIG.HS256.key().build();

    private final RefreshTokenRepository refreshTokenRepository;
    
    public String generateAccessToken(UserDetails userDetails) {
        return generateToken(userDetails.getUsername(), 1000 * 60 * 60 * 3);
    }

    public String generateRefreshToken(UserDetails userDetails) {
    	long expirationMillis = 1000L * 60 * 60 * 24 * 7;
    	Date expirationDate = new Date(System.currentTimeMillis() + expirationMillis);
    	
    	String refreshToken = generateToken(userDetails.getUsername(), expirationMillis);
    	
    	User user = (User) userDetails;
    	ZonedDateTime expiredAt = ZonedDateTime.now().plusDays(7);
    	
    	refreshTokenRepository.create(user, refreshToken, expiredAt);
    	
    	return refreshToken;
    }
    public String getUsername(String accessToken) {
        return getSubject(accessToken);
    }
    
    public void invalidateRefreshToken(String refreshToken) {
    	RefreshToken token = refreshTokenRepository.findByToken(refreshToken);
    	
    	refreshTokenRepository.delete(token);
    }
    
    private String generateToken(String subject, long expirationMillis) {
    	var now = new Date();
    	var exp = new Date(now.getTime() + expirationMillis);
    	
    	return Jwts.builder()
    			.subject(subject)
    			.signWith(key)
    			.issuedAt(now)
    			.expiration(exp)
    			.compact();
    }
    
    private String getSubject(String token) {
    	try {
    		return Jwts.parser()
    				.verifyWith(key)
    				.build()
    				.parseSignedClaims(token)
    				.getPayload()
    				.getSubject();

    	} catch (JwtException e) {
    		logger.error("JwtException", e);
    		throw e;
    	}
    }
}
