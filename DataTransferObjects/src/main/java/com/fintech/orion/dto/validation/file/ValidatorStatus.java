package com.fintech.orion.dto.validation.file;

/**
 * Created by TharinduMP on 10/27/2016.
 * ValidatorStatus
 */
public class ValidatorStatus {

    private boolean isPassed;
    private String message;

    public boolean isPassed() {
        return isPassed;
    }

    public void setPassed(boolean passed) {
        this.isPassed = passed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
