package com.fintech.orion.dataabstraction.models.verificationresult;

/**
 * Created by ChathurangaRW on 10/7/2016.
 */
public class VerificationProcess {
    private String verificationProcessId;
    private String status;
    private String data;

    public String getVerificationProcessId() {
        return verificationProcessId;
    }

    public void setVerificationProcessId(String verificationProcessId) {
        this.verificationProcessId = verificationProcessId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
