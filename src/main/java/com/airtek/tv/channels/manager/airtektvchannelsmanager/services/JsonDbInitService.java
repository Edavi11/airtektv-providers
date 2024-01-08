package com.airtek.tv.channels.manager.airtektvchannelsmanager.services;

import java.util.Map;
import java.util.List;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.airtek.tv.channels.manager.airtektvchannelsmanager.models.ChannelsModel;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.models.ProvidersModel;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.Interfaces.IJsonDbInit;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.models.CategoriesModel;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.Interfaces.IChannelsService;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.Interfaces.IDemoChannelsService;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.Interfaces.IProvidersService;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.Interfaces.ITestChannelsService;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.Interfaces.ICategoriesService;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.repositories.JsonInitRepository;

@Service
public class JsonDbInitService implements IJsonDbInit {

    @Autowired
    private JsonInitRepository jsonInitRepository;

    @Autowired
    private ICategoriesService categoriesService;

    @Autowired 
    private IProvidersService providersServices;

    @Autowired
    private IChannelsService channelsService;

    @Autowired
    private ITestChannelsService testChannelsService;

    @Autowired
    private IDemoChannelsService demoChannelsService;


    @Override
    public void insertInitialProviders() {
        try {
            List<Map<String,String>> providersList = jsonInitRepository.getAllProviders();
            for (Map<String,String> element : providersList) {
                ProvidersModel provider = new ProvidersModel(element.get("provider"), element.get("version"), element.get("next_version"),element.get("demo_version"));
                providersServices.createProvider(provider);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertInitialCategories() {

        try {
            List<Map<String,Object>> categoriesList = jsonInitRepository.getAllCategories();
            categoriesList.forEach(element -> {
                CategoriesModel category = new CategoriesModel(element.get("category_description").toString().toLowerCase());
                categoriesService.createCategory(category);
            });
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertInitialChannels() {
        try {
            List<Map<String,String>> channels = jsonInitRepository.getAllChannels();
            channels.forEach(element -> {
                ChannelsModel channel = new ChannelsModel(element.get("title").toLowerCase(), Integer.parseInt(element.get("fk_category")), element.get("url"), element.get("backup_url"), element.get("thumbnail")); 
                channelsService.createChannel(channel);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertInitialTestChannels() {
        try {
            List<Map<String,String>> channels = jsonInitRepository.getAllChannels();
            channels.forEach(element -> {
                ChannelsModel channel = new ChannelsModel(element.get("title").toLowerCase(), Integer.parseInt(element.get("fk_category")), element.get("url").replace("airtek", "atvdev.airtek"), element.get("backup_url"), element.get("thumbnail")); 
                testChannelsService.createChannel(channel);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertInitialDemoChannels() {
        try {
            List<Map<String,String>> channels = jsonInitRepository.getAllChannels();
            channels.forEach(element -> {
                ChannelsModel channel = new ChannelsModel(element.get("title").toLowerCase(), Integer.parseInt(element.get("fk_category")), element.get("url").replace("airtek", "atvdev.airtek"), element.get("backup_url"), element.get("thumbnail")); 
                demoChannelsService.createChannel(channel);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }    
    }
}
