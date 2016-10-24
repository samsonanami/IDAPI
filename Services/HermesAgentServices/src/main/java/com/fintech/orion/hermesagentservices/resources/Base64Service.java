package com.fintech.orion.hermesagentservices.resources;

import com.fintech.orion.common.exceptions.resource.Base64ServiceException;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by TharinduMP on 10/21/2016.
 * Base64Service
 */
@Service
public class Base64Service implements Base64ServiceInterface {

    @Override
    public synchronized String fileToBase64(InputStream stream) throws Base64ServiceException {
        if (stream != null) {
            byte[] bytes;
            try {
                bytes = IOUtils.toByteArray(stream);
            } catch (IOException e) {
                throw new Base64ServiceException(e);
            }
            return java.util.Base64.getUrlEncoder().encodeToString(bytes);
        } else {
            throw new Base64ServiceException("InputStream is null");
        }
    }
}
