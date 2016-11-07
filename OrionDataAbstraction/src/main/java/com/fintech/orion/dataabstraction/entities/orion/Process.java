package com.fintech.orion.dataabstraction.entities.orion;
// Generated Nov 3, 2016 3:50:24 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Process generated by hbm2java
 */
@Entity
@Table(name="process"
)
public class Process  implements java.io.Serializable {


     private Integer id;
     private ProcessType processType;
     private ProcessingRequest processingRequest;
     private ProcessingStatus processingStatus;
     private Date requestSentOn;
     private Date responseReceivedOn;
     private String processIdentificationCode;
     private Set<Resource> resources = new HashSet<Resource>(0);
     private Response response;

    public Process() {
    }

	
    public Process(ProcessType processType, ProcessingRequest processingRequest, ProcessingStatus processingStatus, String processIdentificationCode) {
        this.processType = processType;
        this.processingRequest = processingRequest;
        this.processingStatus = processingStatus;
        this.processIdentificationCode = processIdentificationCode;
    }
    public Process(ProcessType processType, ProcessingRequest processingRequest, ProcessingStatus processingStatus, Date requestSentOn, Date responseReceivedOn, String processIdentificationCode, Set<Resource> resources, Response response) {
       this.processType = processType;
       this.processingRequest = processingRequest;
       this.processingStatus = processingStatus;
       this.requestSentOn = requestSentOn;
       this.responseReceivedOn = responseReceivedOn;
       this.processIdentificationCode = processIdentificationCode;
       this.resources = resources;
       this.response = response;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="ID", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PROCESS_TYPE", nullable=false)
    public ProcessType getProcessType() {
        return this.processType;
    }
    
    public void setProcessType(ProcessType processType) {
        this.processType = processType;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PROCESSING_REQUEST", nullable=false)
    public ProcessingRequest getProcessingRequest() {
        return this.processingRequest;
    }
    
    public void setProcessingRequest(ProcessingRequest processingRequest) {
        this.processingRequest = processingRequest;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PROCESSING_STATUS", nullable=false)
    public ProcessingStatus getProcessingStatus() {
        return this.processingStatus;
    }
    
    public void setProcessingStatus(ProcessingStatus processingStatus) {
        this.processingStatus = processingStatus;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="REQUEST_SENT_ON", length=19)
    public Date getRequestSentOn() {
        return this.requestSentOn;
    }
    
    public void setRequestSentOn(Date requestSentOn) {
        this.requestSentOn = requestSentOn;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="RESPONSE_RECEIVED_ON", length=19)
    public Date getResponseReceivedOn() {
        return this.responseReceivedOn;
    }
    
    public void setResponseReceivedOn(Date responseReceivedOn) {
        this.responseReceivedOn = responseReceivedOn;
    }

    
    @Column(name="PROCESS_IDENTIFICATION_CODE", nullable=false, length=40)
    public String getProcessIdentificationCode() {
        return this.processIdentificationCode;
    }
    
    public void setProcessIdentificationCode(String processIdentificationCode) {
        this.processIdentificationCode = processIdentificationCode;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="process")
    public Set<Resource> getResources() {
        return this.resources;
    }
    
    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }

@OneToOne(fetch=FetchType.LAZY, mappedBy="process")
    public Response getResponse() {
        return this.response;
    }
    
    public void setResponse(Response response) {
        this.response = response;
    }




}


