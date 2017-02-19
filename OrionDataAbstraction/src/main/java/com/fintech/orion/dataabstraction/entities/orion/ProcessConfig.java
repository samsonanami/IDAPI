package com.fintech.orion.dataabstraction.entities.orion;
// Generated Feb 18, 2017 5:58:33 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ProcessConfig generated by hbm2java
 */
@Entity
@Table(name="process_config"
)
public class ProcessConfig  implements java.io.Serializable {


     private Integer id;
     private Client client;
     private ConfigurationKey configurationKey;
     private ProcessType processType;
     private String value;

    public ProcessConfig() {
    }

	
    public ProcessConfig(Client client, ConfigurationKey configurationKey, ProcessType processType) {
        this.client = client;
        this.configurationKey = configurationKey;
        this.processType = processType;
    }
    public ProcessConfig(Client client, ConfigurationKey configurationKey, ProcessType processType, String value) {
       this.client = client;
       this.configurationKey = configurationKey;
       this.processType = processType;
       this.value = value;
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
    @JoinColumn(name="CLIENT", nullable=false)
    public Client getClient() {
        return this.client;
    }
    
    public void setClient(Client client) {
        this.client = client;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="KEY", nullable=false)
    public ConfigurationKey getConfigurationKey() {
        return this.configurationKey;
    }
    
    public void setConfigurationKey(ConfigurationKey configurationKey) {
        this.configurationKey = configurationKey;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PROCESS_TYPE", nullable=false)
    public ProcessType getProcessType() {
        return this.processType;
    }
    
    public void setProcessType(ProcessType processType) {
        this.processType = processType;
    }

    
    @Column(name="VALUE", length=45)
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }




}


