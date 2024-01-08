package com.airtek.tv.channels.manager.airtektvchannelsmanager.services;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.airtek.tv.channels.manager.airtektvchannelsmanager.entities.Categories;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.models.ChannelsModel;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.entities.DemoChannels;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.Interfaces.IDemoChannelsService;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.repositories.CategoriesRepository;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.repositories.DemoChannelsRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DemoChannelsServiceImpl implements IDemoChannelsService{

    @Autowired
    private DemoChannelsRepository demoChannelsRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    public Map<String, Object> createChannel(ChannelsModel channel) {
        Map<String, Object> response = new HashMap<>();

        try {
            Optional<Categories> optionalCategory = categoriesRepository.findById(channel.getFkCategory());
            Optional<DemoChannels> optionalChannels = demoChannelsRepository.findByTitleAndUrl(channel.getTitle(),channel.getUrl());

        if (optionalCategory.isPresent()) {
                Categories category = optionalCategory.get();
                if (!optionalChannels.isPresent()) {

                    DemoChannels channelsDb = this.demoChannelsRepository.save(new DemoChannels(channel.getTitle(),category,channel.getUrl(),channel.getBackupUrl(),channel.getThumbnail()));
                    ChannelsModel model = new ChannelsModel(channelsDb.getId(),channelsDb.getTitle(),channelsDb.getFkCategory().getId(),channelsDb.getUrl(),channelsDb.getBackupUrl(),channelsDb.getThumbnail());
                    response.put("msg", "Recurso creado exitosamente");
                    response.put("new_value", model);

                } else {

                    DemoChannels channelsDb = optionalChannels.get();
                    ChannelsModel model = new ChannelsModel(channelsDb.getId(),channelsDb.getTitle(),channelsDb.getFkCategory().getId(),channelsDb.getUrl(),channelsDb.getBackupUrl(),channelsDb.getThumbnail());
                    response.put("msg", "Registro ya existente");
                    response.put("value", model);
                }
        } else {
            response.put("msg", "Por favor ingrese un id de categoria existente");
        }


        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            response.put("err", "Ha ocurrido una execpcion: "+e.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            response.put("err", "Ha ocurrido una execpcion: "+ex.getMessage());
        }

        return response;    
    }    

    @Override
    public Map<String, Object> getAllChannels() {

        Map<String, Object> response = new HashMap<>();

        List<ChannelsModel> models;

        try {
            List<DemoChannels> testChannels = this.demoChannelsRepository.findAllDemoChannels();

            if (!testChannels.isEmpty()) {
                models = testChannels.stream().map(channel -> new ChannelsModel(channel.getId(),channel.getTitle(),channel.getFkCategory().getId(),channel.getUrl(),channel.getBackupUrl(),channel.getThumbnail())).collect(Collectors.toList());
                response.put("channels", models);
            } else {
                response.put("msg", "No existen registros en la tabla");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.put("err", "Ha ocurrido una execpcion: "+e.getMessage());
        }
        return response;         
    }

    @Override
    public Map<String, Object> getChannelById(Integer id) {

        Map<String, Object> response = new HashMap<>();

        try {
            Optional<DemoChannels> optionalChannels = demoChannelsRepository.findById(id);

            if (optionalChannels.isPresent()) {
                DemoChannels channel = optionalChannels.get();
                ChannelsModel model = new ChannelsModel(channel.getId(),channel.getTitle(),channel.getFkCategory().getId(),channel.getUrl(),channel.getBackupUrl(),channel.getThumbnail());
                response.put("channel", model);
            } else {
                response.put("msg", "No se encontró el registro con el id = " + id);
            }

        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            response.put("err", "Ha ocurrido una execpcion: "+ e.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            response.put("err", "Ha ocurrido una execpcion: "+ ex.getMessage());
        }

        return response;        
    }

    @Override
    public Map<String, Object> updateChannel(ChannelsModel channel) {
        Map<String, Object> response = new HashMap<>();

        try {
            Optional<DemoChannels> optionalChannel = demoChannelsRepository.findById(channel.getId());
            Optional<Categories> optionalCategories = categoriesRepository.findById(channel.getFkCategory());

            if (optionalCategories.isPresent()) {
                
                Categories category = optionalCategories.get();

                if (optionalChannel.isPresent()) {

                    DemoChannels existingChannel = optionalChannel.get();
                    DemoChannels previousChannel = existingChannel.clone();

                    existingChannel.setTitle(channel.getTitle());
                    existingChannel.setFkCategory(category);
                    existingChannel.setUrl(channel.getUrl());
                    existingChannel.setBackupUrl(channel.getBackupUrl());
                    existingChannel.setThumbnail(channel.getThumbnail());

                    ChannelsModel existingModel = new ChannelsModel(existingChannel.getId(),existingChannel.getTitle().toLowerCase(),existingChannel.getFkCategory().getId(),existingChannel.getUrl(),existingChannel.getBackupUrl(),existingChannel.getThumbnail());
                    ChannelsModel previousModel = new ChannelsModel(previousChannel.getId(),previousChannel.getTitle().toLowerCase(),previousChannel.getFkCategory().getId(),previousChannel.getUrl(),previousChannel.getBackupUrl(),previousChannel.getThumbnail());

                    response.put("msg", "Registro actualizado correctamente");
                    response.put("updated", existingModel);
                    response.put("previous", previousModel);

                    this.demoChannelsRepository.save(existingChannel);

                } else {
                    response.put("msg", "No se encontró el registro con el id = " + channel.getId());
                }
            } else {
                response.put("msg", "Por favor ingrese un id de categoria existente");
            }
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            response.put("err", "Ha ocurrido una execpcion: "+e.getMessage());
        }
        return response;    
    }

    @Override
    public Map<String, Object> deleteChannel(Integer id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Optional<DemoChannels> optionalChannel = demoChannelsRepository.findById(id);

            if (optionalChannel.isPresent()) {

                DemoChannels channel = optionalChannel.get();
                demoChannelsRepository.deleteById(id);
                response.put("msg", "Se ha eliminado el registro con el id = " + id);
                ChannelsModel model = new ChannelsModel(channel.getId(),channel.getTitle(),channel.getFkCategory().getId(),channel.getUrl(),channel.getBackupUrl(),channel.getThumbnail());
                response.put("removed", model);

            } else {
                response.put("msg", "No se encontró el registro con el id = " + id);
            }

        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            response.put("err", "Ha ocurrido una execpcion: "+e.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            response.put("err", "Ha ocurrido una execpcion: "+ex.getMessage());
        }
        return response;
    }

}
