package com.fintech.orion.dataabstraction.entities.orion;
// Generated Dec 25, 2016 10:54:56 AM by Hibernate Tools 4.3.1


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
 * ProcessTypeLicense generated by hbm2java
 */
@Entity
@Table(name="process_type_license"
)
public class ProcessTypeLicense  implements java.io.Serializable {


     private Integer id;
     private License license;
     private ProcessType processType;

    public ProcessTypeLicense() {
    }

    public ProcessTypeLicense(License license, ProcessType processType) {
       this.license = license;
       this.processType = processType;
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
    @JoinColumn(name="LICENSE", nullable=false)
    public License getLicense() {
        return this.license;
    }
    
    public void setLicense(License license) {
        this.license = license;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PROCESS_TYPE", nullable=false)
    public ProcessType getProcessType() {
        return this.processType;
    }
    
    public void setProcessType(ProcessType processType) {
        this.processType = processType;
    }




}


