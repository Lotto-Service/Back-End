package com.icebear2n2.lotto.service;

import java.util.Random;

import com.icebear2n2.lotto.model.entity.AuthCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.icebear2n2.lotto.model.dto.AuthCodeDto;
import com.icebear2n2.lotto.model.entity.User;
import com.icebear2n2.lotto.repository.AuthCodeRepository;

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
	private static final String AUTH_MESSAGE_FORMAT = "[Web 발신] 인증번호: %s";
	private final AuthCodeRepository authCodeRepository;
    private DefaultMessageService defaultMessageService;
	private final Random random;

    @Value("${nurigo.api.key}")
    private String nurigoApiKey;

    @Value("${nurigo.api.secret}")
    private String nurigoApiSecret;

    @PostConstruct
    public void initDefaultMessageService() {
    	this.defaultMessageService = NurigoApp.INSTANCE.initialize(nurigoApiKey, nurigoApiSecret, "http://api.coolsms.co.kr");
    	
    }
    
    public AuthCodeDto sendAuthCode(User currentUser, String phoneNumber) {
    	String code = generateCode(currentUser);
    	
    	Message message = new Message();
    	message.setFrom(phoneNumber);
        message.setTo(phoneNumber);
        message.setText(String.format(AUTH_MESSAGE_FORMAT, code));
        
        try {
            this.defaultMessageService.sendOne(new SingleMessageSendingRequest(message));
            return AuthCodeDto.of(authCodeRepository.saveAuthCode(currentUser, phoneNumber, code));
        } catch (Exception e) {
            e.printStackTrace(); 
            throw new IllegalArgumentException("Failed to send auth code: " + e.getMessage());
        }
    }
    
    public AuthCodeDto checkAuthCode(String phoneNumber, User currentUser, String code) {
    	return AuthCodeDto.of(authCodeRepository.checkAuthCode(phoneNumber, code, currentUser));
    }

    public AuthCode getValidAuthCode(String phoneNumber, String code) {
        return authCodeRepository.getValidAuthCode(phoneNumber, code);
    }
    
    public Balance getBalance() {
    	return this.defaultMessageService.getBalance();
    }
    
    private String generateCode(User currentUser) {
    	int code;
    	 do {
             code = random.nextInt(900000) + 100000;
         } while (authCodeRepository.isCodeIssuedToUser(currentUser, String.valueOf(code)));
    	 
         return String.valueOf(code);
    }

    public AuthCodeDto completedSaveAuthCode(AuthCode authCode) {
        return AuthCodeDto.of(authCodeRepository.completedSaveAuthCode(authCode));
    }
}
