package com.fintech.orion.dto.response.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * DocumentDataValidation
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-02T05:17:07.596Z")

public class DocumentMrzVizValidation {
    @JsonProperty("document")
    private String document = null;

    @JsonProperty("validations")
    private List<MrzVizValidation> validations = new ArrayList<MrzVizValidation>();

    public DocumentMrzVizValidation document(String document) {
        this.document = document;
        return this;
    }

    /**
     * Get document
     *
     * @return document
     **/
    @ApiModelProperty(value = "")
    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public DocumentMrzVizValidation validations(List<MrzVizValidation> validations) {
        this.validations = validations;
        return this;
    }

    public DocumentMrzVizValidation addValidationsItem(MrzVizValidation validationsItem) {
        this.validations.add(validationsItem);
        return this;
    }

    /**
     * Get validations
     *
     * @return validations
     **/
    @ApiModelProperty(value = "")
    public List<MrzVizValidation> getValidations() {
        return validations;
    }

    public void setValidations(List<MrzVizValidation> validations) {
        this.validations = validations;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DocumentMrzVizValidation documentDataValidation = (DocumentMrzVizValidation) o;
        return Objects.equals(this.document, documentDataValidation.document) &&
                Objects.equals(this.validations, documentDataValidation.validations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(document, validations);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DocumentDataValidation {\n");

        sb.append("    document: ").append(toIndentedString(document)).append("\n");
        sb.append("    validations: ").append(toIndentedString(validations)).append("\n");
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

