package com.icebear2n2.lotto.service;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.icebear2n2.lotto.exception.ClientErrorException;
import com.icebear2n2.lotto.model.entity.AuthCode;
import com.icebear2n2.lotto.model.entity.User;
import com.icebear2n2.lotto.repository.AuthCodeRepository;

import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.message.model.Message;

@Service
@RequiredArgsConstructor
public class AuthCodeService {
	private static final String AUTH_MESSAGE_FORMAT = "[Web발신] 인증번호: %s";
	private final Random random;
	private final AuthCodeRepository authCodeRepository;
    private final MessagingService messagingService;
    

    
    public String sendAuthCode(User user) {
    	 String code = generateCode(user);

         Message message = createMessage(user.getPhoneNumber(), code);
         messagingService.sendMessage(message); // 메시지 전송 로직 분리

         saveAuthCode(user, code);
         return "메세지를 성공적으로 발송했습니다.";
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

    private Message createMessage(String phoneNumber, String code) {
        Message message = new Message();
        message.setFrom(phoneNumber);
        message.setTo(phoneNumber);
        message.setText(String.format(AUTH_MESSAGE_FORMAT, code));
        return message;
    }
    
    public AuthCode getValidAuthCode(String phone, String code) {
        AuthCode authCode = authCodeRepository.findByUserPhoneNumberAndCode(phone, code);
        if (authCode != null && authCode.getExpiredAt().isBefore(ZonedDateTime.now())) {
            authCodeRepository.delete(authCode);
            throw new ClientErrorException(HttpStatus.BAD_REQUEST, "인증 코드가 만료되었습니다.");
        }

        if (authCode == null) {
            throw new ClientErrorException(HttpStatus.BAD_REQUEST, "인증 코드를 찾을 수 없습니다.");
        }

        return authCode;
    }
    
    public void completedSaveAuthCode(AuthCode authCode) {
    	authCode.setCompletedAt(ZonedDateTime.now());
    	authCodeRepository.update(authCode);
    }
}
