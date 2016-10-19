package com.fintech.orion.dto.license;

import java.util.Date;

/**
 * Created by ChathurangaRW on 10/17/2016.
 */
public class LicenseDTO {

    private int id;
    private Date startDate;
    private Date endDate;
    private Integer requestCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Integer getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(Integer requestCount) {
        this.requestCount = requestCount;
    }
}
