package com.fintech.orion.dataabstraction.entities.orion;
// Generated Sep 12, 2016 10:49:51 AM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Response generated by hbm2java
 */
@Entity
@Table(name="response"
)
public class Response  implements java.io.Serializable {


     private int id;
     private Process process;
     private String rawJson;
     private String extractedJson;

    public Response() {
    }

	
    public Response(int id, Process process) {
        this.id = id;
        this.process = process;
    }
    public Response(int id, Process process, String rawJson, String extractedJson) {
       this.id = id;
       this.process = process;
       this.rawJson = rawJson;
       this.extractedJson = extractedJson;
    }
   
     @Id 

    
    @Column(name="ID", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PROCESS", nullable=false)
    public Process getProcess() {
        return this.process;
    }
    
    public void setProcess(Process process) {
        this.process = process;
    }

    
    @Column(name="RAW_JSON")
    public String getRawJson() {
        return this.rawJson;
    }
    
    public void setRawJson(String rawJson) {
        this.rawJson = rawJson;
    }

    
    @Column(name="EXTRACTED_JSON")
    public String getExtractedJson() {
        return this.extractedJson;
    }
    
    public void setExtractedJson(String extractedJson) {
        this.extractedJson = extractedJson;
    }




}


