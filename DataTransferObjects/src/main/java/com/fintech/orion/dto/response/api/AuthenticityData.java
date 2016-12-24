package com.fintech.orion.dto.response.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * AuthenticityData
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-12-18T09:12:11.427Z")

public class AuthenticityData   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("match")
  private Boolean match = null;

  @JsonProperty("confidence")
  private Integer confidence = null;

  public AuthenticityData id(String id) {
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

  public AuthenticityData match(Boolean match) {
    this.match = match;
    return this;
  }

   /**
   * Get match
   * @return match
  **/
  @ApiModelProperty(value = "")
  public Boolean getMatch() {
    return match;
  }

  public void setMatch(Boolean match) {
    this.match = match;
  }

  public AuthenticityData confidence(Integer confidence) {
    this.confidence = confidence;
    return this;
  }

   /**
   * Get confidence
   * @return confidence
  **/
  @ApiModelProperty(value = "")
  public Integer getConfidence() {
    return confidence;
  }

  public void setConfidence(Integer confidence) {
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
    AuthenticityData authenticityData = (AuthenticityData) o;
    return Objects.equals(this.id, authenticityData.id) &&
        Objects.equals(this.match, authenticityData.match) &&
        Objects.equals(this.confidence, authenticityData.confidence);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, match, confidence);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AuthenticityData {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    match: ").append(toIndentedString(match)).append("\n");
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

