package com.icebear2n2.lotto.exception.auth;

import org.springframework.http.HttpStatus;

import com.icebear2n2.lotto.exception.ClientErrorException;

public class AuthCodeExpiredException extends ClientErrorException {
	public AuthCodeExpiredException() { super(HttpStatus.BAD_REQUEST, "인증 정보가 만료되었습니다."); }
}
