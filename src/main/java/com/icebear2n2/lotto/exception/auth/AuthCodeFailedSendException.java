package com.icebear2n2.lotto.exception.auth;

import org.springframework.http.HttpStatus;

import com.icebear2n2.lotto.exception.ClientErrorException;

public class AuthCodeFailedSendException extends ClientErrorException {
    public AuthCodeFailedSendException() { 
        super(HttpStatus.INTERNAL_SERVER_ERROR, "인증 코드를 전송하는 데 실패했습니다."); 
    }
}
