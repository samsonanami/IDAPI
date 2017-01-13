package com.fintech.orion.documentverification.strategy;

/**
 * Created by sasitha on 12/25/16.
 */
public class ValidationResult {
    boolean status;
    String remarks;

    public ValidationResult() {

    }

    public ValidationResult(boolean status) {
        this.status = status;
    }

    public ValidationResult(boolean status, String remarks) {
        this.status = status;
        this.remarks = remarks;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String isRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
