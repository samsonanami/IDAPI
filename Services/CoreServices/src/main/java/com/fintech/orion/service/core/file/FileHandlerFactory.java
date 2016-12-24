package com.fintech.orion.service.core.file;

/**
 * Created by sasitha on 12/18/16.
 *
 */
public class FileHandlerFactory {

    public FileHandler getFileHandler(FileStorage storage){
        FileHandler fileHandler = null;
        switch (storage){
            case LOCAL:
                fileHandler = new LocalFileHandler();
                break;
        }
        return fileHandler;
    }
}
