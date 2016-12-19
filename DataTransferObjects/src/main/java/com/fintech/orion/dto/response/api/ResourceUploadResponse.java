package com.fintech.orion.dto.response.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * ResourceUploadResponse
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-12-18T09:12:11.427Z")

public class ResourceUploadResponse   {
  @JsonProperty("resourceReferenceCode")
  private String resourceReferenceCode = null;

  public ResourceUploadResponse resourceReferenceCode(String resourceReferenceCode) {
    this.resourceReferenceCode = resourceReferenceCode;
    return this;
  }

   /**
   * Get resourceReferenceCode
   * @return resourceReferenceCode
  **/
  @ApiModelProperty(value = "")
  public String getResourceReferenceCode() {
    return resourceReferenceCode;
  }

  public void setResourceReferenceCode(String resourceReferenceCode) {
    this.resourceReferenceCode = resourceReferenceCode;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResourceUploadResponse resourceUploadResponse = (ResourceUploadResponse) o;
    return Objects.equals(this.resourceReferenceCode, resourceUploadResponse.resourceReferenceCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(resourceReferenceCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResourceUploadResponse {\n");

    sb.append("    resourceReferenceCode: ").append(toIndentedString(resourceReferenceCode)).append("\n");
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

