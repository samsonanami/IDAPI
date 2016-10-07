package com.fintech.orion.dataabstraction.entities.orion;
// Generated Oct 7, 2016 10:51:46 AM by Hibernate Tools 4.3.1


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

/**
 * Resource generated by hbm2java
 */
@Entity
@Table(name="resource"
    ,catalog="orion"
)
public class Resource  implements java.io.Serializable {


     private Integer id;
     private Client client;
     private ResourceType resourceType;
     private String location;
     private String resourceIdentificationCode;
     private Set<ProcessResource> processResources = new HashSet<ProcessResource>(0);

    public Resource() {
    }

	
    public Resource(Client client, ResourceType resourceType, String location, String resourceIdentificationCode) {
        this.client = client;
        this.resourceType = resourceType;
        this.location = location;
        this.resourceIdentificationCode = resourceIdentificationCode;
    }
    public Resource(Client client, ResourceType resourceType, String location, String resourceIdentificationCode, Set<ProcessResource> processResources) {
       this.client = client;
       this.resourceType = resourceType;
       this.location = location;
       this.resourceIdentificationCode = resourceIdentificationCode;
       this.processResources = processResources;
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
    @JoinColumn(name="RESOURCE_TYPE", nullable=false)
    public ResourceType getResourceType() {
        return this.resourceType;
    }
    
    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    
    @Column(name="LOCATION", nullable=false, length=128)
    public String getLocation() {
        return this.location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }

    
    @Column(name="RESOURCE_IDENTIFICATION_CODE", nullable=false, length=40)
    public String getResourceIdentificationCode() {
        return this.resourceIdentificationCode;
    }
    
    public void setResourceIdentificationCode(String resourceIdentificationCode) {
        this.resourceIdentificationCode = resourceIdentificationCode;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="resource")
    public Set<ProcessResource> getProcessResources() {
        return this.processResources;
    }
    
    public void setProcessResources(Set<ProcessResource> processResources) {
        this.processResources = processResources;
    }




}


