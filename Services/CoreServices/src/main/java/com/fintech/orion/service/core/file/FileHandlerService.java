package com.fintech.orion.service.core.file;

import com.fintech.orion.dataabstraction.helper.GenerateUUID;
import com.fintech.orion.exception.FileHandlerException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by sasitha on 12/18/16.
 *
 */
@Service
public class FileHandlerService implements FileHandlerServiceInterface{

    @Override
    public String persistFile(MultipartFile file, FileStorage storage, String filePath) throws FileHandlerException, IllegalArgumentException{
        Assert.notNull(file, "Multipart file should not be null");
        Assert.notNull(storage, "Storage should not be null");
        Assert.notNull(filePath, "File path should not be null");
        FileHandlerFactory fileHandlerFactory = new FileHandlerFactory();
        FileHandler fileHandler = fileHandlerFactory.getFileHandler(storage);

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        String fileName = getFileName(extension);
        try {
            fileHandler.saveFile(filePath, fileName, file);
        } catch (IOException e) {
            throw new FileHandlerException("Could not save file ", e);
        }
        return fileName;
    }

    private String getFileName(String extension){
        String uuidNumber = GenerateUUID.uuidNumber();
        return  uuidNumber + "." + extension;
    }
}
