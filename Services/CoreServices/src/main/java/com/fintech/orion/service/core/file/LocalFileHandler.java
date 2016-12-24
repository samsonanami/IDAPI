package com.fintech.orion.service.core.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by sasitha on 12/18/16.
 *
 */
public class LocalFileHandler implements FileHandler{

    @Override
    public void saveFile(String filePath, String fileName, MultipartFile file) throws IOException {
        File newFile = new File(filePath + fileName);
        file.transferTo(newFile);
    }
}
