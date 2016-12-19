package com.fintech.orion.service.core.file;

import com.fintech.orion.dataabstraction.helper.GenerateUUID;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by sasitha on 12/18/16.
 *
 */
@Service
public class FileHandlerService implements FileHandlerServiceInterface{

    @Override
    public String persistFile(MultipartFile file, FileStorage storage, String filePath) throws IOException {
        FileHandlerFactory fileHandlerFactory = new FileHandlerFactory();
        FileHandler fileHandler = fileHandlerFactory.getFileHandler(storage);

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        String fileName = getFileName(extension);
        fileHandler.saveFile(filePath, fileName, file);
        return fileName;
    }

    private String getFileName(String extension){
        String uuidNumber = GenerateUUID.uuidNumber();
        return  uuidNumber + "." + extension;
    }
}
