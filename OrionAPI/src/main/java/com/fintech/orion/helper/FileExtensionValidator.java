package com.fintech.orion.helper;

public class FileExtensionValidator implements FileExtensionValidatorInterface {

    private static final String JPEG_FILE_FORMAT = "jpeg";
    private static final String MP4_FILE_FORMAT = "mp4";

    @Override
    public boolean validate(String extension) {
        return extension.equalsIgnoreCase(JPEG_FILE_FORMAT) || extension.equalsIgnoreCase(MP4_FILE_FORMAT);
    }
}
