package com.icebear2n2.lotto.exception.user;

import com.icebear2n2.lotto.exception.ClientErrorException;
import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends ClientErrorException {

    public InvalidPasswordException() {
        super(HttpStatus.BAD_REQUEST, "Password must be at least 8 characters long.");
    }

    public InvalidPasswordException(int length) {
        super(HttpStatus.BAD_REQUEST, "Password is too short: only " + length + " characters.");
    }
}
