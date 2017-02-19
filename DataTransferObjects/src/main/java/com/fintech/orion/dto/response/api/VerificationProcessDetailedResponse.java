package com.fintech.orion.dto.response.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * VerificationProcessDetailedResponse
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-12-18T09:12:11.427Z")

public class VerificationProcessDetailedResponse {
    @JsonProperty("facialMatch")
    private String facialMatch = null;

    @JsonProperty("livenessTest")
    private String livenessTest = null;

    @JsonProperty("verificationRequestId")
    private String verificationRequestId = null;

    @JsonProperty("status")
    private String status = null;

    @JsonProperty("data")
    private List<FieldData> data = new ArrayList<FieldData>();

    @JsonProperty("visualAuthenticity")
    private List<VisualAuthenticity> visualAuthenticity = new ArrayList<VisualAuthenticity>();

    @JsonProperty("imageQuality")
    private List<ImageQuality> imageQuality = new ArrayList<ImageQuality>();

    @JsonProperty("dataValidation")
    private List<DataValidation> dataValidation = new ArrayList<DataValidation>();

    @JsonProperty("idDocFullValidations")
    private List<ValidationData> idDocFullValidations = new ArrayList<ValidationData>();

    @JsonProperty("addressDocFullValidations")
    private List<ValidationData> addressDocFullValidations = new ArrayList<ValidationData>();

    public VerificationProcessDetailedResponse facialMatch(String facialMatch) {
        this.facialMatch = facialMatch;
        return this;
    }

    /**
     * Get facialMatch
     *
     * @return facialMatch
     **/
    @ApiModelProperty(value = "")
    public String getFacialMatch() {
        return facialMatch;
    }

    public void setFacialMatch(String facialMatch) {
        this.facialMatch = facialMatch;
    }

    public VerificationProcessDetailedResponse livenessTest(String livenessTest) {
        this.livenessTest = livenessTest;
        return this;
    }

    /**
     * Get livenessTest
     *
     * @return livenessTest
     **/
    @ApiModelProperty(value = "")
    public String getLivenessTest() {
        return livenessTest;
    }

    public void setLivenessTest(String livenessTest) {
        this.livenessTest = livenessTest;
    }

