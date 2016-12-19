package com.fintech.orion.dto.response.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * FieldData
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-12-18T09:12:11.427Z")

public class FieldData   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("value")
  private List<FieldDataValue> value = new ArrayList<FieldDataValue>();

  @JsonProperty("comparison")
  private List<FieldDataComparision> comparison = new ArrayList<FieldDataComparision>();

  public FieldData id(String id) {
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

  public FieldData value(List<FieldDataValue> value) {
    this.value = value;
    return this;
  }

  public FieldData addValueItem(FieldDataValue valueItem) {
    this.value.add(valueItem);
    return this;
  }

   /**
   * Get value
   * @return value
  **/
  @ApiModelProperty(value = "")
  public List<FieldDataValue> getValue() {
    return value;
  }

  public void setValue(List<FieldDataValue> value) {
    this.value = value;
  }

  public FieldData comparison(List<FieldDataComparision> comparison) {
    this.comparison = comparison;
    return this;
  }

  public FieldData addComparisonItem(FieldDataComparision comparisonItem) {
    this.comparison.add(comparisonItem);
    return this;
  }

   /**
   * Get comparison
   * @return comparison
  **/
  @ApiModelProperty(value = "")
  public List<FieldDataComparision> getComparison() {
    return comparison;
  }

  public void setComparison(List<FieldDataComparision> comparison) {
    this.comparison = comparison;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FieldData fieldData = (FieldData) o;
    return Objects.equals(this.id, fieldData.id) &&
        Objects.equals(this.value, fieldData.value) &&
        Objects.equals(this.comparison, fieldData.comparison);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, value, comparison);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FieldData {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    comparison: ").append(toIndentedString(comparison)).append("\n");
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

