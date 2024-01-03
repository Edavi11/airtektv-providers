package com.airtek.tv.channels.manager.airtektvchannelsmanager.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/config")
public class ConfigController {

    @GetMapping("/hello")
    public Map<String,String> getMethodName() {
        Map<String, String> message = new HashMap<>();
        message.put("message", "es alcanzable");
        return message;
    }
    
}
