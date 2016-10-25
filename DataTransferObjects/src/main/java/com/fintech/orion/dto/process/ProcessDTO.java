package com.fintech.orion.dto.process;

import com.fintech.orion.dto.processingstatus.ProcessingStatusDTO;
import com.fintech.orion.dto.processtype.ProcessTypeDTO;
import com.fintech.orion.dto.response.ResponseDTO;

import java.util.Date;

/**
 * Created by TharinduMP on 10/14/2016.
 */
public class ProcessDTO {

    private Integer id;
    private ProcessTypeDTO processTypeDTO;
    private ProcessingStatusDTO processingStatusDTO;
    private ResponseDTO responseDTO;
    private Date requestSentOn;
    private Date responseReceivedOn;
    private String processIdentificationCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProcessTypeDTO getProcessTypeDTO() {
        return processTypeDTO;
    }

    public void setProcessTypeDTO(ProcessTypeDTO processTypeDTO) {
        this.processTypeDTO = processTypeDTO;
    }

    public ProcessingStatusDTO getProcessingStatusDTO() {
        return processingStatusDTO;
    }

    public void setProcessingStatusDTO(ProcessingStatusDTO processingStatusDTO) {
        this.processingStatusDTO = processingStatusDTO;
    }

    public ResponseDTO getResponseDTO() {
        return responseDTO;
    }

    public void setResponseDTO(ResponseDTO responseDTO) {
        this.responseDTO = responseDTO;
    }

    public Date getRequestSentOn() {
        return requestSentOn;
    }

    public void setRequestSentOn(Date requestSentOn) {
        this.requestSentOn = requestSentOn;
    }

    public Date getResponseReceivedOn() {
        return responseReceivedOn;
    }

    public void setResponseReceivedOn(Date responseReceivedOn) {
        this.responseReceivedOn = responseReceivedOn;
    }

    public String getProcessIdentificationCode() {
        return processIdentificationCode;
    }

    public void setProcessIdentificationCode(String processIdentificationCode) {
        this.processIdentificationCode = processIdentificationCode;
    }
}
