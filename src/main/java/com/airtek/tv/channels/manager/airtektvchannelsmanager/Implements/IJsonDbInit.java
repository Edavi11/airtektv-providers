package com.airtek.tv.channels.manager.airtektvchannelsmanager.Implements;

import java.util.List;
import java.util.Map;

public interface IJsonDbInit {

    void insertInitialProviders();

    void insertInitialCategories();
    
    List<Map<String,Object>> getAllProviders();

    List<Map<String,Object>> getAllCateogies();

    List<Map<String,Object>> getAllTitles();

    List<Map<String,Object>> getAllUrls();

    List<Map<String,Object>> getAllThumbnail();

    List<Map<String,Object>> getAllBackupUrl();
}
