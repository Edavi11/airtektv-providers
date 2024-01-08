package com.airtek.tv.channels.manager.airtektvchannelsmanager.Interfaces;

import java.util.Map;

import com.airtek.tv.channels.manager.airtektvchannelsmanager.models.ProvidersModel;

public interface IProvidersService {

    Map<String, Object> createProvider(ProvidersModel provider);

    Map<String, Object> getAllProviders();
    
    Map<String,Object> getProviderById(Integer id);

    Map<String,Object> updateProvider(ProvidersModel provider);

    Map<String,Object> deleteProvider(Integer id);

}
