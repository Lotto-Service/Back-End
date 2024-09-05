package com.icebear2n2.lotto.exception.user;

import com.icebear2n2.lotto.exception.ClientErrorException;
import org.springframework.http.HttpStatus;

public class UserAlreadyExistException extends ClientErrorException {

    public UserAlreadyExistException() { super(HttpStatus.CONFLICT, "User already exists."); }

    public UserAlreadyExistException(String username) {
        super(HttpStatus.CONFLICT, "User with username " + username + " already exists.");
    }
}
