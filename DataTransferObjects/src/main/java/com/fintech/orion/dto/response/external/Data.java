package com.fintech.orion.dto.response.external;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Data
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-02T05:17:07.596Z")

public class Data implements Serializable {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("value")
    private List<DataValues> value = new ArrayList<DataValues>();

    @JsonProperty("comparison")
    private List<DataComparision> comparison = new ArrayList<DataComparision>();

    public Data id(String id) {
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

    public Data value(List<DataValues> value) {
        this.value = value;
        return this;
    }

    public Data addValueItem(DataValues valueItem) {
        this.value.add(valueItem);
        return this;
    }

    /**
     * Get value
     *
     * @return value
     **/
    @ApiModelProperty(value = "")
    public List<DataValues> getValue() {
        return value;
    }

    public void setValue(List<DataValues> value) {
        this.value = value;
    }

    public Data comparison(List<DataComparision> comparison) {
        this.comparison = comparison;
        return this;
    }

    public Data addComparisonItem(DataComparision comparisonItem) {
        this.comparison.add(comparisonItem);
        return this;
    }

    /**
     * Get comparison
     *
     * @return comparison
     **/
    @ApiModelProperty(value = "")
    public List<DataComparision> getComparison() {
        return comparison;
    }

    public void setComparison(List<DataComparision> comparison) {
        this.comparison = comparison;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Data other = (Data) obj;
        if (comparison == null) {
            if (other.comparison != null)
                return false;
        } else if (!comparison.equals(other.comparison))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((comparison == null) ? 0 : comparison.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Data {\n");

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
