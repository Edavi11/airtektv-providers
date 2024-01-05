package com.airtek.tv.channels.manager.airtektvchannelsmanager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.airtek.tv.channels.manager.airtektvchannelsmanager.entities.Providers;

@Repository
public interface ProviderRepository  extends CrudRepository<Providers, Integer> {

    @Query("select p from Providers p")
    List<Providers> findAllProviders();

    @Modifying
    @Query("update Providers p set p.providerDescription = ?2, p.version = ?3 , p.next_version = ?4, p.demo_version = ?5 , p.isActive = ?6 where p.id = ?1")
    Providers updateProvider(Integer id, String provider_description, String version, String nex_version, String demo_version, Boolean is_active);
}
