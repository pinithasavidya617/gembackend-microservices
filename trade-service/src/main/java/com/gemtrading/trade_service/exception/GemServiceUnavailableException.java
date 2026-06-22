package com.gemtrading.trade_service.exception;

public class GemServiceUnavailableException extends RuntimeException {

    public GemServiceUnavailableException(String message) {
        super(message);
    }
}