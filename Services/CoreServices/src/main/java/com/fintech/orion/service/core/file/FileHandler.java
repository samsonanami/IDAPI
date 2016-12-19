package com.fintech.orion.service.core.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by sasitha on 12/18/16.
 *
 */
public interface FileHandler {
    void saveFile(String filePath, String fileName, MultipartFile file) throws IOException;
}
