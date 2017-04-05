package com.fintech.orion.resource.file.name;

import com.fintech.orion.dataabstraction.helper.GenerateUUID;

/**
 * Created by TharinduMP on 4/4/2017.
 */
public class UuidFileName implements Filename {

    private String uuid;

    public UuidFileName() {
        uuid = GenerateUUID.uuidNumber();
    }

    @Override
    public String getUniqueFileName(String extension) {
        return uuid + "." + extension;
    }
}
