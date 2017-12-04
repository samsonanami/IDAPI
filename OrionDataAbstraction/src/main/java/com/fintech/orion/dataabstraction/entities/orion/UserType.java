package com.fintech.orion.dataabstraction.entities.orion;
// Generated Nov 24, 2017 6:37:53 PM by Hibernate Tools 4.3.5.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * UserType generated by hbm2java
 */
@Entity
@Table(name = "user_type", uniqueConstraints = @UniqueConstraint(columnNames = "TYPE"))
public class UserType implements java.io.Serializable {

    private int id;
    private String type;
    private Set<Client> clients = new HashSet<Client>(0);

    public UserType() {
    }

    public UserType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public UserType(int id, String type, Set<Client> clients) {
        this.id = id;
        this.type = type;
        this.clients = clients;
    }

    @Id

    @Column(name = "ID", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "TYPE", unique = true, nullable = false, length = 45)
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userType")
    public Set<Client> getClients() {
        return this.clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

}