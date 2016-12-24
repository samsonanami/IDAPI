package com.fintech.orion.dataabstraction.entities.orion;
// Generated Dec 17, 2016 2:50:34 PM by Hibernate Tools 4.3.1


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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * License generated by hbm2java
 */
@Entity
@Table(name="license"
)
public class License  implements java.io.Serializable {


     private Integer id;
     private Client client;
     private Date startDate;
     private Date endDate;
     private Integer currentRequestCount;
     private Integer maximumRequestCount;
     private Boolean enabled;
     private Boolean status;
     private String licenseKey;
     private Set<ProcessTypeLicense> processTypeLicenses = new HashSet<ProcessTypeLicense>(0);

    public License() {
    }

    public License(Client client, Date startDate, Date endDate, Integer currentRequestCount, Integer maximumRequestCount, Boolean enabled, Boolean status, String licenseKey, Set<ProcessTypeLicense> processTypeLicenses) {
       this.client = client;
       this.startDate = startDate;
       this.endDate = endDate;
       this.currentRequestCount = currentRequestCount;
       this.maximumRequestCount = maximumRequestCount;
       this.enabled = enabled;
       this.status = status;
       this.licenseKey = licenseKey;
       this.processTypeLicenses = processTypeLicenses;
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
    @JoinColumn(name="CLIENT")
    public Client getClient() {
        return this.client;
    }
    
    public void setClient(Client client) {
        this.client = client;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="START_DATE", length=10)
    public Date getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="END_DATE", length=10)
    public Date getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    
    @Column(name="CURRENT_REQUEST_COUNT")
    public Integer getCurrentRequestCount() {
        return this.currentRequestCount;
    }
    
    public void setCurrentRequestCount(Integer currentRequestCount) {
        this.currentRequestCount = currentRequestCount;
    }

    
    @Column(name="MAXIMUM_REQUEST_COUNT")
    public Integer getMaximumRequestCount() {
        return this.maximumRequestCount;
    }
    
    public void setMaximumRequestCount(Integer maximumRequestCount) {
        this.maximumRequestCount = maximumRequestCount;
    }

    
    @Column(name="ENABLED")
    public Boolean getEnabled() {
        return this.enabled;
    }
    
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    
    @Column(name="STATUS")
    public Boolean getStatus() {
        return this.status;
    }
    
    public void setStatus(Boolean status) {
        this.status = status;
    }

    
    @Column(name="LICENSE_KEY", length=50)
    public String getLicenseKey() {
        return this.licenseKey;
    }
    
    public void setLicenseKey(String licenseKey) {
        this.licenseKey = licenseKey;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="license")
    public Set<ProcessTypeLicense> getProcessTypeLicenses() {
        return this.processTypeLicenses;
    }
    
    public void setProcessTypeLicenses(Set<ProcessTypeLicense> processTypeLicenses) {
        this.processTypeLicenses = processTypeLicenses;
    }




}


