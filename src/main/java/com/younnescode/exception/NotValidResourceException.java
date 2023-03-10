package com.younnescode.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NotValidResourceException extends RuntimeException {
    public NotValidResourceException(String message) {
        super(message);
    }
}
