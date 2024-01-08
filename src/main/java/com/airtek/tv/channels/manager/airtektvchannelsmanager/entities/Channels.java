package com.airtek.tv.channels.manager.airtektvchannelsmanager.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "channels")
public class Channels implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "fk_category",nullable = false)
    private Categories fkCategory;

    @Column(nullable = false, length = 100)
    private String url;

    @Column(name = "backup_url", length = 100)
    private String backupUrl;

    @Column(name = "thumbnail", length = 50)
    private String thumbnail;

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

    public Channels() {
    }

    public Channels(String title, Categories fkCategory, String url, String backupUrl, String thumbnail) {
        this.title = title;
        this.fkCategory = fkCategory;
        this.url = url;
        this.backupUrl = backupUrl;
        this.thumbnail = thumbnail;
    }

    public Channels(Integer id, String title, Categories fkCategory, String url, String backupUrl, String thumbnail) {
        this.id = id;
        this.title = title;
        this.fkCategory = fkCategory;
        this.url = url;
        this.backupUrl = backupUrl;
        this.thumbnail = thumbnail;
    }

    @Override
    public Channels clone() {
        try {
            
            Channels cloned = (Channels) super.clone();

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
