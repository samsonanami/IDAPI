package com.fintech.orion.helper;

/**
 * Validate extensions for specific types
 */
public class FileExtensionValidator implements FileExtensionValidatorInterface {

    private final String JPEG_FILE_FORMAT = "jpeg";
    private final String MP4_FILE_FORMAT = "mp4";

    /**
     *
     * @param extension: extension of the file
     * @return extension is a valid one or not
     */
    @Override
    public boolean validate(String extension) {
        return extension.equalsIgnoreCase(JPEG_FILE_FORMAT) || extension.equalsIgnoreCase(MP4_FILE_FORMAT);
    }
}
