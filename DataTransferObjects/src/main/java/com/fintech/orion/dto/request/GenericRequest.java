package com.fintech.orion.dto.request;

import com.fintech.orion.dto.messaging.GenericMapMessage;

/**
 * Created by TharinduMP on 10/13/2016.
 * GenericRequest
 */
public class GenericRequest extends GenericMapMessage {

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
