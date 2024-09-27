package com.icebear2n2.lotto.exception.auth;

import org.springframework.http.HttpStatus;

import com.icebear2n2.lotto.exception.ClientErrorException;

public class AuthCodeNotFoundException extends ClientErrorException {
	
	public AuthCodeNotFoundException() { super(HttpStatus.NOT_FOUND, "해당하는 인증 정보를 찾을 수 없습니다."); }

}
