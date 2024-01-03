package com.airtek.tv.channels.manager.airtektvchannelsmanager.Implements;

import java.util.List;
import java.util.Map;

import com.airtek.tv.channels.manager.airtektvchannelsmanager.entities.Providers;

public interface IProvidersService {

    Providers save(Providers provider);

    List<Providers> getAllProviders();
    
    Providers getProviderById(Integer id);

    Providers getProviderByProviderDescriptionAndVersion(String providerDescription, String version);
    Providers getProviderByProviderDescriptionAndTestVersion(String providerDescription, String next_version);
    Providers getProviderByProviderDescriptionAndDemoVersion(String providerDescription, String demo_version);

    Providers update(Integer id, String providerDescription, String version, String next_version, String demo_version, Boolean isActive);

    Map<String,Object> delete(Integer id);

}
