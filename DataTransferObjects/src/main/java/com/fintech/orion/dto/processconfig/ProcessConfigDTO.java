package com.fintech.orion.dto.processconfig;

/**
 * Created by ChathurangaRW on 10/18/2016.
 */
public class ProcessConfigDTO {

    private int processType;
    private String key;
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
}
