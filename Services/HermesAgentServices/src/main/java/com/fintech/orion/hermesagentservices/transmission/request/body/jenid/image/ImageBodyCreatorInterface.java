package com.fintech.orion.hermesagentservices.transmission.request.body.jenid.image;

import com.fintech.orion.hermesagentservices.transmission.payload.model.jenid.Image;

import java.io.IOException;

/**
 * Created by TharinduMP on 10/21/2016.
 * ImageBodyCreatorInterface
 */
@FunctionalInterface
public interface ImageBodyCreatorInterface {
    Image createImage(String resourcePath) throws IOException;
}
