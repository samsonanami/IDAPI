package com.fintech.orion.dto.response.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * DataValidationValue
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-02T05:17:07.596Z")

public class DocumentMrzVizValidationValue {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("value")
    private Object value = null;

    @JsonProperty("confidence")
    private BigDecimal confidence = null;

    public DocumentMrzVizValidationValue id(String id) {
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

    public DocumentMrzVizValidationValue value(String value) {
        this.value = value;
        return this;
    }

    /**
     * Get value
     *
     * @return value
     **/
    @ApiModelProperty(value = "")
    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public DocumentMrzVizValidationValue confidence(BigDecimal confidence) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DocumentMrzVizValidationValue dataValidationValue = (DocumentMrzVizValidationValue) o;
        return Objects.equals(this.id, dataValidationValue.id) &&
                Objects.equals(this.value, dataValidationValue.value) &&
                Objects.equals(this.confidence, dataValidationValue.confidence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, confidence);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DataValidationValue {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    value: ").append(toIndentedString(value)).append("\n");
        sb.append("    confidence: ").append(toIndentedString(confidence)).append("\n");
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

