package com.fintech.orion.dataabstraction.entities.orion;
// Generated Dec 25, 2016 10:54:56 AM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * OcrProcessingRequest generated by hbm2java
 */
@Entity
@Table(name="ocr_processing_request"
    ,catalog="idapi"
)
public class OcrProcessingRequest  implements java.io.Serializable {


     private Integer id;
     private Date receivedOn;
     private String processingRequestCode;
     private Set<OcrProcess> ocrProcesses = new HashSet<OcrProcess>(0);

    public OcrProcessingRequest() {
    }

    public OcrProcessingRequest(Date receivedOn, String processingRequestCode, Set<OcrProcess> ocrProcesses) {
       this.receivedOn = receivedOn;
       this.processingRequestCode = processingRequestCode;
       this.ocrProcesses = ocrProcesses;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="ID", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="RECEIVED_ON", length=19)
    public Date getReceivedOn() {
        return this.receivedOn;
    }
    
    public void setReceivedOn(Date receivedOn) {
        this.receivedOn = receivedOn;
    }

    
    @Column(name="PROCESSING_REQUEST_CODE", length=50)
    public String getProcessingRequestCode() {
        return this.processingRequestCode;
    }
    
    public void setProcessingRequestCode(String processingRequestCode) {
        this.processingRequestCode = processingRequestCode;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="ocrProcessingRequest")
    public Set<OcrProcess> getOcrProcesses() {
        return this.ocrProcesses;
    }
    
    public void setOcrProcesses(Set<OcrProcess> ocrProcesses) {
        this.ocrProcesses = ocrProcesses;
    }




}


