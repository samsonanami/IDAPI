package com.fintech.orion.dto.hermese.model.oracle.response;

/**
 * Created by sasitha on 1/12/17.
 * 
 */
public enum  OcrResponseType {
    PENDING("pending"), PROCESSING_STARTED("processing_started"),
    PROCESSING_FAILED("processing_failed"), PROCESSING_SUCCESSFUL("processing_successful");

    private  String name = "";
    OcrResponseType(String name) { this.name = name; }
    public String getName() { return name; }

}
