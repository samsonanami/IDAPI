package com.fintech.orion.dto.validation.client;

/**
 *
 * Created by sasitha on 11/3/16.
 */
public class ClientLicenseValidationStatus {

    private boolean status;
    private String message;
    private String licenseKey;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLicenseKey() {
        return licenseKey;
    }

    public void setLicenseKey(String licenseKey) {
        this.licenseKey = licenseKey;
    }
}
