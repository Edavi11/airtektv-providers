package com.airtek.tv.channels.manager.airtektvchannelsmanager.entities;

import java.util.Date;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "providers")
public class Providers implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "provider_description")
    private String providerDescription;

    @Column(nullable = false)
    private String version;

    @Column(nullable = false)
    private String next_version;

    @Column(nullable = false)
    private String demo_version;

    @Column(name = "is_active", nullable = false )
    @ColumnDefault("true")
    private Boolean isActive;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "deleted_at")
    private Date deletedAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        updatedAt = new Date();
        isActive = true;
    }
    
    public Providers() {
    }

    public Providers(String providerDescription, String version, String next_version, String demo_version) {
        this.providerDescription = providerDescription;
        this.version = version;
        this.next_version = next_version;
        this.demo_version = demo_version;
    }

    public Providers(String providerDescription, String version, String next_version, String demo_version, Boolean isActive) {
        this.providerDescription = providerDescription;
        this.version = version;
        this.next_version = next_version;
        this.demo_version = demo_version;
        this.isActive = isActive;
    }

    public Providers(Integer id, String providerDescription, String version, String next_version, String demo_version, Boolean isActive) {
        this.id = id;
        this.providerDescription = providerDescription;
        this.version = version;
        this.next_version = next_version;
        this.demo_version = demo_version;
        this.isActive = isActive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProviderDescription() {
        return providerDescription;
    }

    public void setProviderDescription(String providerDescription) {
        this.providerDescription = providerDescription;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getNext_version() {
        return next_version;
    }

    public void setNext_version(String next_version) {
        this.next_version = next_version;
    }

    public String getDemo_version() {
        return demo_version;
    }

    public void setDemo_version(String demo_version) {
        this.demo_version = demo_version;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public Providers clone() {
        try {
            Providers cloned = (Providers) super.clone();

            // Realizar copias profundas de objetos internos si es necesario
            cloned.createdAt = (createdAt != null) ? new Date(createdAt.getTime()) : null;
            cloned.updatedAt = (updatedAt != null) ? new Date(updatedAt.getTime()) : null;
            cloned.deletedAt = (deletedAt != null) ? new Date(deletedAt.getTime()) : null;

            return cloned;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
