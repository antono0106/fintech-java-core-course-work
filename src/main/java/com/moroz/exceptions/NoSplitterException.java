package com.moroz.exceptions;

/**
 * @author : anton
 * @since : 14.02.2022, пн
 **/
public class NoSplitterException extends Exception {
    public NoSplitterException() {
    }

    public NoSplitterException(String message) {
        super(message);
    }

    public NoSplitterException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSplitterException(Throwable cause) {
        super(cause);
    }

    public NoSplitterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
