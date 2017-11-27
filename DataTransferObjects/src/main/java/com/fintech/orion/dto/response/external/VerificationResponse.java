package com.fintech.orion.dto.response.external;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * VerificationResponse
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-02T05:17:07.596Z")

public class VerificationResponse implements Serializable {
    @JsonProperty("status")
    private String status = null;

    @JsonProperty("message")
    private String message = null;

    @JsonProperty("verificationRequestId")
    private String verificationRequestId = null;

    @JsonProperty("facialVerification")
    private FacialVerification facialVerification = null;

    @JsonProperty("livenessTest")
    private LivenessTest livenessTest = null;

    @JsonProperty("idVerification")
    private IdVerification idVerification = null;

    @JsonProperty("addressVerification")
    private AddressVerification addressVerification = null;

    @JsonProperty("data")
    private List<Data> data = new ArrayList<Data>();

    @JsonProperty("imageDetails")
    private List<Image> imageDetails = new ArrayList<Image>();

    @JsonProperty("verificationDetails")
    private List<Verification> verificationDetails = new ArrayList<Verification>();

    @JsonProperty("processingFailures")
    private List<ProcessingFailure> processingFailures = new ArrayList<>();

    public VerificationResponse status(String status) {
        this.status = status;
        return this;
    }

    /**
     * Get status
     *
     * @return status
     **/
    @ApiModelProperty(value = "")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public VerificationResponse message(String message) {
        this.message = message;
        return this;
    }

    /**
     * Get message
     *
     * @return message
     **/
    @ApiModelProperty(value = "")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public VerificationResponse verificationRequestId(String verificationRequestId) {
        this.verificationRequestId = verificationRequestId;
        return this;
    }

    /**
     * Get verificationRequestId
     *
     * @return verificationRequestId
     **/
    @ApiModelProperty(value = "")
    public String getVerificationRequestId() {
        return verificationRequestId;
    }

    public void setVerificationRequestId(String verificationRequestId) {
        this.verificationRequestId = verificationRequestId;
    }

    public VerificationResponse facialVerification(FacialVerification facialVerification) {
        this.facialVerification = facialVerification;
        return this;
    }

    /**
     * Get facialVerification
     *
     * @return facialVerification
     **/
    @ApiModelProperty(value = "")
    public FacialVerification getFacialVerification() {
        return facialVerification;
    }

    public void setFacialVerification(FacialVerification facialVerification) {
        this.facialVerification = facialVerification;
    }

    public VerificationResponse livenessTest(LivenessTest livenessTest) {
        this.livenessTest = livenessTest;
        return this;
    }

    /**
     * Get livenessTest
     *
     * @return livenessTest
     **/
    @ApiModelProperty(value = "")
    public LivenessTest getLivenessTest() {
        return livenessTest;
    }

    public void setLivenessTest(LivenessTest livenessTest) {
        this.livenessTest = livenessTest;
    }

    public VerificationResponse idVerification(IdVerification idVerification) {
        this.idVerification = idVerification;
        return this;
    }

    /**
     * Get idVerification
     *
     * @return idVerification
     **/
    @ApiModelProperty(value = "")
    public IdVerification getIdVerification() {
        return idVerification;
    }

    public void setIdVerification(IdVerification idVerification) {
        this.idVerification = idVerification;
    }

    public VerificationResponse addressVerification(AddressVerification addressVerification) {
        this.addressVerification = addressVerification;
        return this;
    }

    /**
     * Get addressVerification
     *
     * @return addressVerification
     **/
    @ApiModelProperty(value = "")
    public AddressVerification getAddressVerification() {
        return addressVerification;
    }

    public void setAddressVerification(AddressVerification addressVerification) {
        this.addressVerification = addressVerification;
    }

    public VerificationResponse data(List<Data> data) {
        this.data = data;
        return this;
    }

    public VerificationResponse addDataItem(Data dataItem) {
        this.data.add(dataItem);
        return this;
    }

    /**
     * Get data
     *
     * @return data
     **/
    @ApiModelProperty(value = "")
    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public VerificationResponse imageDetails(List<Image> imageDetails) {
        this.imageDetails = imageDetails;
        return this;
    }

    public VerificationResponse addImageDetailsItem(Image imageDetailsItem) {
        this.imageDetails.add(imageDetailsItem);
        return this;
    }

