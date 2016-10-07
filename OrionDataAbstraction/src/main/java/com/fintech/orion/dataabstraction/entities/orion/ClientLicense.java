package com.fintech.orion.dataabstraction.entities.orion;
// Generated Oct 7, 2016 10:51:46 AM by Hibernate Tools 4.3.1


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
 * ClientLicense generated by hbm2java
 */
@Entity
@Table(name="client_license"
    ,catalog="orion"
)
public class ClientLicense  implements java.io.Serializable {


     private Integer id;
     private Client client;
     private License license;

    public ClientLicense() {
    }

    public ClientLicense(Client client, License license) {
       this.client = client;
       this.license = license;
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
    @JoinColumn(name="client_ID", nullable=false)
    public Client getClient() {
        return this.client;
    }
    
    public void setClient(Client client) {
        this.client = client;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="license_ID", nullable=false)
    public License getLicense() {
        return this.license;
    }
    
    public void setLicense(License license) {
        this.license = license;
    }




}


