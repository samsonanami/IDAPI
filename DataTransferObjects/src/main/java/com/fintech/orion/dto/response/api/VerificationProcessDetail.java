package com.fintech.orion.dto.response.api;

import java.util.List;

public class VerificationProcessDetail {

    private String verificationProcessName;

    private List<ImageDetail> imageDetails;


    public String getVerificationProcessName() {
        return verificationProcessName;
    }

    public void setVerificationProcessName(String verificationProcessName) {
        this.verificationProcessName = verificationProcessName;
    }

    public List<ImageDetail> getImageDetails() {
        return imageDetails;
    }

    public void setImageDetails(List<ImageDetail> imageDetails) {
        this.imageDetails = imageDetails;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VerificationProcessDetail)) {
            return false;
        }

        VerificationProcessDetail that = (VerificationProcessDetail) o;

        if (getVerificationProcessName() != null ? !getVerificationProcessName().equals(that.getVerificationProcessName()) : that.getVerificationProcessName() != null){
            return false;
        }
        return getImageDetails() != null ? getImageDetails().equals(that.getImageDetails()) : that.getImageDetails() == null;
    }

    @Override
    public int hashCode() {
        int result = getVerificationProcessName() != null ? getVerificationProcessName().hashCode() : 0;
        result = 31 * result + (getImageDetails() != null ? getImageDetails().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class VerificationProcessDetail{ \n");
        sb.append("     verificationProcessName:").append(toIndentedString(verificationProcessName)).append("\n");

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
