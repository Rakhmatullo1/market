package com.rakhmatullo.market.exception;

public class UserExistedException extends Exception {
    public UserExistedException(String message) {
        super(message);
    }

    public UserExistedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserExistedException(Throwable cause) {
        super(cause);
    }
}
