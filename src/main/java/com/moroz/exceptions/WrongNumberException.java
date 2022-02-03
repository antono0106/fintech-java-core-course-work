package com.moroz.exceptions;

/**
 * @author : anton
 * @since : 03.02.2022, чт
 **/
public class WrongNumberException extends RuntimeException {
    public WrongNumberException() {
    }

    public WrongNumberException(String message) {
        super(message);
    }

    public WrongNumberException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongNumberException(Throwable cause) {
        super(cause);
    }

    public WrongNumberException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
