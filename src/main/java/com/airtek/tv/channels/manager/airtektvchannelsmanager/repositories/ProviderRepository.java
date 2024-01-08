package com.airtek.tv.channels.manager.airtektvchannelsmanager.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.airtek.tv.channels.manager.airtektvchannelsmanager.entities.Providers;

@Repository
public interface ProviderRepository  extends CrudRepository<Providers, Integer> {

    @Query("select p from Providers p")
    List<Providers> findAllProviders();

    @Query("select p from Providers p where p.providerDescription = ?1")
    Optional<Providers> findByProviderDescription(String provider_description);

    @Query("select p from Providers p where p.providerDescription = ?1 and p.version = ?2 ")
    Optional<Providers> getChannelsByProviderAndVersion(String provider_description, String version);

    @Query("select p from Providers p where p.providerDescription = ?1 and p.next_version = ?2 ")
    Optional<Providers> getChannelsByProviderAndNextVersion(String provider_description, String version);

    @Query("select p from Providers p where p.providerDescription = ?1 and p.demo_version = ?2 ")
    Optional<Providers> getChannelsByProviderAndDemoVersion(String provider_description, String version);
}
