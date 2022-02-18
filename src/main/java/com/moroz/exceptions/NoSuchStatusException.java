package com.moroz.exceptions;

/**
 * @author : anton
 * @since : 18.02.2022, пт
 **/
public class NoSuchStatusException extends RuntimeException {
    public NoSuchStatusException() {
    }

    public NoSuchStatusException(String message) {
        super(message);
    }

    public NoSuchStatusException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchStatusException(Throwable cause) {
        super(cause);
    }

    public NoSuchStatusException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
