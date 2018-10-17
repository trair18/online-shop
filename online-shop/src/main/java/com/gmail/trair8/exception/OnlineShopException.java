package com.gmail.trair8.exception;

public class OnlineShopException extends RuntimeException{

    public OnlineShopException() {
    }

    public OnlineShopException(String message) {
        super(message);
    }

    public OnlineShopException(String message, Throwable cause) {
        super(message, cause);
    }

    public OnlineShopException(Throwable cause) {
        super(cause);
    }

    public OnlineShopException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
