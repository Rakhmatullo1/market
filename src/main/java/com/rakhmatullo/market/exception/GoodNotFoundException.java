package com.rakhmatullo.market.exception;

public class GoodNotFoundException extends Exception{

    public GoodNotFoundException(String message) {
        super(message);
    }

    public GoodNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public GoodNotFoundException(Throwable cause) {
        super(cause);
    }
}
