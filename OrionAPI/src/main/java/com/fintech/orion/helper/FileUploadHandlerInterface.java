package com.fintech.orion.helper;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadHandlerInterface {

    boolean upload(MultipartFile multipartFile, String filename);

}
