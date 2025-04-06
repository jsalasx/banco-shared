package com.drkapps.shared.domain.exceptions;

public class BancoGlobalException extends RuntimeException {

    public BancoGlobalException(String message) {
        super(message);
    }

    public BancoGlobalException(String message, Throwable cause) {
        super(message, cause);
    }

    public BancoGlobalException(Throwable cause) {
        super(cause);
    }

    public BancoGlobalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
