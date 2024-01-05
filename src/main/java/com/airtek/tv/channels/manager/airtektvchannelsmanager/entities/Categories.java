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
@Table(name = "categories")
public class Categories implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String category_description;

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

    public Categories() {
    }

    public Categories(Integer id, String category_description) {
        this.id = id;
        this.category_description = category_description;
    }

    public Categories(String category_description) {
        this.category_description = category_description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory_description() {
        return category_description;
    }

    public void setCategory_description(String category_description) {
        this.category_description = category_description;
    }

    @Override
    public Categories clone() {
        try {
            
            Categories cloned = (Categories) super.clone();

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
