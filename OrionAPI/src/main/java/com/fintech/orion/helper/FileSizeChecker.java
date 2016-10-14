package com.fintech.orion.helper;

import org.springframework.web.multipart.MultipartFile;


public class FileSizeChecker implements FileSizeCheckerInterface {

    @Override
    public boolean checkSize(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        String extension = fileName.split("\\.")[1];
        if(("jpeg".equalsIgnoreCase(extension)) && (multipartFile.getSize() <= 5 * 1024 * 1024)){
            return true;
        } else if (("mp4".equalsIgnoreCase(extension)) && (multipartFile.getSize() <= 2 * 1024 * 1024)){
            return true;
        }
        return false;
    }

}
