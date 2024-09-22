package com.icebear2n2.lotto.model.request;

public record PasswordRecoveryRequest(Long userId, String code, String newPassword, String confirmNewPassword) {
}
