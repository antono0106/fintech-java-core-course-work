package com.moroz.exceptions;

/**
 * @author : anton
 * @since : 15.02.2022, вт
 **/
public class NotEnoughArgsException extends Exception {
    public NotEnoughArgsException() {
    }

    public NotEnoughArgsException(String message) {
        super(message);
    }

    public NotEnoughArgsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughArgsException(Throwable cause) {
        super(cause);
    }

    public NotEnoughArgsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
