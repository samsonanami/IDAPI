package com.fintech.orion.dataabstraction.entities.orion;
// Generated Oct 10, 2016 8:50:22 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ProcessType generated by hbm2java
 */
@Entity
@Table(name="process_type"
    ,catalog="orion"
)
public class ProcessType  implements java.io.Serializable {


     private int id;
     private String type;
     private Set<ProcessTypeLicense> processTypeLicenses = new HashSet<ProcessTypeLicense>(0);
     private Set<Process> processes = new HashSet<Process>(0);

    public ProcessType() {
    }

	
    public ProcessType(int id) {
        this.id = id;
    }
    public ProcessType(int id, String type, Set<ProcessTypeLicense> processTypeLicenses, Set<Process> processes) {
       this.id = id;
       this.type = type;
       this.processTypeLicenses = processTypeLicenses;
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

    
    @Column(name="TYPE", length=20)
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="processType")
    public Set<ProcessTypeLicense> getProcessTypeLicenses() {
        return this.processTypeLicenses;
    }
    
    public void setProcessTypeLicenses(Set<ProcessTypeLicense> processTypeLicenses) {
        this.processTypeLicenses = processTypeLicenses;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="processType")
    public Set<Process> getProcesses() {
        return this.processes;
    }
    
    public void setProcesses(Set<Process> processes) {
        this.processes = processes;
    }




}


