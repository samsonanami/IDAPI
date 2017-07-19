package com.fintech.orion.dto.response.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * ContentQualityData
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-12-18T09:12:11.427Z")

public class ContentQualityData   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("compliance")
  private Integer compliance = null;

  public ContentQualityData id(String id) {
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

  public ContentQualityData compliance(Integer compliance) {
    this.compliance = compliance;
    return this;
  }

   /**
   * Get compliance
   * @return compliance
  **/
  @ApiModelProperty(value = "")
  public Integer getCompliance() {
    return compliance;
  }

  public void setCompliance(Integer compliance) {
    this.compliance = compliance;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ContentQualityData contentQualityData = (ContentQualityData) o;
    return Objects.equals(this.id, contentQualityData.id) &&
        Objects.equals(this.compliance, contentQualityData.compliance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, compliance);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ContentQualityData {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    compliance: ").append(toIndentedString(compliance)).append("\n");
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

