package com.airtek.tv.channels.manager.airtektvchannelsmanager.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProvidersModel {

    @JsonProperty
    private Integer id;

    @JsonProperty
    private String provider_description;

    @JsonProperty
    private String version;
    
    @JsonProperty
    private String next_version;
    
    @JsonProperty
    private String demo_version;

    @JsonProperty
    private Boolean is_active;

    public ProvidersModel() {
    }

    // insert
    public ProvidersModel(String provider_description, String version, String next_version, String demo_version,
            Boolean is_active) {
        this.provider_description = provider_description;
        this.version = version;
        this.next_version = next_version;
        this.demo_version = demo_version;
        this.is_active = is_active;
    }

    // delete
    public ProvidersModel(Integer id) {
        this.id = id;
    }

    // update
    public ProvidersModel(Integer id, String provider_description, String version, String next_version,
            String demo_version, Boolean is_active) {
        this.id = id;
        this.provider_description = provider_description;
        this.version = version;
        this.next_version = next_version;
        this.demo_version = demo_version;
        this.is_active = is_active;
    }

    @Override
    public String toString() {
        return "ProvidersModel [id=" + id + ", provider_description=" + provider_description + ", version=" + version
                + ", next_version=" + next_version + ", demo_version=" + demo_version + ", is_active=" + is_active
                + "]";
    }
}
