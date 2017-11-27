package com.fintech.orion.dto.response.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * VerificationDetail
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-02T05:17:07.596Z")

public class Verification implements Serializable {
    @JsonProperty("verificationProcessName")
    private String verificationProcessName = null;

    @JsonProperty("imageDetails")
    private List<Image> imageDetails = new ArrayList<Image>();

    public Verification verificationProcessName(String verificationProcessName) {
        this.verificationProcessName = verificationProcessName;
        return this;
    }

    /**
     * Get verificationProcessName
     *
     * @return verificationProcessName
     **/
    @ApiModelProperty(value = "")
    public String getVerificationProcessName() {
        return verificationProcessName;
    }

    public void setVerificationProcessName(String verificationProcessName) {
        this.verificationProcessName = verificationProcessName;
    }

    public Verification imageDetails(List<Image> imageDetails) {
        this.imageDetails = imageDetails;
        return this;
    }

    public Verification addImageDetailsItem(Image imageDetailsItem) {
        this.imageDetails.add(imageDetailsItem);
        return this;
    }

    /**
     * Get imageDetails
     *
     * @return imageDetails
     **/
    @ApiModelProperty(value = "")
    public List<Image> getImageDetails() {
        return imageDetails;
    }

    public void setImageDetails(List<Image> imageDetails) {
        this.imageDetails = imageDetails;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Verification other = (Verification) obj;
        if (imageDetails == null) {
            if (other.imageDetails != null)
                return false;
        } else if (!imageDetails.equals(other.imageDetails))
            return false;
        if (verificationProcessName == null) {
            if (other.verificationProcessName != null)
                return false;
        } else if (!verificationProcessName.equals(other.verificationProcessName))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((imageDetails == null) ? 0 : imageDetails.hashCode());
        result = prime * result + ((verificationProcessName == null) ? 0 : verificationProcessName.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Verification [verificationProcessName=" + verificationProcessName + ", imageDetails=" + imageDetails
                + "]";
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
