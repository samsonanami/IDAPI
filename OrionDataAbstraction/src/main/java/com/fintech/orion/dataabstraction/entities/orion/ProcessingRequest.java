package com.fintech.orion.dataabstraction.entities.orion;
// Generated Sep 12, 2016 10:49:51 AM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ProcessingRequest generated by hbm2java
 */
@Entity
@Table(name="processing_request"
)
public class ProcessingRequest  implements java.io.Serializable {


     private int id;
     private Client client;
     private Date receivedOn;
     private Set<Process> processes = new HashSet<Process>(0);

    public ProcessingRequest() {
    }

	
    public ProcessingRequest(int id, Client client) {
        this.id = id;
        this.client = client;
    }
    public ProcessingRequest(int id, Client client, Date receivedOn, Set<Process> processes) {
       this.id = id;
       this.client = client;
       this.receivedOn = receivedOn;
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

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CLIENT", nullable=false)
    public Client getClient() {
        return this.client;
    }
    
    public void setClient(Client client) {
        this.client = client;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="RECEIVED_ON", length=19)
    public Date getReceivedOn() {
        return this.receivedOn;
    }
    
    public void setReceivedOn(Date receivedOn) {
        this.receivedOn = receivedOn;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="processingRequest")
    public Set<Process> getProcesses() {
        return this.processes;
    }
    
    public void setProcesses(Set<Process> processes) {
        this.processes = processes;
    }




}


