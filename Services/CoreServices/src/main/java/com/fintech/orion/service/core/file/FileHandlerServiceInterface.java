package com.fintech.orion.service.core.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by sasitha on 12/18/16.
 *
 */
public interface FileHandlerServiceInterface {

    String persistFile(MultipartFile file, FileStorage storage, String filePath) throws IOException;
}
