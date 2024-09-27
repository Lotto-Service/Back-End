package com.icebear2n2.lotto.service;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.icebear2n2.lotto.exception.auth.AuthCodeExpiredException;
import com.icebear2n2.lotto.exception.auth.AuthCodeFailedSendException;
import com.icebear2n2.lotto.exception.auth.InvalidCredentialException;
import com.icebear2n2.lotto.model.entity.AuthCode;
import com.icebear2n2.lotto.model.entity.User;
import com.icebear2n2.lotto.repository.AuthCodeRepository;
import com.icebear2n2.lotto.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Balance;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Service
@RequiredArgsConstructor
public class AuthCodeService {
	private static final String AUTH_MESSAGE_FORMAT = "[Web발신] 인증번호: %s";
	private final Random random;
	private final AuthCodeRepository authCodeRepository;
    private final UserRepository userRepository;
    private DefaultMessageService defaultMessageService;
    
    @Value("${nurigo.api.key}")
    private String nurigoApiKey;

    @Value("${nurigo.api.secret}")
    private String nurigoApiSecret;
    
    @PostConstruct
    public void initDefaultMessageService() {
        this.defaultMessageService = NurigoApp.INSTANCE.initialize(nurigoApiKey, nurigoApiSecret, "https://api.coolsms.co.kr");
    }
    
    public String sendAuthCode(User user) {
        String code = generateCode(user);

        Message message = new Message();
        message.setFrom(user.getPhoneNumber());
        message.setTo(user.getPhoneNumber());
        message.setText(String.format(AUTH_MESSAGE_FORMAT, code));

        try {
            this.defaultMessageService.sendOne(new SingleMessageSendingRequest(message));
            saveAuthCode(user, code);
            
            return "메세지를 성공적으로 발송했습니다.";
        } catch (Exception e) {
            throw new AuthCodeFailedSendException();
        }
    }
    
    public String checkAuthCode(User user, String code) {
        // Step 1: 전화번호와 코드로 인증코드가 존재하는지 확인
        AuthCode authCode = getValidAuthCode(user.getPhoneNumber(), code);

        // Step 2: authCode 유저 정보 저장
        authCode.setUser(user);
        authCodeRepository.update(authCode);

        // Step 3: 인증코드 완료 날짜 작성
        completedSaveAuthCode(authCode);
        
        return "인증 확인이 완료되었습니다.";
    }
    
    // 잔액 조회
    public Balance getBalance() {
        return this.defaultMessageService.getBalance();
    } 
    
    
    private void saveAuthCode(User user, String code) {
        authCodeRepository.create(user, code, ZonedDateTime.now().plus(5, ChronoUnit.MINUTES), null);
    }
    
    private String generateCode(User user) {
        int code;
        do {
            code = random.nextInt(900000) + 100000;
        } while (isCodeIssuedToUser(user, String.valueOf(code)));
        return String.valueOf(code);
    }

    private boolean isCodeIssuedToUser(User user, String code) {
        return authCodeRepository.findByUserAndCode(user, code) != null;
    }

    
    private AuthCode getValidAuthCode(String phone, String code) {
        AuthCode authCode = authCodeRepository.findByUserPhoneNumberAndCode(phone, code);
        if (authCode != null && authCode.getExpiredAt().isBefore(ZonedDateTime.now())) {
            authCodeRepository.delete(authCode);
            throw new AuthCodeExpiredException();
        }

        if (authCode == null) {
            throw new InvalidCredentialException();
        }

        return authCode;
    }
    
    private void updateUserPhoneNumber(User user, String phoneNumber) {
    	user.setPhoneNumber(phoneNumber);
    	userRepository.update(user);
    }
    
    private void completedSaveAuthCode(AuthCode authCode) {
    	authCode.setCompletedAt(ZonedDateTime.now());
    	authCodeRepository.update(authCode);
    }
}
