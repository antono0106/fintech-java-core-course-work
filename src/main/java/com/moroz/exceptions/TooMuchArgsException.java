package com.moroz.exceptions;

/**
 * @author : anton
 * @since : 15.02.2022, вт
 **/
public class TooMuchArgsException extends Exception {
    public TooMuchArgsException() {
    }

    public TooMuchArgsException(String message) {
        super(message);
    }

    public TooMuchArgsException(String message, Throwable cause) {
        super(message, cause);
    }

    public TooMuchArgsException(Throwable cause) {
        super(cause);
    }

    public TooMuchArgsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
