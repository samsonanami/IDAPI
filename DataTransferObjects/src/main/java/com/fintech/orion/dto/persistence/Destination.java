package com.fintech.orion.dto.persistence;

/**
 * Created by TharinduMP on 10/28/2016.
 * Destination Object used get file transfer information
 */
public class Destination {

    private String destinationPath;
    private String fileName;

    public String getDestinationPath() {
        return destinationPath;
    }

    public void setDestinationPath(String destinationPath) {
        this.destinationPath = destinationPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
