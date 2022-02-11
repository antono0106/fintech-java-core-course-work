package com.moroz.exceptions;

import java.io.IOException;

/**
 * @author : anton
 * @since : 11.02.2022, пт
 **/
public class UnsupportedFileExtension extends IOException {
    public UnsupportedFileExtension() {
    }

    public UnsupportedFileExtension(String message) {
        super(message);
    }

    public UnsupportedFileExtension(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedFileExtension(Throwable cause) {
        super(cause);
    }
}
