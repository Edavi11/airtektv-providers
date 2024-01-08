package com.airtek.tv.channels.manager.airtektvchannelsmanager.Interfaces;

import java.util.Map;

import com.airtek.tv.channels.manager.airtektvchannelsmanager.models.ChannelsModel;

public interface IDemoChannelsService {

    Map<String, Object> createChannel(ChannelsModel channel);

    Map<String, Object> getAllChannels();

    Map<String, Object> getChannelById(Integer id);

    Map<String,Object> updateChannel(ChannelsModel channel);

    Map<String,Object> deleteChannel(Integer id);
    
}
