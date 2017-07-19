package com.fintech.orion.dto.request;

/**
 * Created by TharinduMP on 10/14/2016.
 * RequestProcessDTO
 */
public class RequestProcessDTO {

    private Integer processId;
    private Integer processType;

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public Integer getProcessType() {
        return processType;
    }

    public void setProcessType(Integer processType) {
        this.processType = processType;
    }
}
