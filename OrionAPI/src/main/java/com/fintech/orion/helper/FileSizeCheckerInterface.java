package com.fintech.orion.helper;

import org.springframework.web.multipart.MultipartFile;

public interface FileSizeCheckerInterface {

    boolean checkSize(MultipartFile multipartFile);

}
