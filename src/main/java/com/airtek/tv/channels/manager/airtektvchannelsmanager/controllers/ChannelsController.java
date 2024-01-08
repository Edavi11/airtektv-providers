package com.airtek.tv.channels.manager.airtektvchannelsmanager.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airtek.tv.channels.manager.airtektvchannelsmanager.models.ChannelsModel;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.Interfaces.IChannelsService;

@RestController
@RequestMapping("/api/v1")
public class ChannelsController {

    @Autowired
    private IChannelsService channelsService;

    @GetMapping("/get/all/channels")
    public ResponseEntity<?> getAllCategories() {
        Map<String, Object> response = channelsService.getAllChannels();
        return response.containsKey("channels") ? new ResponseEntity<>(response.get("channels"), HttpStatus.OK): new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/get/channel")
    public ResponseEntity<?> getProvidersById(@Validated @RequestBody ChannelsModel body) {
        Map<String, Object> response = channelsService.getChannelById(body.getId());
        return response.containsKey("channel") ? new ResponseEntity<>(response.get("channel"), HttpStatus.OK) : new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create/channel")
    public ResponseEntity<?> createProvider(@RequestBody ChannelsModel body) {
        Map<String, Object> response = channelsService.createChannel(body);
        return response.containsKey("new_value") ? new ResponseEntity<>(response.get("new_value"), HttpStatus.CREATED) : new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/update/channel")
    public ResponseEntity<Map<String, Object>> updateProvider(@RequestBody ChannelsModel body) {
        Map<String,Object> response = channelsService.updateChannel(body);
        return response.containsKey("updated") ? new ResponseEntity<>(response, HttpStatus.OK) : new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/delete/channel")
    public ResponseEntity<Map<String, Object>> postMethodName(@RequestBody ChannelsModel body) {
        Map<String, Object> response = channelsService.deleteChannel(body.getId());
        return response.containsKey("removed") ? new ResponseEntity<>(response, HttpStatus.OK) : new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}