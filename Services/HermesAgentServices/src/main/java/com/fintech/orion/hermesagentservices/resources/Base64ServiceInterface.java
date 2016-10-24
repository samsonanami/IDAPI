package com.fintech.orion.hermesagentservices.resources;

import com.fintech.orion.common.exceptions.resource.Base64ServiceException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by TharinduMP on 10/21/2016.
 * Base64ServiceInterface
 */
@FunctionalInterface
public interface Base64ServiceInterface {
    String fileToBase64(InputStream stream) throws IOException, Base64ServiceException;
}
