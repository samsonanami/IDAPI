package com.fintech.orion.dataabstraction.models;

public enum Status {
    PROCESSING_COMPLETE("ProcessingComplete"),
    PROCESSING_PENDING("ProcessingPending"),
    PROCESSING_REQUESTED("ProcessingRequested");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String toString(){
        return getStatus();
    }

}
