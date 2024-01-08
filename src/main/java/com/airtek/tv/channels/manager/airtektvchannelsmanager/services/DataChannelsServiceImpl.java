package com.airtek.tv.channels.manager.airtektvchannelsmanager.services;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.airtek.tv.channels.manager.airtektvchannelsmanager.entities.Channels;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.entities.DemoChannels;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.entities.Providers;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.entities.TestChannels;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.entities.Categories;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.repositories.ChannelsRepository;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.repositories.ProviderRepository;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.repositories.CategoriesRepository;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.repositories.DemoChannelsRepository;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.repositories.TestChannelsRepository;

@Service
public class DataChannelsServiceImpl {

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private ChannelsRepository channelsRepository;

    @Autowired
    private TestChannelsRepository testChannelsRepository;

    @Autowired
    private DemoChannelsRepository demoChannelsRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;
    

    public Map<String, Object> getDataChannelsByProviderAndVersion(String provider, String version){

        Map<String, Object> response = new HashMap<>();
        List<Map<String, Object>> channeList = new ArrayList<>();

        Optional<Providers> optionalProvidersProd = providerRepository.getChannelsByProviderAndVersion(provider,version);
        Optional<Providers> optionalProvidersTest = providerRepository.getChannelsByProviderAndNextVersion(provider,version);
        Optional<Providers> optionalProvidersDemo = providerRepository.getChannelsByProviderAndDemoVersion(provider,version);

        
        if (optionalProvidersProd.isPresent()) {
            List<Channels> channels = channelsRepository.findAllChannels();
            
            if (!channels.isEmpty()) {

                channels.forEach(element -> {
                    Map<String, Object> data = new HashMap<>();
                    Map<String, String> innerMap = new HashMap<>();
                    
                    Optional<Categories> optionalCategory = categoriesRepository.findById(element.getFkCategory().getId());
                    Categories category = optionalCategory.get();
                    
                    data.put("id",element.getId());
                    data.put("title",element.getTitle());
                    data.put("url", element.getUrl());
                    data.put("backup_url", element.getBackupUrl());
                    data.put("thumbnail", element.getThumbnail());
                    innerMap.put("tags", category.getCategory_description());
                    data.put("categories", innerMap);
                    channeList.add(data);
                });

                response.put("data", channeList);
            } else {
                response.put("msg", "There are no records in the table");
            }
        }

        if (optionalProvidersTest.isPresent()) {
            List<TestChannels> channels = testChannelsRepository.findAllTestChannels();
            
            if (!channels.isEmpty()) {

                channels.forEach(element -> {
                    Map<String, Object> data = new HashMap<>();
                    Map<String, String> innerMap = new HashMap<>();
                    
                    Optional<Categories> optionalCategory = categoriesRepository.findById(element.getFkCategory().getId());
                    Categories category = optionalCategory.get();
                    
                    data.put("id",element.getId());
                    data.put("title",element.getTitle());
                    data.put("url", element.getUrl());
                    data.put("backup_url", element.getBackupUrl());
                    data.put("thumbnail", element.getThumbnail());
                    innerMap.put("tags", category.getCategory_description());
                    data.put("categories", innerMap);
                    channeList.add(data);
                });

                response.put("data", channeList);
            } else {
                response.put("msg", "There are no records in the table");
            }
        }

        if (optionalProvidersDemo.isPresent()) {
            List<DemoChannels> channels = demoChannelsRepository.findAllDemoChannels();
            
            if (!channels.isEmpty()) {

                channels.forEach(element -> {
                    Map<String, Object> data = new HashMap<>();
                    Map<String, String> innerMap = new HashMap<>();
                    
                    Optional<Categories> optionalCategory = categoriesRepository.findById(element.getFkCategory().getId());
                    Categories category = optionalCategory.get();
                    
                    data.put("id",element.getId());
                    data.put("title",element.getTitle());
                    data.put("url", element.getUrl());
                    data.put("backup_url", element.getBackupUrl());
                    data.put("thumbnail", element.getThumbnail());
                    innerMap.put("tags", category.getCategory_description());
                    data.put("categories", innerMap);
                    channeList.add(data);
                });

                response.put("data", channeList);
            } else {
                response.put("msg", "There are no records in the table");
            }
        }

        if (optionalProvidersProd.isEmpty() && optionalProvidersTest.isEmpty() && optionalProvidersDemo.isEmpty()) {
            response.put("msg", "There are no records in the table");
        }

        return response;
    }
}
