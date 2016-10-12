package com.fintech.orion.dto.validator;

/**
 * Created by TharinduMP on 10/12/2016.
 *  Optimistic result. by default the hasErrors is set to false.
 */
public class ValidatorResult {

    private boolean hasErrors;

    public boolean hasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }
}
