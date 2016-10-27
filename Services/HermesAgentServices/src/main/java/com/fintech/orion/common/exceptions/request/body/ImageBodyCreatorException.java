package com.fintech.orion.common.exceptions.request.body;

/**
 * Created by TharinduMP on 10/6/2016.
 */
public class ImageBodyCreatorException extends Exception {
    public ImageBodyCreatorException(Exception e) {
        super(e);
    }

    public ImageBodyCreatorException(String message) {
        super(message);
    }
}
