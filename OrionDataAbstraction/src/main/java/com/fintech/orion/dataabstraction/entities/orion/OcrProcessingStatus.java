package com.fintech.orion.dataabstraction.entities.orion;
// Generated Dec 17, 2016 2:50:34 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * OcrProcessingStatus generated by hbm2java
 */
@Entity
@Table(name="ocr_processing_status"
)
public class OcrProcessingStatus  implements java.io.Serializable {


     private int id;
     private String status;
     private Set<OcrProcess> ocrProcesses = new HashSet<OcrProcess>(0);

    public OcrProcessingStatus() {
    }

	
    public OcrProcessingStatus(int id) {
        this.id = id;
    }
    public OcrProcessingStatus(int id, String status, Set<OcrProcess> ocrProcesses) {
       this.id = id;
       this.status = status;
       this.ocrProcesses = ocrProcesses;
    }
   
     @Id 

    
    @Column(name="ID", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    
    @Column(name="STATUS", length=45)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="ocrProcessingStatus")
    public Set<OcrProcess> getOcrProcesses() {
        return this.ocrProcesses;
    }
    
    public void setOcrProcesses(Set<OcrProcess> ocrProcesses) {
        this.ocrProcesses = ocrProcesses;
    }




}


