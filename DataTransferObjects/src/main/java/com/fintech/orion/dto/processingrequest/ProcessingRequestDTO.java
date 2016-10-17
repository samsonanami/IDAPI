package com.fintech.orion.dto.processingrequest;

import com.fintech.orion.dto.client.ClientDTO;

import java.util.Date;

/**
 * Created by ChathurangaRW on 10/17/2016.
 */
public class ProcessingRequestDTO {

    private Integer id;
    private ClientDTO clientDTO;
    private Date receivedOn;
    private String processingRequestIdentificationCode;

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
}
