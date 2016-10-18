package com.fintech.orion.dto.processingstatus;

import com.fintech.orion.dto.process.ProcessDTO;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ChathurangaRW on 10/17/2016.
 */
public class ProcessingStatusDTO {

    private int id;
    private String status;
    private String description;
    private Set<ProcessDTO> processDTOs = new HashSet<ProcessDTO>(0);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ProcessDTO> getProcessDTOs() {
        return processDTOs;
    }

    public void setProcessDTOs(Set<ProcessDTO> processDTOs) {
        this.processDTOs = processDTOs;
    }
}
