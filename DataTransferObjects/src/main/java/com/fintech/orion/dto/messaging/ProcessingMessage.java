package com.fintech.orion.dto.messaging;

import java.io.Serializable;

import com.fintech.orion.dto.response.external.VerificationResponse;

/**
 * Created by sasitha on 12/18/16.
 *
 */
public class ProcessingMessage implements Serializable {
    private static long serialVersionUID = 1L;

    private String verificationRequestCode;
    private String clientLicense;
    private boolean reVerification;
    private VerificationResponse verificationResponse;

    public String getVerificationRequestCode() {
        return verificationRequestCode;
    }

    public void setVerificationRequestCode(String verificationRequestCode) {
        this.verificationRequestCode = verificationRequestCode;
    }

    public String getClientLicense() {
        return clientLicense;
    }

    public void setClientLicense(String clientLicense) {
        this.clientLicense = clientLicense;
    }

    public boolean isReVerification() {
        return reVerification;
    }

    public void setReVerification(boolean reVerification) {
        this.reVerification = reVerification;
    }

    public VerificationResponse getVerificationResponse() {
        return verificationResponse;
    }

    public void setVerificationResponse(VerificationResponse verificationResponse) {
        this.verificationResponse = verificationResponse;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((clientLicense == null) ? 0 : clientLicense.hashCode());
        result = prime * result + (reVerification ? 1231 : 1237);
        result = prime * result + ((verificationRequestCode == null) ? 0 : verificationRequestCode.hashCode());
        result = prime * result + ((verificationResponse == null) ? 0 : verificationResponse.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProcessingMessage other = (ProcessingMessage) obj;
        if (clientLicense == null) {
            if (other.clientLicense != null)
                return false;
        } else if (!clientLicense.equals(other.clientLicense))
            return false;
        if (reVerification != other.reVerification)
            return false;
        if (verificationRequestCode == null) {
            if (other.verificationRequestCode != null)
                return false;
        } else if (!verificationRequestCode.equals(other.verificationRequestCode))
            return false;
        if (verificationResponse == null) {
            if (other.verificationResponse != null)
                return false;
        } else if (!verificationResponse.equals(other.verificationResponse))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ProcessingMessage [verificationRequestCode=" + verificationRequestCode + ", clientLicense :"
                + clientLicense + " , reVerification=" + reVerification + ", verificationResponse="
                + verificationResponse + "]";
    }

}
