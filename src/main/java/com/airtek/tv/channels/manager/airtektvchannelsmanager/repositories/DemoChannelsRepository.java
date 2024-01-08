package com.airtek.tv.channels.manager.airtektvchannelsmanager.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.airtek.tv.channels.manager.airtektvchannelsmanager.entities.DemoChannels;

public interface DemoChannelsRepository extends CrudRepository<DemoChannels, Integer> {

    @Query("select ch from DemoChannels ch")
    List<DemoChannels> findAllDemoChannels();

    @Query("select ch from DemoChannels ch where ch.title = ?1 and ch.url = ?2")
    Optional<DemoChannels> findByTitleAndUrl(String title, String url);
}
