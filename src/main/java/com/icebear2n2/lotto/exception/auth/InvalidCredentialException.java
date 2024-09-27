package com.icebear2n2.lotto.exception.auth;

import org.springframework.http.HttpStatus;

import com.icebear2n2.lotto.exception.ClientErrorException;

public class InvalidCredentialException extends ClientErrorException {
    public InvalidCredentialException() { 
        super(HttpStatus.UNAUTHORIZED, "유효하지 않은 인증 정보입니다."); 
    }
}
