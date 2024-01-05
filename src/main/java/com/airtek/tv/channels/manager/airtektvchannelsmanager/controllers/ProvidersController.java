package com.airtek.tv.channels.manager.airtektvchannelsmanager.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.airtek.tv.channels.manager.airtektvchannelsmanager.entities.Providers;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.models.ProvidersModel;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.Implements.IProvidersService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1")
public class ProvidersController {

    @Autowired
    private IProvidersService providersService;

    @GetMapping("/get/all/providers")
    public ResponseEntity<?> getAllProviders() {
        Map<String, Object> response = providersService.getAllProviders();
        return response.containsKey("msg") ? new ResponseEntity<>(response, HttpStatus.NOT_FOUND) : new ResponseEntity<>(response.get("providers"), HttpStatus.OK);
    }

    @PostMapping("/get/provider")
    public ResponseEntity<?> getProvidersById(@RequestBody ProvidersModel body) {
        Map<String, Object> response = providersService.getProviderById(body.getId());
        return response.containsKey("msg") ? new ResponseEntity<>(response, HttpStatus.NOT_FOUND) : new ResponseEntity<>(response.get("provider"), HttpStatus.OK);
    }

    @PostMapping("/create/provider")
    public ResponseEntity<?> createProvider(@RequestBody ProvidersModel body) {
        Providers providerDb = new Providers(body.getProvider_description(), body.getVersion(),body.getNext_version(), body.getDemo_version(), body.getIs_active());
        return new ResponseEntity<>(providersService.createProvider(providerDb), HttpStatus.CREATED);
    }

    @PostMapping("/update/provider")
    public ResponseEntity<Map<String, Object>> updateProvider(@RequestBody ProvidersModel body) {
        Map<String,Object> response = providersService.updateProvider(body.getId(), body.getProvider_description(), body.getVersion(), body.getNext_version(), body.getDemo_version(), body.getIs_active());
        return response.containsKey("updated") ? new ResponseEntity<>(response, HttpStatus.OK) : new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/delete/provider")
    public ResponseEntity<Map<String, Object>> deleteProviderById(@RequestBody ProvidersModel body) {
        Map<String, Object> response = providersService.deleteProvider(body.getId());
        return response.containsKey("removed") ? new ResponseEntity<>(response, HttpStatus.OK) : new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}