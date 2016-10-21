package com.fintech.orion.hermesagentservices.resources;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by TharinduMP on 10/21/2016.
 */
@Service
public class Base64Service implements Base64ServiceInterface {

    @Override
    public synchronized String fileToBase64(InputStream stream) throws IOException {
        byte[] bytes = IOUtils.toByteArray(stream);
        return java.util.Base64.getUrlEncoder().encodeToString(bytes);
    }
}
