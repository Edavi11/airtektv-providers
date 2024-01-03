package com.airtek.tv.channels.manager.airtektvchannelsmanager.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "providers")
public class Providers {

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

    @Column(name = "is_active", nullable = false)
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
    }

    // insert
    public Providers(String providerDescription, String version, String next_version, String demo_version,
            Boolean isActive) {
        this.providerDescription = providerDescription;
        this.version = version;
        this.next_version = next_version;
        this.demo_version = demo_version;
        this.isActive = isActive;
    }

    // update
    public Providers(Integer id, String providerDescription, String version, String next_version, String demo_version,
            Boolean isActive) {
        this.id = id;
        this.providerDescription = providerDescription;
        this.version = version;
        this.next_version = next_version;
        this.demo_version = demo_version;
        this.isActive = isActive;
    }

    // delete
    public Providers(Integer id) {
        this.id = id;
    }
}
