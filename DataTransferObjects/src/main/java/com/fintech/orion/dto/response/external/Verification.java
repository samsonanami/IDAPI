package com.fintech.orion.dto.response.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * VerificationDetail
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-02T05:17:07.596Z")

public class Verification {
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Verification verificationDetail = (Verification) o;
        return Objects.equals(this.verificationProcessName, verificationDetail.verificationProcessName) &&
                Objects.equals(this.imageDetails, verificationDetail.imageDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(verificationProcessName, imageDetails);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class VerificationDetail {\n");

        sb.append("    verificationProcessName: ").append(toIndentedString(verificationProcessName)).append("\n");
        sb.append("    imageDetails: ").append(toIndentedString(imageDetails)).append("\n");
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

