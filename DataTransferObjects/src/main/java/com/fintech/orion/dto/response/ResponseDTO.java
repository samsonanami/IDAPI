package com.fintech.orion.dto.response;

/**
 * Created by ChathurangaRW on 10/17/2016.
 */
public class ResponseDTO {

    private Integer processId;
    private String rawJson;
    private String extractedJson;

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public String getRawJson() {
        return rawJson;
    }

    public void setRawJson(String rawJson) {
        this.rawJson = rawJson;
    }

    public String getExtractedJson() {
        return extractedJson;
    }

    public void setExtractedJson(String extractedJson) {
        this.extractedJson = extractedJson;
    }
}
