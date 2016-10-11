package com.fintech.orion.helper;

import com.fintech.orion.model.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class FileUploadHandler implements FileUploadHandlerInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(SFileTransfer.class);

    @Autowired
    private Configuration configuration;

    @Autowired
    private String workingDir;

    @Autowired
    private FileSizeCheckerInterface fileSizeCheckerInterface;

    @Override
    public boolean upload(MultipartFile multipartFile, String filename) {
        try {
            File file = new File(filename);
            multipartFile.transferTo(file);

            if(fileSizeCheckerInterface.checkSize(multipartFile)) {

                SFileTransfer sFileTransfer = new SFileTransfer(configuration);

                LOGGER.info("Uploading File.");

                if (sFileTransfer.transferFile(file.getPath(), workingDir)) {
                    LOGGER.info("Uploading File Complete.");
                    return true;
                } else {
                    LOGGER.info("Uploading File Failed.");
                    return false;
                }
            } else {
                LOGGER.info("File Size Not Allowed.");
                return false;
            }
        } catch (IOException e) {
            LOGGER.error(e.toString());
            LOGGER.error("File Not Found." + e.getMessage());
            return false;
        }
    }

}
