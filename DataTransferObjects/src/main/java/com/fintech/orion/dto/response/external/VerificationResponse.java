package com.fintech.orion.dto.response.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * VerificationResponse
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-02T05:17:07.596Z")

public class VerificationResponse {
    @JsonProperty("status")
    private String status = null;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VerificationResponse verificationResponse = (VerificationResponse) o;
        return Objects.equals(this.status, verificationResponse.status) &&
                Objects.equals(this.verificationRequestId, verificationResponse.verificationRequestId) &&
                Objects.equals(this.facialVerification, verificationResponse.facialVerification) &&
                Objects.equals(this.livenessTest, verificationResponse.livenessTest) &&
                Objects.equals(this.idVerification, verificationResponse.idVerification) &&
                Objects.equals(this.addressVerification, verificationResponse.addressVerification) &&
                Objects.equals(this.data, verificationResponse.data) &&
                Objects.equals(this.imageDetails, verificationResponse.imageDetails) &&
                Objects.equals(this.verificationDetails, verificationResponse.verificationDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, verificationRequestId, facialVerification, livenessTest, idVerification, addressVerification, data, imageDetails, verificationDetails);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class VerificationResponse {\n");

        sb.append("    status: ").append(toIndentedString(status)).append("\n");
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

