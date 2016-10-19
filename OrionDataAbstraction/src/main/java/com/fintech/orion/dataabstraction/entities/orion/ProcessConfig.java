package com.fintech.orion.dataabstraction.entities.orion;
// Generated Oct 19, 2016 6:19:33 PM by Hibernate Tools 4.3.1


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ProcessConfig generated by hbm2java
 */
@Entity
@Table(name="process_config"
    ,catalog="orion"
)
public class ProcessConfig  implements java.io.Serializable {


     private ProcessConfigId id;
     private ProcessType processType;
     private String value;

    public ProcessConfig() {
    }

    public ProcessConfig(ProcessConfigId id, ProcessType processType, String value) {
       this.id = id;
       this.processType = processType;
       this.value = value;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="processType", column=@Column(name="PROCESS_TYPE", nullable=false) ), 
        @AttributeOverride(name="key", column=@Column(name="KEY", nullable=false, length=45) ) } )
    public ProcessConfigId getId() {
        return this.id;
    }
    
    public void setId(ProcessConfigId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PROCESS_TYPE", nullable=false, insertable=false, updatable=false)
    public ProcessType getProcessType() {
        return this.processType;
    }
    
    public void setProcessType(ProcessType processType) {
        this.processType = processType;
    }

    
    @Column(name="VALUE", nullable=false, length=45)
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }




}


