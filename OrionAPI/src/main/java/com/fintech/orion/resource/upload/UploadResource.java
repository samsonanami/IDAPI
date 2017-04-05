package com.fintech.orion.resource.upload;

import com.fintech.orion.resource.upload.exception.UploadResourceException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by TharinduMP on 3/29/2017.
 */
public class UploadResource {

    private MultipartFile multipartFile;
    private String contentType;

    public UploadResource(MultipartFile multipartFile, String contentType) {
        if(multipartFile == null || contentType == null) {
            throw new UploadResourceException("Required Resource and/or Content Type Not Provided");
        }
        this.multipartFile = multipartFile;
        this.contentType = contentType;
    }

    public String getResourceContentType() {
        return contentType;
    }

    public String getResourceExtension() {
        return FilenameUtils.getExtension(multipartFile.getOriginalFilename().toLowerCase());
    }

    public boolean isResourceNull() {
        return multipartFile == null;
    }

    public long getResourceSize() {
        return multipartFile.getSize();
    }

    public byte[] getResourceBytes() throws IOException {
        return multipartFile.getBytes();
    }
}
