package com.airtek.tv.channels.manager.airtektvchannelsmanager.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
public class CategoriesModel {

    private Integer id;
    
    private String category_description;

    public CategoriesModel() {
    }

    public CategoriesModel(Integer id, String category_description) {
        this.id = id;
        this.category_description = category_description;
    }

    public CategoriesModel(String category_description) {
        this.category_description = category_description;
    }
}
