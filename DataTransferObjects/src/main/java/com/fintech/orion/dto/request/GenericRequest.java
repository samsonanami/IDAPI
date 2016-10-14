package com.fintech.orion.dto.request;

import com.fintech.orion.dto.messaging.GenericMapMessage;

/**
 * Created by TharinduMP on 10/13/2016.
 */
public class GenericRequest extends GenericMapMessage {

    private int processId;
    private int processType;

    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public int getProcessType() {
        return processType;
    }

    public void setProcessType(int processType) {
        this.processType = processType;
    }
}
