package com.fintech.orion.dto.response.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * VerificationRequestResponse
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-12-18T09:12:11.427Z")

public class VerificationRequestResponse   {
  @JsonProperty("processingRequestId")
  private String processingRequestId = null;

  public VerificationRequestResponse processingRequestId(String processingRequestId) {
    this.processingRequestId = processingRequestId;
    return this;
  }

   /**
   * Get processingRequestId
   * @return processingRequestId
  **/
  @ApiModelProperty(value = "")
  public String getProcessingRequestId() {
    return processingRequestId;
  }

  public void setProcessingRequestId(String processingRequestId) {
    this.processingRequestId = processingRequestId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VerificationRequestResponse verificationRequestResponse = (VerificationRequestResponse) o;
    return Objects.equals(this.processingRequestId, verificationRequestResponse.processingRequestId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(processingRequestId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VerificationRequestResponse {\n");

    sb.append("    processingRequestId: ").append(toIndentedString(processingRequestId)).append("\n");
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

