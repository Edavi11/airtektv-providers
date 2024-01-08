package com.airtek.tv.channels.manager.airtektvchannelsmanager.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.airtek.tv.channels.manager.airtektvchannelsmanager.entities.Channels;

@Repository
public interface ChannelsRepository extends CrudRepository<Channels, Integer> {

    @Query("select ch from Channels ch")
    List<Channels> findAllChannels();

    @Query("select ch from Channels ch where ch.title = ?1 and ch.url = ?2")
    Optional<Channels> findByTitleAndUrl(String title, String url);

}
