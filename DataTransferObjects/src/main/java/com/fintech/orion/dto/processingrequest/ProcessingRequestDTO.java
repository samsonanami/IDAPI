package com.fintech.orion.dto.processingrequest;

import com.fintech.orion.dto.client.ClientDTO;
import com.fintech.orion.dto.process.ProcessDTO;

import java.util.Date;
import java.util.List;

/**
 * Created by ChathurangaRW on 10/17/2016.
 */
public class ProcessingRequestDTO {

    private Integer id;
    private ClientDTO clientDTO;
    private Date receivedOn;
    private String processingRequestIdentificationCode;
    private List<ProcessDTO> processes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ClientDTO getClientDTO() {
        return clientDTO;
    }

    public void setClientDTO(ClientDTO clientDTO) {
        this.clientDTO = clientDTO;
    }

    public Date getReceivedOn() {
        return receivedOn;
    }

    public void setReceivedOn(Date receivedOn) {
        this.receivedOn = receivedOn;
    }

    public String getProcessingRequestIdentificationCode() {
        return processingRequestIdentificationCode;
    }

    public void setProcessingRequestIdentificationCode(String processingRequestIdentificationCode) {
        this.processingRequestIdentificationCode = processingRequestIdentificationCode;
    }

    public List<ProcessDTO> getProcesses() {
        return processes;
    }

    public void setProcesses(List<ProcessDTO> processes) {
        this.processes = processes;
    }
}
