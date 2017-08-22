package com.fintech.orion.dto.response.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * DataValidationValue
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-12-18T09:12:11.427Z")

public class DataValidationValue {
    @JsonProperty("documentName")
    private String documentName = null;

    @JsonProperty("mrzValue")
    private Object mrzValue = null;

    @JsonProperty("vizValue")
    private Object vizValue = null;

    @JsonProperty("remarks")
    private String remarks = null;

    @JsonProperty("status")
    private boolean status = false;

    private boolean isCriticalValidation = false;

    /**
     * Get documentName
     *
     * @return documentName
     **/
    @ApiModelProperty(value = "")
    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    /**
     * Get mrzValue
     *
     * @return mrzValue
     **/
    @ApiModelProperty(value = "")
    public Object getMrzValue() {
        return mrzValue;
    }

    public void setMrzValue(Object mrzValue) {
        this.mrzValue = mrzValue;
    }

    /**
     * Get vizValue
     *
     * @return vizValue
     **/
    @ApiModelProperty(value = "")
    public Object getVizValue() {
        return vizValue;
    }

    public void setVizValue(Object vizValue) {
        this.vizValue = vizValue;
    }

    /**
     * Get remarks
     *
     * @return remarks
     **/
    @ApiModelProperty(value = "")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * Get status
     *
     * @return status
     **/
    @ApiModelProperty(value = "")
    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public boolean isCriticalValidation() {
        return isCriticalValidation;
    }

    public void setCriticalValidation(boolean criticalValidation) {
        isCriticalValidation = criticalValidation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DataValidationValue dataValidationValue = (DataValidationValue) o;
        return isIdDocValueEqual(dataValidationValue) && isAddressDocValueEqual(dataValidationValue);
    }

    private boolean isIdDocValueEqual(DataValidationValue dataValidationValue) {
        return Objects.equals(this.mrzValue, dataValidationValue.mrzValue) &&
                Objects.equals(this.documentName, dataValidationValue.documentName) &&
                Objects.equals(this.vizValue, dataValidationValue.vizValue);
    }

    private boolean isAddressDocValueEqual(DataValidationValue dataValidationValue) {
        return Objects.equals(this.remarks, dataValidationValue.remarks) &&
                Objects.equals(this.status, dataValidationValue.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentName, mrzValue, vizValue, remarks, status);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DataValidationValue {\n");

        sb.append("    documentName: ").append(toIndentedString(documentName)).append("\n");
        sb.append("    mrzValue: ").append(toIndentedString(mrzValue)).append("\n");
        sb.append("    vizValue: ").append(toIndentedString(vizValue)).append("\n");
        sb.append("    remarks: ").append(toIndentedString(remarks)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
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

