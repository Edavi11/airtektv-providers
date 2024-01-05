package com.airtek.tv.channels.manager.airtektvchannelsmanager.Implements;

import java.util.Map;

import com.airtek.tv.channels.manager.airtektvchannelsmanager.entities.Providers;

public interface IProvidersService {

    Providers createProvider(Providers provider);

    Map<String, Object> getAllProviders();
    
    Map<String,Object> getProviderById(Integer id);

    Map<String,Object> updateProvider(Integer id, String providerDescription, String version, String next_version, String demo_version, Boolean isActive);

    Map<String,Object> deleteProvider(Integer id);

}
