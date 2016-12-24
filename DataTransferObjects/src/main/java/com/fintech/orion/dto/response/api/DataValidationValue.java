package com.fintech.orion.dto.response.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * DataValidationValue
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-12-18T09:12:11.427Z")

public class DataValidationValue   {
  @JsonProperty("idDocVizValue")
  private String idDocVizValue = null;

  @JsonProperty("idDocMrzValue")
  private String idDocMrzValue = null;

  @JsonProperty("idDocConfidence")
  private Integer idDocConfidence = null;

  @JsonProperty("addressVizValue")
  private String addressVizValue = null;

  @JsonProperty("addressMrzvalue")
  private String addressMrzvalue = null;

  @JsonProperty("addressDocConfidence")
  private Integer addressDocConfidence = null;

  public DataValidationValue idDocVizValue(String idDocVizValue) {
    this.idDocVizValue = idDocVizValue;
    return this;
  }

   /**
   * Get idDocVizValue
   * @return idDocVizValue
  **/
  @ApiModelProperty(value = "")
  public String getIdDocVizValue() {
    return idDocVizValue;
  }

  public void setIdDocVizValue(String idDocVizValue) {
    this.idDocVizValue = idDocVizValue;
  }

  public DataValidationValue idDocMrzValue(String idDocMrzValue) {
    this.idDocMrzValue = idDocMrzValue;
    return this;
  }

   /**
   * Get idDocMrzValue
   * @return idDocMrzValue
  **/
  @ApiModelProperty(value = "")
  public String getIdDocMrzValue() {
    return idDocMrzValue;
  }

  public void setIdDocMrzValue(String idDocMrzValue) {
    this.idDocMrzValue = idDocMrzValue;
  }

  public DataValidationValue idDocConfidence(Integer idDocConfidence) {
    this.idDocConfidence = idDocConfidence;
    return this;
  }

   /**
   * Get idDocConfidence
   * @return idDocConfidence
  **/
  @ApiModelProperty(value = "")
  public Integer getIdDocConfidence() {
    return idDocConfidence;
  }

  public void setIdDocConfidence(Integer idDocConfidence) {
    this.idDocConfidence = idDocConfidence;
  }

  public DataValidationValue addressVizValue(String addressVizValue) {
    this.addressVizValue = addressVizValue;
    return this;
  }

   /**
   * Get addressVizValue
   * @return addressVizValue
  **/
  @ApiModelProperty(value = "")
  public String getAddressVizValue() {
    return addressVizValue;
  }

  public void setAddressVizValue(String addressVizValue) {
    this.addressVizValue = addressVizValue;
  }

  public DataValidationValue addressMrzvalue(String addressMrzvalue) {
    this.addressMrzvalue = addressMrzvalue;
    return this;
  }

   /**
   * Get addressMrzvalue
   * @return addressMrzvalue
  **/
  @ApiModelProperty(value = "")
  public String getAddressMrzvalue() {
    return addressMrzvalue;
  }

  public void setAddressMrzvalue(String addressMrzvalue) {
    this.addressMrzvalue = addressMrzvalue;
  }

  public DataValidationValue addressDocConfidence(Integer addressDocConfidence) {
    this.addressDocConfidence = addressDocConfidence;
    return this;
  }

   /**
   * Get addressDocConfidence
   * @return addressDocConfidence
  **/
  @ApiModelProperty(value = "")
  public Integer getAddressDocConfidence() {
    return addressDocConfidence;
  }

  public void setAddressDocConfidence(Integer addressDocConfidence) {
    this.addressDocConfidence = addressDocConfidence;
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
    return Objects.equals(this.idDocVizValue, dataValidationValue.idDocVizValue) &&
        Objects.equals(this.idDocMrzValue, dataValidationValue.idDocMrzValue) &&
        Objects.equals(this.idDocConfidence, dataValidationValue.idDocConfidence) &&
        Objects.equals(this.addressVizValue, dataValidationValue.addressVizValue) &&
        Objects.equals(this.addressMrzvalue, dataValidationValue.addressMrzvalue) &&
        Objects.equals(this.addressDocConfidence, dataValidationValue.addressDocConfidence);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idDocVizValue, idDocMrzValue, idDocConfidence, addressVizValue, addressMrzvalue, addressDocConfidence);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DataValidationValue {\n");

    sb.append("    idDocVizValue: ").append(toIndentedString(idDocVizValue)).append("\n");
    sb.append("    idDocMrzValue: ").append(toIndentedString(idDocMrzValue)).append("\n");
    sb.append("    idDocConfidence: ").append(toIndentedString(idDocConfidence)).append("\n");
    sb.append("    addressVizValue: ").append(toIndentedString(addressVizValue)).append("\n");
    sb.append("    addressMrzvalue: ").append(toIndentedString(addressMrzvalue)).append("\n");
    sb.append("    addressDocConfidence: ").append(toIndentedString(addressDocConfidence)).append("\n");
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

