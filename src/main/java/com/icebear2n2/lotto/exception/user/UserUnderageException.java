package com.icebear2n2.lotto.exception.user;

import com.icebear2n2.lotto.exception.ClientErrorException;
import org.springframework.http.HttpStatus;

public class UserUnderageException extends ClientErrorException {

    public UserUnderageException() {
        super(HttpStatus.BAD_REQUEST, "User must be at least 18 years old.");
    }

    public UserUnderageException(int age) {
        super(HttpStatus.BAD_REQUEST, "User is underage: " + age + " years old.");
    }
}
