package com.fintech.orion.resource.persistence;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * Created by TharinduMP on 3/30/2017.
 */
@Component
public class LocalStoragePersistence implements Persistence {

    private byte[] bytes;
    private String workingDir;
    private String fileName;

    public LocalStoragePersistence(byte[] bytes, String workingDir, String fileName) {
        this.bytes = bytes;
        this.workingDir = workingDir;
        this.fileName = fileName;
    }

    @Override
    public String persist() throws IOException {
        File file = new File(workingDir + fileName);
        FileUtils.writeByteArrayToFile(file, bytes);
        return file.getAbsolutePath();
    }

}
