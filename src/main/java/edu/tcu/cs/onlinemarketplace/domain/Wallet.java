package edu.tcu.cs.onlinemarketplace.domain;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Wallet implements Serializable {
    @Id
    private String id;
    private int creditBalance;
    private String creationTime;
    private String lastModified;
    private Boolean status;

    public Wallet() {
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getCreditBalance() {
        return creditBalance;
    }
    public void setCreditBalance(int creditBalance) {
        this.creditBalance = creditBalance;
    }
    public String getCreationTime() {
        return creationTime;
    }
    public void setCreationTime(String string) {
        this.creationTime = string;
    }
    public String getLastModified() {
        return lastModified;
    }
    public void setLastModified(String string) {
        this.lastModified = string;
    }
    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
}
