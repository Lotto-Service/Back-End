package com.icebear2n2.lotto.service;

import com.icebear2n2.lotto.model.entity.AuthCode;
import com.icebear2n2.lotto.model.entity.User;
import com.icebear2n2.lotto.model.request.CheckAuthCodeRequest;
import com.icebear2n2.lotto.model.request.PasswordRecoveryRequest;
import com.icebear2n2.lotto.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordRecoveryService {
    private final UserRepository userRepository;
    private final AuthCodeService authCodeService;

    public String requestPasswordRecovery(Long userId) {
        User user = userRepository.findById(userId);

        // 인증 코드 SMS 전송
        authCodeService.sendAuthCode(user, user.getPhoneNumber());

        return "Success";
    }

    public String verifyAuthCodAndResetPassword(PasswordRecoveryRequest request) {
        User user = userRepository.findById(request.userId());

        // Step 1: 인증코드가 일치하는지 확인하고 해당 인증코드 객체 반환 받기
        CheckAuthCodeRequest checkAuthCodeRequest = new CheckAuthCodeRequest(
                user.getUserId(), user.getPhoneNumber(), request.code()
        );

        AuthCode validAuthCode = authCodeService.getValidAuthCode(checkAuthCodeRequest.phoneNumber(), checkAuthCodeRequest.code());

        // Step 2: 비밀번호가 일치하는지 확인
        confirmNewPassword(request.newPassword(), request.confirmNewPassword());

        // step 3: 비밀번호 확인 메서드가 통과하면 인증코드 완료 필드 업데이트
        authCodeService.completedSaveAuthCode(validAuthCode);

        // Step 4: 비밀번호 재설정
        resetPassword(user, request.newPassword());

        return "Success";
    }

    private void resetPassword(User user, String newPassword) {
        String encodedPassword = new BCryptPasswordEncoder().encode(newPassword);
        user.setPassword(encodedPassword);

        userRepository.updateByPassword(user);
    }

    private void confirmNewPassword(String newPassword, String confirmNewPassword) {
        if (!confirmNewPassword.equals(newPassword)) {
            throw new IllegalArgumentException("일치하지 않습니다.");
        }
    }
}
