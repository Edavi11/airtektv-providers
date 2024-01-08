package com.airtek.tv.channels.manager.airtektvchannelsmanager.services;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.airtek.tv.channels.manager.airtektvchannelsmanager.entities.Categories;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.models.ChannelsModel;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.entities.TestChannels;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.Interfaces.ITestChannelsService;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.repositories.CategoriesRepository;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.repositories.TestChannelsRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TestChannelsServiceImpl implements ITestChannelsService {

    @Autowired
    private TestChannelsRepository testChannelsRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    public Map<String, Object> createChannel(ChannelsModel channel) {
        Map<String, Object> response = new HashMap<>();

        try {
            Optional<Categories> optionalCategory = categoriesRepository.findById(channel.getFkCategory());
            Optional<TestChannels> optionalChannels = testChannelsRepository.findByTitleAndUrl(channel.getTitle(),channel.getUrl());

        if (optionalCategory.isPresent()) {
                Categories category = optionalCategory.get();
                if (!optionalChannels.isPresent()) {

                    TestChannels channelsDb = this.testChannelsRepository.save(new TestChannels(channel.getTitle(),category,channel.getUrl(),channel.getBackupUrl(),channel.getThumbnail()));
                    ChannelsModel model = new ChannelsModel(channelsDb.getId(),channelsDb.getTitle(),channelsDb.getFkCategory().getId(),channelsDb.getUrl(),channelsDb.getBackupUrl(),channelsDb.getThumbnail());
                    response.put("msg", "Recurso creado exitosamente");
                    response.put("new_value", model);

                } else {

                    TestChannels channelsDb = optionalChannels.get();
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
            List<TestChannels> testChannels = this.testChannelsRepository.findAllTestChannels();

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
            Optional<TestChannels> optionalChannels = testChannelsRepository.findById(id);

            if (optionalChannels.isPresent()) {
                TestChannels channel = optionalChannels.get();
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
            Optional<TestChannels> optionalChannel = testChannelsRepository.findById(channel.getId());
            Optional<Categories> optionalCategories = categoriesRepository.findById(channel.getFkCategory());

            if (optionalCategories.isPresent()) {
                
                Categories category = optionalCategories.get();

                if (optionalChannel.isPresent()) {

                    TestChannels existingChannel = optionalChannel.get();
                    TestChannels previousChannel = existingChannel.clone();

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

                    this.testChannelsRepository.save(existingChannel);

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
            Optional<TestChannels> optionalChannel = testChannelsRepository.findById(id);

            if (optionalChannel.isPresent()) {

                TestChannels channel = optionalChannel.get();
                testChannelsRepository.deleteById(id);
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
