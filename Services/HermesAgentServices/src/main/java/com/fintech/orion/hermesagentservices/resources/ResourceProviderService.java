package com.fintech.orion.hermesagentservices.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by TharinduMP on 10/21/2016.
 * ResourceProviderService
 */
@Service
public class ResourceProviderService implements ResourceProviderServiceInterface {

    @Autowired
    private String resourcesDir;

    @Override
    public FileInputStream getResource(String path) throws FileNotFoundException {
        return new FileInputStream(resourcesDir + path);
    }
}
