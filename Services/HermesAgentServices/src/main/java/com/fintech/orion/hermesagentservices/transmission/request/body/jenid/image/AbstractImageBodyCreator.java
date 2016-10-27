package com.fintech.orion.hermesagentservices.transmission.request.body.jenid.image;

import com.fintech.orion.hermesagentservices.transmission.payload.model.jenid.Image;

/**
 * Created by TharinduMP on 10/21/2016.
 * AbstractImageBodyCreator
 */
public abstract class AbstractImageBodyCreator {

    protected Image getDefaultImage() {
        Image image = new Image();
        image.setMmHeight(0);
        image.setMmWidth(0);
        image.setPixelHeight(0);
        image.setPixelWidth(0);
        image.setCropped(0);
        image.setSize(0);
        return image;
    }
}
