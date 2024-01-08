package com.airtek.tv.channels.manager.airtektvchannelsmanager.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airtek.tv.channels.manager.airtektvchannelsmanager.services.DataChannelsServiceImpl;

@RestController
@RequestMapping("/api/v1")
public class DataChannelController {

    @Autowired
    private DataChannelsServiceImpl dataChannelsServiceImpl;

    @GetMapping("/get/channels/{provider}/{version}")
        public ResponseEntity<?> getDataChannels(@PathVariable("provider") String provider, @PathVariable("version") String version) {
        Map<String, Object> response = dataChannelsServiceImpl.getDataChannelsByProviderAndVersion(provider,version);
        return response.containsKey("data") ? new ResponseEntity<>(response, HttpStatus.OK) : new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
