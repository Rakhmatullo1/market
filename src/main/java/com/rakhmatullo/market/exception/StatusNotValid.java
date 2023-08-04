package com.rakhmatullo.market.exception;

public class StatusNotValid extends Exception{
    public StatusNotValid(String message) {
        super(message);
    }

    public StatusNotValid(String message, Throwable cause) {
        super(message, cause);
    }

    public StatusNotValid(Throwable cause) {
        super(cause);
    }
}
