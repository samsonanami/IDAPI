package com.fintech.orion.dto.license;

import com.fintech.orion.dto.client.ClientDTO;

import java.util.Date;

/**
 * Created by ChathurangaRW on 10/17/2016.
 */
public class LicenseDTO {

    private int id;
    private ClientDTO clientDTOt;
    private Date startDate;
    private Date endDate;
    private Integer currentRequestCount;
    private Integer maximumRequestCount;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ClientDTO getClientDTOt() {
        return clientDTOt;
    }

    public void setClientDTOt(ClientDTO clientDTOt) {
        this.clientDTOt = clientDTOt;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getCurrentRequestCount() {
        return currentRequestCount;
    }

    public void setCurrentRequestCount(Integer currentRequestCount) {
        this.currentRequestCount = currentRequestCount;
    }

    public Integer getMaximumRequestCount() {
        return maximumRequestCount;
    }

    public void setMaximumRequestCount(Integer maximumRequestCount) {
        this.maximumRequestCount = maximumRequestCount;
    }
}
