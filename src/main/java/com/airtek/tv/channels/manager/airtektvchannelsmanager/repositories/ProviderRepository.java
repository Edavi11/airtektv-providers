package com.airtek.tv.channels.manager.airtektvchannelsmanager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.airtek.tv.channels.manager.airtektvchannelsmanager.entities.Providers;

@Repository
public interface ProviderRepository  extends CrudRepository<Providers, Integer> {

    @Query("select p from Providers p")
    List<Providers> findAllProviders();
}
