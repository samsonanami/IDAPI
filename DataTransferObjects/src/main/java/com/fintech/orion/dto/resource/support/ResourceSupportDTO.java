package com.fintech.orion.dto.resource.support;

/**
 * Created by TharinduMP on 3/29/2017.
 */
public class ResourceSupportDTO {

    private String fileExtension;
    private long fileSizeLimit;

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public long getFileSizeLimit() {
        return fileSizeLimit;
    }

    public void setFileSizeLimit(long fileSizeLimit) {
        this.fileSizeLimit = fileSizeLimit;
    }
}
