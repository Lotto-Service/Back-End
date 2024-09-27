package com.icebear2n2.lotto.service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.icebear2n2.lotto.exception.ClientErrorException;
import com.icebear2n2.lotto.model.entity.RefreshToken;
import com.icebear2n2.lotto.model.entity.User;
import com.icebear2n2.lotto.repository.RefreshTokenRepository;

import javax.crypto.SecretKey;

import java.time.ZonedDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {
    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    @Value("${jwt-secret}")
    private String jwtSecret;  // 환경 설정에서 시크릿 키 주입
    private SecretKey key;

    private final RefreshTokenRepository refreshTokenRepository;
    
    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes()); // SecretKey 초기화
    }
    
    public String generateAccessToken(UserDetails userDetails) {
        return generateToken(userDetails.getUsername(), 1000 * 60 * 60 * 3); // 3시간 만료
    }

    public String generateRefreshToken(UserDetails userDetails) {
        long expirationMillis = 1000L * 60 * 60 * 24 * 7; // 7일 만료
        return generateAndSaveRefreshToken((User) userDetails, expirationMillis);
    }
    
    public String getUsername(String accessToken) {
        return getSubject(accessToken);
    }
    
    public void invalidateRefreshToken(String refreshToken) {
        RefreshToken token = refreshTokenRepository.findByToken(refreshToken);
        if (token == null) {
            throw new ClientErrorException(HttpStatus.NOT_FOUND, "리프레시 토큰을 찾을 수 없습니다.");
        }
        
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
    
    private String generateAndSaveRefreshToken(User user, long expirationMillis) {
        String refreshToken = generateToken(user.getUsername(), expirationMillis);
        ZonedDateTime expiredAt = ZonedDateTime.now().plusDays(7);

        refreshTokenRepository.create(user, refreshToken, expiredAt);
        return refreshToken;
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
    		logger.error("JWT 유효성 검사 실패: {}", e.getMessage());
    		throw new ClientErrorException(HttpStatus.NOT_FOUND, "토큰을 찾을 수 없습니다.");
    	}
    }
}
