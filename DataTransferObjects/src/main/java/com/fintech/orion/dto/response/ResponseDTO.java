package com.fintech.orion.dto.response;

import com.fintech.orion.dto.process.ProcessDTO;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ChathurangaRW on 10/17/2016.
 */
public class ResponseDTO {
    private int id;
    private String rawJson;
    private String extractedJson;
    private Set<ProcessDTO> processDTOs = new HashSet<ProcessDTO>(0);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Set<ProcessDTO> getProcessDTOs() {
        return processDTOs;
    }

    public void setProcessDTOs(Set<ProcessDTO> processDTOs) {
        this.processDTOs = processDTOs;
    }
}
