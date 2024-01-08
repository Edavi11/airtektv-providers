package com.airtek.tv.channels.manager.airtektvchannelsmanager.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.airtek.tv.channels.manager.airtektvchannelsmanager.entities.TestChannels;


public interface TestChannelsRepository extends CrudRepository<TestChannels, Integer> {

    @Query("select ch from TestChannels ch")
    List<TestChannels> findAllTestChannels();

    @Query("select ch from TestChannels ch where ch.title = ?1 and ch.url = ?2")
    Optional<TestChannels> findByTitleAndUrl(String title, String url);
}
