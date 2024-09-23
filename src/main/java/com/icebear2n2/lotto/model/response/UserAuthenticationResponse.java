package com.icebear2n2.lotto.model.response;

import lombok.Getter;

@Getter
public class UserAuthenticationResponse {
    private String accessToken;
    private String refreshToken;

    public UserAuthenticationResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
