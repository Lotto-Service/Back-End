package com.icebear2n2.lotto.exception.auth;

import org.springframework.http.HttpStatus;

import com.icebear2n2.lotto.exception.ClientErrorException;

public class InvalidTokenException extends ClientErrorException {
    public InvalidTokenException() { 
        super(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."); 
    }
}
