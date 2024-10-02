package com.icebear2n2.lotto.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.icebear2n2.lotto.exception.ClientErrorException;
import com.icebear2n2.lotto.model.entity.AuthCode;
import com.icebear2n2.lotto.model.entity.User;
import com.icebear2n2.lotto.model.request.PasswordRecoveryRequest;
import com.icebear2n2.lotto.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PasswordRecoveryService {
	private final UserRepository userRepository;
	private final AuthCodeService authCodeService;
	
	public String requestPasswordRecovery(String username) {
		User user = userRepository.findByUsername(username);
		
		// 인증번호 SMS 전송
		return authCodeService.sendAuthCode(user);
	}
	
	public String verifyAuthCodeAndResetPassword(PasswordRecoveryRequest passwordRecoveryRequest) {
		
		
		User user = userRepository.findByUsername(passwordRecoveryRequest.username());
		
		// Step 1: 인증코드가 일치하는지 확인하고 해당 인증코드 객체 반환 받기
		AuthCode validAuthCode = authCodeService.getValidAuthCode(user.getPhoneNumber(), passwordRecoveryRequest.code());
		
		// Step 2: 비밀번호가 일치하는지 확인
		confirmNewPassword(passwordRecoveryRequest.newPassword(), passwordRecoveryRequest.confirmNewPassword());
		
		// Step 3: 비밀번호 확인 메서드가 통과하면 인증코드 완료일자 필드 업데이트
		authCodeService.completedSaveAuthCode(validAuthCode);
		
		// Step 4: 비밀번호 재설정
		resetPassword(user, passwordRecoveryRequest.newPassword());
		
		return "성공적으로 비밀번호가 변경되었습니다.";
		
	}
	
	private void resetPassword(User user, String newPassword) {
		String encodedPassword = new BCryptPasswordEncoder().encode(newPassword);
		user.setPassword(encodedPassword);
		
		userRepository.update(user);
	}
	
	private void confirmNewPassword(String newPassword, String confirmNewPassword) {
		if (!confirmNewPassword.equals(newPassword)) {
			throw new ClientErrorException(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다.");
		}
	}
}
