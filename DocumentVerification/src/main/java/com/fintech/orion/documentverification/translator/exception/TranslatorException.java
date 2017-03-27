package com.fintech.orion.documentverification.translator.exception;


public class TranslatorException extends Exception{

    public TranslatorException(String message) {
        super(message);
    }

    public TranslatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public TranslatorException(Throwable cause) {
        super(cause);
    }
}
