package com.fintech.orion.documentverification.common.mrz;

/**
 * Created by MudithaJ on 12/23/2016.
 */
public class ValidateMRZResult {

    private String MRZType;
    private String validationResult;
    private String message;
    private String item;

    public ValidateMRZResult() {
        this.MRZType = "";
        this.validationResult = "";
        this.message = "";

    }

    public String getMRZType() {
        return MRZType;
    }

    public void setMRZType(String MRZType) {
        this.MRZType = MRZType;
    }

    public String getValidationResult() {
        return validationResult;
    }

    public void setValidationResult(String validationResult) {
        this.validationResult = validationResult;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
