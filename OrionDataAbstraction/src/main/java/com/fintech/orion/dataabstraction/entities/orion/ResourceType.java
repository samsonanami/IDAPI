package com.fintech.orion.dataabstraction.entities.orion;
// Generated Nov 24, 2016 6:26:52 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ResourceType generated by hbm2java
 */
@Entity
@Table(name="resource_type"
    ,catalog="orion"
)
public class ResourceType  implements java.io.Serializable {


     private int id;
     private String type;
     private Set<Resource> resources = new HashSet<Resource>(0);

    public ResourceType() {
    }

	
    public ResourceType(int id) {
        this.id = id;
    }
    public ResourceType(int id, String type, Set<Resource> resources) {
       this.id = id;
       this.type = type;
       this.resources = resources;
    }
   
     @Id 

    
    @Column(name="ID", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    
    @Column(name="TYPE", length=10)
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="resourceType")
    public Set<Resource> getResources() {
        return this.resources;
    }
    
    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }




}


