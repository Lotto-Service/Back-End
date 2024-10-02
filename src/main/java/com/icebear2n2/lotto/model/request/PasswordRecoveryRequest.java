package com.icebear2n2.lotto.model.request;

public record PasswordRecoveryRequest(String username, String code, String newPassword, String confirmNewPassword) {

}
