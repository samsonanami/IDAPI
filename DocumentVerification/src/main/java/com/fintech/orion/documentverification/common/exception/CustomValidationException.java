package com.fintech.orion.documentverification.common.exception;

/**
 * Created by sasitha on 12/26/16.
 */
public class CustomValidationException extends Exception {

    public CustomValidationException(String s) {
        super(s);
    }

    public CustomValidationException(String s, Exception e) {
        super(s, e);
    }

    public CustomValidationException(Exception e) {
        super(e);
    }
}
