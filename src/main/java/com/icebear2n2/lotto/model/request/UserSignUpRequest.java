package com.icebear2n2.lotto.model.request;

import java.util.Date;

public record UserSignUpRequest(String username, String password, String email, Date birth, String phoneNumber) {
}
