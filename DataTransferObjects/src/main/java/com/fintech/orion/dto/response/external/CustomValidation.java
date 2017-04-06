package com.fintech.orion.dto.response.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * CustomValidation
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-02T05:17:07.596Z")

public class CustomValidation {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("value")
    private String value = null;

    @JsonProperty("confidence")
    private BigDecimal confidence = null;

    @JsonProperty("remarks")
    private String remarks = null;

    @JsonProperty("status")
    private boolean status = false;

    @JsonProperty("isACriticalValidation")
    private boolean criticalValidation = false;

    public CustomValidation id(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     *
     * @return id
     **/
    @ApiModelProperty(value = "")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CustomValidation value(String value) {
        this.value = value;
        return this;
    }

    /**
     * Get value
     *
     * @return value
     **/
    @ApiModelProperty(value = "")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public CustomValidation confidence(BigDecimal confidence) {
        this.confidence = confidence;
        return this;
    }

    /**
     * Get confidence
     *
     * @return confidence
     **/
    @ApiModelProperty(value = "")
    public BigDecimal getConfidence() {
        return confidence;
    }

    public void setConfidence(BigDecimal confidence) {
        this.confidence = confidence;
    }

    public CustomValidation remarks(String remarks) {
        this.remarks = remarks;
        return this;
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

    public CustomValidation status(boolean status) {
        this.status = status;
        return this;
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


    public boolean getCriticalValidation() {
        return criticalValidation;
    }

    public void setCriticalValidation(boolean criticalValidation) {
        this.criticalValidation = criticalValidation;
    }

    /**
     * Get isACriticalValidation
     *
     * @return
     */
    @ApiModelProperty(value = "")

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomValidation customValidation = (CustomValidation) o;
        return Objects.equals(this.id, customValidation.id) &&
                Objects.equals(this.value, customValidation.value) &&
                Objects.equals(this.confidence, customValidation.confidence) &&
                Objects.equals(this.remarks, customValidation.remarks) &&
                Objects.equals(this.status, customValidation.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, confidence, remarks, status);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CustomValidation {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    value: ").append(toIndentedString(value)).append("\n");
        sb.append("    confidence: ").append(toIndentedString(confidence)).append("\n");
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

