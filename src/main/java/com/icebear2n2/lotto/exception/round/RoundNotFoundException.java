package com.icebear2n2.lotto.exception.round;

import com.icebear2n2.lotto.exception.ClientErrorException;
import org.springframework.http.HttpStatus;

public class RoundNotFoundException extends ClientErrorException {

    public RoundNotFoundException() { super(HttpStatus.NOT_FOUND, "Round not found."); }

    public RoundNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, "Round with id " + id + " not found.");
    }
}