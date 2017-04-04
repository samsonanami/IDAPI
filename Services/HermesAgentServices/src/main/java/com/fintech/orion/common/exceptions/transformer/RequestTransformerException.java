package com.fintech.orion.common.exceptions.transformer;

public class RequestTransformerException extends Exception {

    public RequestTransformerException(String message) {
        super(message);
    }

    public RequestTransformerException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestTransformerException(Throwable cause) {
        super(cause);
    }
}
