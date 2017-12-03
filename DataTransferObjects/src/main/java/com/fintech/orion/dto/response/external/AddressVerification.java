package com.fintech.orion.dto.response.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * AddressVeriffication
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-02T05:17:07.596Z")

public class AddressVerification {
    @JsonProperty("status")
    private String status = null;

    @JsonProperty("dataValidations")
    private List<DocumentMrzVizValidation> dataValidations = new ArrayList<DocumentMrzVizValidation>();

    @JsonProperty("customValidations")
    private List<CustomValidation> customValidations = new ArrayList<CustomValidation>();

    public AddressVerification status(String status) {
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

    public AddressVerification dataValidations(List<DocumentMrzVizValidation> dataValidations) {
        this.dataValidations = dataValidations;
        return this;
    }

    public AddressVerification addDataValidationsItem(DocumentMrzVizValidation dataValidationsItem) {
        this.dataValidations.add(dataValidationsItem);
        return this;
    }

    /**
     * Get dataValidations
     *
     * @return dataValidations
     **/
    @ApiModelProperty(value = "")
    public List<DocumentMrzVizValidation> getDataValidations() {
        return dataValidations;
    }

    public void setDataValidations(List<DocumentMrzVizValidation> dataValidations) {
        this.dataValidations = dataValidations;
    }

    public AddressVerification customValidations(List<CustomValidation> customValidations) {
        this.customValidations = customValidations;
        return this;
    }

    public AddressVerification addCustomValidationsItem(CustomValidation customValidationsItem) {
        this.customValidations.add(customValidationsItem);
        return this;
    }

    /**
     * Get customValidations
     *
     * @return customValidations
     **/
    @ApiModelProperty(value = "")
    public List<CustomValidation> getCustomValidations() {
        return customValidations;
    }

    public void setCustomValidations(List<CustomValidation> customValidations) {
        this.customValidations = customValidations;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AddressVerification addressVeriffication = (AddressVerification) o;
        return Objects.equals(this.status, addressVeriffication.status) &&
                Objects.equals(this.dataValidations, addressVeriffication.dataValidations) &&
                Objects.equals(this.customValidations, addressVeriffication.customValidations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, dataValidations, customValidations);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AddressVeriffication {\n");

        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    dataValidations: ").append(toIndentedString(dataValidations)).append("\n");
        sb.append("    customValidations: ").append(toIndentedString(customValidations)).append("\n");
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

