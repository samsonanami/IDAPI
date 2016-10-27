package com.fintech.orion.hermesagentservices.resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by TharinduMP on 10/21/2016.
 * ResourceProviderServiceInterface
 */
@FunctionalInterface
public interface ResourceProviderServiceInterface {
    FileInputStream getResource(String path) throws FileNotFoundException;
}
