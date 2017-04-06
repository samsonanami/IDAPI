package com.fintech.orion.dto.response.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * DataValidation
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-02T05:17:07.596Z")

public class MrzVizValidation {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("status")
    private boolean status = false;

    @JsonProperty("isACriticalValidation")
    private boolean isACriticalValidation = false;

    @JsonProperty("values")
    private List<DocumentMrzVizValidationValue> values = new ArrayList<DocumentMrzVizValidationValue>();

    public MrzVizValidation id(String id) {
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

    public MrzVizValidation status(boolean status) {
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

    public MrzVizValidation values(List<DocumentMrzVizValidationValue> values) {
        this.values = values;
        return this;
    }

    public MrzVizValidation addValuesItem(DocumentMrzVizValidationValue valuesItem) {
        this.values.add(valuesItem);
        return this;
    }

    /**
     * Get values
     *
     * @return values
     **/
    @ApiModelProperty(value = "")
    public List<DocumentMrzVizValidationValue> getValues() {
        return values;
    }

    public void setValues(List<DocumentMrzVizValidationValue> values) {
        this.values = values;
    }


    /**
     * Get isACriticalValidation
     *
     * @return isACriticalValidation
     */
    public boolean isACriticalValidation() {
        return isACriticalValidation;
    }

    public void setACriticalValidation(boolean ACriticalValidation) {
        isACriticalValidation = ACriticalValidation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MrzVizValidation dataValidation = (MrzVizValidation) o;
        return Objects.equals(this.id, dataValidation.id) &&
                Objects.equals(this.status, dataValidation.status) &&
                Objects.equals(this.values, dataValidation.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, values);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DataValidation {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    values: ").append(toIndentedString(values)).append("\n");
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

