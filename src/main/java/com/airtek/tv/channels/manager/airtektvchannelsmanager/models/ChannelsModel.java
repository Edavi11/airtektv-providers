package com.airtek.tv.channels.manager.airtektvchannelsmanager.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@ToString
public class ChannelsModel {
    
    private Integer id;
    private String title;

    @JsonProperty(value = "fk_category")
    private Integer fkCategory;

    private String url;

    @JsonProperty(value = "backup_url")
    private String backupUrl;

    private String thumbnail;


    public ChannelsModel() {
    }

    public ChannelsModel(String title, Integer fkCategory, String url, String backupUrl, String thumbnail) {
        this.title = title;
        this.fkCategory = fkCategory;
        this.url = url;
        this.backupUrl = backupUrl;
        this.thumbnail = thumbnail;
    }

    public ChannelsModel(Integer id, String title,Integer fkCategory, String url, String backupUrl, String thumbnail) {
        this.id = id;
        this.title = title;
        this.fkCategory = fkCategory;
        this.url = url;
        this.backupUrl = backupUrl;
        this.thumbnail = thumbnail;
    }
}
