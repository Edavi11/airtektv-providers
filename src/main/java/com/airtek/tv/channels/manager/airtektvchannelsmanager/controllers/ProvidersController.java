package com.airtek.tv.channels.manager.airtektvchannelsmanager.controllers;

import java.lang.module.ModuleDescriptor.Provides;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.airtek.tv.channels.manager.airtektvchannelsmanager.Implements.IProvidersService;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.entities.Providers;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.models.ProvidersModel;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1")
public class ProvidersController {

    @Autowired
    private IProvidersService providersService;

    @GetMapping("/get/all/providers")
    public List<Providers> getAllProviders() {
        List<Providers> providers = providersService.getAllProviders();
        for (Providers p : providers) {
            System.out.println(p);
        }
        return providers;
    }

    @PostMapping("/create/provider")
    public ResponseEntity<?> createProvider(@RequestBody ProvidersModel provider) {
        Providers providerDb = new Providers(provider.getProvider_description(), provider.getVersion(), provider.getNext_version(), provider.getDemo_version(), provider.getIs_active());
        return new ResponseEntity<>(providersService.save(providerDb), HttpStatus.CREATED);
    }

    @PostMapping("/delete/provider")
    public ResponseEntity<Map<String, Object>> deleteProviderById(@RequestBody ProvidersModel providersModel) {

        System.out.println(providersModel.getId());
        Map<String, Object> response = providersService.delete(providersModel.getId());
        

        return null;

        // if (response.containsKey("removed")) {
        //     return new ResponseEntity<>(response, HttpStatus.OK);
        // } else {
        //     return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        // }
    }
}