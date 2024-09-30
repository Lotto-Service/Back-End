package com.icebear2n2.lotto.repository;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.icebear2n2.lotto.exception.ClientErrorException;
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
		return refreshTokenJpaRepository.findByToken(token).orElseThrow(() -> new ClientErrorException(HttpStatus.OK, "유효하지 않은 토큰입니다."));
	}
	
	public RefreshToken findByUser(User user) {
		return refreshTokenJpaRepository.findByUser(user).orElseThrow(() -> new ClientErrorException(HttpStatus.OK, "해당 유저(" + user.getUsername() +")와 일치하는 토큰을 찾을 수 없습니다."));
	}
	
	public boolean existsByUser(User user) {
		return refreshTokenJpaRepository.existsByUser(user);
	}
	
	@Transactional
	public void update(User user, String token, ZonedDateTime expiredAt) {
		RefreshToken refreshToken = findByUser(user);
		refreshToken.setToken(token);
		refreshToken.setExpiredAt(expiredAt);
		
		refreshTokenJpaRepository.save(refreshToken);
	}
	
	public void delete(RefreshToken token) {
		refreshTokenJpaRepository.delete(token);
	}
}
