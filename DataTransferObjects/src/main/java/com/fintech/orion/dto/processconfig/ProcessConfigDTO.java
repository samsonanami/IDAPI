package com.fintech.orion.dto.processconfig;

import com.fintech.orion.dto.processtype.ProcessTypeDTO;

/**
 * Created by ChathurangaRW on 10/18/2016.
 */
public class ProcessConfigDTO {

    private int processType;
    private String key;
    private ProcessTypeDTO processTypeDTO;
    private String value;

    public int getProcessType() {
        return processType;
    }

    public void setProcessType(int processType) {
        this.processType = processType;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ProcessTypeDTO getProcessTypeDTO() {
        return processTypeDTO;
    }

    public void setProcessTypeDTO(ProcessTypeDTO processTypeDTO) {
        this.processTypeDTO = processTypeDTO;
    }
}
