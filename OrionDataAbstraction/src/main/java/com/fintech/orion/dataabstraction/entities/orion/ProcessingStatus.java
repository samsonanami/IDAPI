package com.fintech.orion.dataabstraction.entities.orion;
// Generated Nov 3, 2016 3:50:24 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ProcessingStatus generated by hbm2java
 */
@Entity
@Table(name="processing_status"
)
public class ProcessingStatus  implements java.io.Serializable {


     private int id;
     private String status;
     private String description;
     private Set<Process> processes = new HashSet<Process>(0);

    public ProcessingStatus() {
    }

	
    public ProcessingStatus(int id, String status, String description) {
        this.id = id;
        this.status = status;
        this.description = description;
    }
    public ProcessingStatus(int id, String status, String description, Set<Process> processes) {
       this.id = id;
       this.status = status;
       this.description = description;
       this.processes = processes;
    }
   
     @Id 

    
    @Column(name="ID", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    
    @Column(name="STATUS", nullable=false, length=45)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    
    @Column(name="DESCRIPTION", nullable=false, length=100)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="processingStatus")
    public Set<Process> getProcesses() {
        return this.processes;
    }
    
    public void setProcesses(Set<Process> processes) {
        this.processes = processes;
    }




}