    public VerificationProcessDetailedResponse verificationRequestId(String verificationRequestId) {
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

    public VerificationProcessDetailedResponse status(String status) {
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

    public VerificationProcessDetailedResponse data(List<FieldData> data) {
        this.data = data;
        return this;
    }

    public VerificationProcessDetailedResponse addDataItem(FieldData dataItem) {
        this.data.add(dataItem);
        return this;
    }

    /**
     * Get data
     *
     * @return data
     **/
    @ApiModelProperty(value = "")
    public List<FieldData> getData() {
        return data;
    }

    public void setData(List<FieldData> data) {
        this.data = data;
    }

    public VerificationProcessDetailedResponse visualAuthenticity(List<VisualAuthenticity> visualAuthenticity) {
        this.visualAuthenticity = visualAuthenticity;
        return this;
    }

    public VerificationProcessDetailedResponse addVisualAuthenticityItem(VisualAuthenticity visualAuthenticityItem) {
        this.visualAuthenticity.add(visualAuthenticityItem);
        return this;
    }

    /**
     * Get visualAuthenticity
     *
     * @return visualAuthenticity
     **/
    @ApiModelProperty(value = "")
    public List<VisualAuthenticity> getVisualAuthenticity() {
        return visualAuthenticity;
    }

    public void setVisualAuthenticity(List<VisualAuthenticity> visualAuthenticity) {
        this.visualAuthenticity = visualAuthenticity;
    }

    public VerificationProcessDetailedResponse imageQuality(List<ImageQuality> imageQuality) {
        this.imageQuality = imageQuality;
        return this;
    }

    public VerificationProcessDetailedResponse addImageQualityItem(ImageQuality imageQualityItem) {
        this.imageQuality.add(imageQualityItem);
        return this;
    }

    /**
     * Get imageQuality
     *
     * @return imageQuality
     **/
    @ApiModelProperty(value = "")
    public List<ImageQuality> getImageQuality() {
        return imageQuality;
    }

    public void setImageQuality(List<ImageQuality> imageQuality) {
        this.imageQuality = imageQuality;
    }

    public VerificationProcessDetailedResponse dataValidation(List<DataValidation> dataValidation) {
        this.dataValidation = dataValidation;
        return this;
    }

    public VerificationProcessDetailedResponse addDataValidationItem(DataValidation dataValidationItem) {
        this.dataValidation.add(dataValidationItem);
        return this;
    }

    /**
     * Get dataValidation
     *
     * @return dataValidation
     **/
    @ApiModelProperty(value = "")
    public List<DataValidation> getDataValidation() {
        return dataValidation;
    }

    public void setDataValidation(List<DataValidation> dataValidation) {
        this.dataValidation = dataValidation;
    }

    public VerificationProcessDetailedResponse idDocFullValidations(List<ValidationData> idDocFullValidations) {
        this.idDocFullValidations = idDocFullValidations;
        return this;
    }

    public VerificationProcessDetailedResponse addIdDocFullValidationsItem(ValidationData idDocFullValidationsItem) {
        this.idDocFullValidations.add(idDocFullValidationsItem);
        return this;
    }

    /**
     * Get idDocFullValidations
     *
     * @return idDocFullValidations
     **/
    @ApiModelProperty(value = "")
    public List<ValidationData> getIdDocFullValidations() {
        return idDocFullValidations;
    }

    public void setIdDocFullValidations(List<ValidationData> idDocFullValidations) {
        this.idDocFullValidations = idDocFullValidations;
    }

    public VerificationProcessDetailedResponse addressDocFullValidations(List<ValidationData> addressDocFullValidations) {
        this.addressDocFullValidations = addressDocFullValidations;
        return this;
    }

    public VerificationProcessDetailedResponse addAddressDocFullValidationsItem(ValidationData addressDocFullValidationsItem) {
        this.addressDocFullValidations.add(addressDocFullValidationsItem);
        return this;
    }

    /**
     * Get addressDocFullValidations
     *
     * @return addressDocFullValidations
     **/
    @ApiModelProperty(value = "")
    public List<ValidationData> getAddressDocFullValidations() {
        return addressDocFullValidations;
    }

    public void setAddressDocFullValidations(List<ValidationData> addressDocFullValidations) {
        this.addressDocFullValidations = addressDocFullValidations;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VerificationProcessDetailedResponse verificationProcessDetaildResponse = (VerificationProcessDetailedResponse) o;
        return Objects.equals(this.verificationRequestId, verificationProcessDetaildResponse.verificationRequestId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(facialMatch, livenessTest, verificationRequestId, status, data, visualAuthenticity, imageQuality, dataValidation, idDocFullValidations, addressDocFullValidations);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class VerificationProcessDetailedResponse {\n");

        sb.append("    facialMatch: ").append(toIndentedString(facialMatch)).append("\n");
        sb.append("    livenessTest: ").append(toIndentedString(livenessTest)).append("\n");
        sb.append("    verificationRequestId: ").append(toIndentedString(verificationRequestId)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    data: ").append(toIndentedString(data)).append("\n");
        sb.append("    visualAuthenticity: ").append(toIndentedString(visualAuthenticity)).append("\n");
        sb.append("    imageQuality: ").append(toIndentedString(imageQuality)).append("\n");
        sb.append("    dataValidation: ").append(toIndentedString(dataValidation)).append("\n");
        sb.append("    idDocFullValidations: ").append(toIndentedString(idDocFullValidations)).append("\n");
        sb.append("    addressDocFullValidations: ").append(toIndentedString(addressDocFullValidations)).append("\n");
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

