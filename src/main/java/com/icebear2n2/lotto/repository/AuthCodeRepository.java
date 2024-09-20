package com.icebear2n2.lotto.repository;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.icebear2n2.lotto.exception.user.UserNotFoundException;
import com.icebear2n2.lotto.model.entity.AuthCode;
import com.icebear2n2.lotto.model.entity.User;
import com.icebear2n2.lotto.model.request.CheckAuthCodeRequest;
import com.icebear2n2.lotto.model.request.PhoneNumberRequest;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Balance;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.service.DefaultMessageService;


@Repository
@RequiredArgsConstructor
public class AuthCodeRepository {
	private final AuthCodeJpaRepository authCodeJpaRepository;
	private final UserJpaRepository userJpaRepository;
    
    public AuthCode saveAuthCode(User currentUser, String phoneNumber, String code) {
    	AuthCode authCode = new AuthCode(currentUser, phoneNumber, code, ZonedDateTime.now().plus(5, ChronoUnit.MINUTES));
    	
    	return authCodeJpaRepository.save(authCode);
    }
    
    
    public boolean isCodeIssuedToUser(User currentUser, String code) {
    	return authCodeJpaRepository.findByUserAndCode(currentUser, code) != null;
    }
    
    public AuthCode checkAuthCode(String phoneNumber, String code, User currentUser) {
    	// Step 1: 전화번호와 코드로 인증코드가 존재하는지 확인
    	AuthCode authCode = getValidAuthCode(phoneNumber, code);
    	
    	// Step 2: 유저 전화번호 업데이트
    	updateUserPhoneNumber(currentUser, phoneNumber);
    	
    	// Step 3: 인증코드 유저 정보 저장
    	authCode.setUser(currentUser);
    	authCodeJpaRepository.save(authCode);
    	
    	// Step 4: 인증코드 완료 날짜 저장
    	completedSaveAuthCode(authCode);
    	
    	return authCode;
    }
    
    private AuthCode getValidAuthCode(String phoneNumber, String code) {
    	AuthCode authCode = authCodeJpaRepository.findByPhoneNumberAndCode(phoneNumber, code);
    	
    	if (authCode != null && authCode.getExpirationTime().isBefore(ZonedDateTime.now())) {
    		authCodeJpaRepository.delete(authCode);
    		throw new IllegalArgumentException("Expired Auth code.");
    	}
    	
    	if (authCode == null) {
    		throw new IllegalArgumentException("Invalid credential.");
    	}
    	
    	return authCode;
    }
    
    private User getByUser(Long userId) {
    	return userJpaRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
    }
    
    private void updateUserPhoneNumber(User user, String phoneNumber) {
    	user.setPhoneNumber(phoneNumber);
    	userJpaRepository.save(user);
    }
    
    private void completedSaveAuthCode(AuthCode authCode) {
    	authCode.setCompletedAt(ZonedDateTime.now());
    	authCodeJpaRepository.save(authCode);
    }
    

}