    /**
     * Get imageDetails
     *
     * @return imageDetails
     **/
    @ApiModelProperty(value = "")
    public List<Image> getImageDetails() {
        return imageDetails;
    }

    public void setImageDetails(List<Image> imageDetails) {
        this.imageDetails = imageDetails;
    }

    public VerificationResponse verificationDetails(List<Verification> verificationDetails) {
        this.verificationDetails = verificationDetails;
        return this;
    }

    public VerificationResponse addVerificationDetailsItem(Verification verificationDetailsItem) {
        this.verificationDetails.add(verificationDetailsItem);
        return this;
    }

    /**
     * Get verificationDetails
     *
     * @return verificationDetails
     **/
    @ApiModelProperty(value = "")
    public List<Verification> getVerificationDetails() {
        return verificationDetails;
    }

    public void setVerificationDetails(List<Verification> verificationDetails) {
        this.verificationDetails = verificationDetails;
    }

    public VerificationResponse addProcessingFailureItem(ProcessingFailure processingFailure) {
        this.processingFailures.add(processingFailure);
        return this;
    }

    /**
     * Get processingFailures
     *
     * @return
     */
    @ApiModelProperty(value = "")
    public List<ProcessingFailure> getProcessingFailures() {
        return processingFailures;
    }

    public void setProcessingFailures(List<ProcessingFailure> processingFailures) {
        this.processingFailures = processingFailures;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        VerificationResponse other = (VerificationResponse) obj;
        if (addressVerification == null) {
            if (other.addressVerification != null)
                return false;
        } else if (!addressVerification.equals(other.addressVerification))
            return false;
        if (data == null) {
            if (other.data != null)
                return false;
        } else if (!data.equals(other.data))
            return false;
        if (facialVerification == null) {
            if (other.facialVerification != null)
                return false;
        } else if (!facialVerification.equals(other.facialVerification))
            return false;
        if (idVerification == null) {
            if (other.idVerification != null)
                return false;
        } else if (!idVerification.equals(other.idVerification))
            return false;
        if (imageDetails == null) {
            if (other.imageDetails != null)
                return false;
        } else if (!imageDetails.equals(other.imageDetails))
            return false;
        if (livenessTest == null) {
            if (other.livenessTest != null)
                return false;
        } else if (!livenessTest.equals(other.livenessTest))
            return false;
        if (message == null) {
            if (other.message != null)
                return false;
        } else if (!message.equals(other.message))
            return false;
        if (processingFailures == null) {
            if (other.processingFailures != null)
                return false;
        } else if (!processingFailures.equals(other.processingFailures))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (verificationDetails == null) {
            if (other.verificationDetails != null)
                return false;
        } else if (!verificationDetails.equals(other.verificationDetails))
            return false;
        if (verificationRequestId == null) {
            if (other.verificationRequestId != null)
                return false;
        } else if (!verificationRequestId.equals(other.verificationRequestId))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((addressVerification == null) ? 0 : addressVerification.hashCode());
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + ((facialVerification == null) ? 0 : facialVerification.hashCode());
        result = prime * result + ((idVerification == null) ? 0 : idVerification.hashCode());
        result = prime * result + ((imageDetails == null) ? 0 : imageDetails.hashCode());
        result = prime * result + ((livenessTest == null) ? 0 : livenessTest.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((processingFailures == null) ? 0 : processingFailures.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((verificationDetails == null) ? 0 : verificationDetails.hashCode());
        result = prime * result + ((verificationRequestId == null) ? 0 : verificationRequestId.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class VerificationResponse {\n");

        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    message: ").append(toIndentedString(message)).append("\n");
        sb.append("    verificationRequestId: ").append(toIndentedString(verificationRequestId)).append("\n");
        sb.append("    facialVerification: ").append(toIndentedString(facialVerification)).append("\n");
        sb.append("    livenessTest: ").append(toIndentedString(livenessTest)).append("\n");
        sb.append("    idVerification: ").append(toIndentedString(idVerification)).append("\n");
        sb.append("    addressVerification: ").append(toIndentedString(addressVerification)).append("\n");
        sb.append("    data: ").append(toIndentedString(data)).append("\n");
        sb.append("    imageDetails: ").append(toIndentedString(imageDetails)).append("\n");
        sb.append("    verificationDetails: ").append(toIndentedString(verificationDetails)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
