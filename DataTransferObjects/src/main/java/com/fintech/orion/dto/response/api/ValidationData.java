package com.fintech.orion.dto.response.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * ValidationData
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-12-18T09:12:11.427Z")

public class ValidationData   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("value")
  private String value = null;

  @JsonProperty("ocrConfidence")
  private Integer ocrConfidence = null;

  @JsonProperty("remarks")
  private String remarks = null;

  public ValidationData id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ValidationData value(String value) {
    this.value = value;
    return this;
  }

   /**
   * Get value
   * @return value
  **/
  @ApiModelProperty(value = "")
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public ValidationData ocrConfidence(Integer ocrConfidence) {
    this.ocrConfidence = ocrConfidence;
    return this;
  }

   /**
   * Get ocrConfidence
   * @return ocrConfidence
  **/
  @ApiModelProperty(value = "")
  public Integer getOcrConfidence() {
    return ocrConfidence;
  }

  public void setOcrConfidence(Integer ocrConfidence) {
    this.ocrConfidence = ocrConfidence;
  }

  public ValidationData remarks(String remarks) {
    this.remarks = remarks;
    return this;
  }

   /**
   * Get remarks
   * @return remarks
  **/
  @ApiModelProperty(value = "")
  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ValidationData validationData = (ValidationData) o;
    return Objects.equals(this.id, validationData.id) &&
        Objects.equals(this.value, validationData.value) &&
        Objects.equals(this.ocrConfidence, validationData.ocrConfidence) &&
        Objects.equals(this.remarks, validationData.remarks);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, value, ocrConfidence, remarks);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ValidationData {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    ocrConfidence: ").append(toIndentedString(ocrConfidence)).append("\n");
    sb.append("    remarks: ").append(toIndentedString(remarks)).append("\n");
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

