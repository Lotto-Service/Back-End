package com.icebear2n2.lotto.repository;

import java.time.ZonedDateTime;

import org.springframework.stereotype.Repository;

import com.icebear2n2.lotto.exception.auth.InvalidTokenException;
import com.icebear2n2.lotto.model.entity.RefreshToken;
import com.icebear2n2.lotto.model.entity.User;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepository {
	private final RefreshTokenJpaRepository refreshTokenJpaRepository;
	
	@Transactional
	public void create(User user, String token, ZonedDateTime expiredAt) {
	
		refreshTokenJpaRepository.save(new RefreshToken(user, token, expiredAt));
	}
	
	public RefreshToken findByToken(String token) {
		return refreshTokenJpaRepository.findByToken(token).orElseThrow(InvalidTokenException::new);
	}
	
	public RefreshToken findByUser(User user) {
		return refreshTokenJpaRepository.findByUser(user).orElseThrow(() -> new IllegalArgumentException("No refresh token found for user"));
	}
	
	public void delete(RefreshToken token) {
		refreshTokenJpaRepository.delete(token);
	}
}
