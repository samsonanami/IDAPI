package com.fintech.orion.dto.response.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * ImageDetail
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-02T05:17:07.596Z")

public class Image {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("imageName")
    private String imageName = null;

    public Image id(String id) {
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

    public Image imageName(String imageName) {
        this.imageName = imageName;
        return this;
    }

    /**
     * Get imageName
     *
     * @return imageName
     **/
    @ApiModelProperty(value = "")
    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Image imageDetail = (Image) o;
        return Objects.equals(this.id, imageDetail.id) &&
                Objects.equals(this.imageName, imageDetail.imageName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imageName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ImageDetail {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    imageName: ").append(toIndentedString(imageName)).append("\n");
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

