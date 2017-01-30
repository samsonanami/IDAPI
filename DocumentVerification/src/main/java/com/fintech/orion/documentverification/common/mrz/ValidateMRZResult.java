package com.fintech.orion.documentverification.common.mrz;

/**
 * This class contains the MRZ decoded items.
 * Created by MudithaJ on 12/23/2016.
 */
public class ValidateMRZResult {

    private String mrztype;
    private String validationResult;
    private String message;
    private String item;

    public ValidateMRZResult() {
        this.mrztype = "";
        this.validationResult = "";
        this.message = "";

    }

    public String getMRZType() {
        return mrztype;
    }

    public void setMRZType(String mrztype) {
        this.mrztype = mrztype;
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
