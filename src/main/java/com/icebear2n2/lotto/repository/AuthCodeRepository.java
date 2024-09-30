package com.icebear2n2.lotto.repository;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.icebear2n2.lotto.exception.ClientErrorException;
import com.icebear2n2.lotto.model.entity.AuthCode;
import com.icebear2n2.lotto.model.entity.User;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AuthCodeRepository {
	private final AuthCodeJpaRepository authCodeJpaRepository;
	
	public AuthCode create(User user, String code, ZonedDateTime expiredAt, ZonedDateTime completedAt) {
		return authCodeJpaRepository.save(new AuthCode(user, code, expiredAt, completedAt));
	}
	
	public AuthCode findByUserPhoneNumberAndCode(String phoneNumber, String code) {
		return authCodeJpaRepository.findByUserPhoneNumberAndCode(phoneNumber, code).orElseThrow(() -> new ClientErrorException(HttpStatus.OK, "해당 전화번호(" + phoneNumber + ")와 일치하는 인증 코드를 찾을 수 없습니다."));
	}
	
	public AuthCode findByUserAndCode(User user, String code) {
		return authCodeJpaRepository.findByUserAndCode(user, code);
	}
	
	public void update(AuthCode code) {
		authCodeJpaRepository.save(code);
	}
	
	public void delete(AuthCode code) {
		authCodeJpaRepository.delete(code);
	}
}
