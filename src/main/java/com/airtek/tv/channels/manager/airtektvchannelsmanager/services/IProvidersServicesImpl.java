package com.airtek.tv.channels.manager.airtektvchannelsmanager.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airtek.tv.channels.manager.airtektvchannelsmanager.Implements.IProvidersService;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.entities.Providers;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.repositories.ProviderRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class IProvidersServicesImpl implements IProvidersService {

    @Autowired
    private ProviderRepository providerRepository;


    @Override
    public Providers save(Providers provider) {
        return this.providerRepository.save(provider);
    }

    @Override
    public List<Providers> getAllProviders() {
        return this.providerRepository.findAllProviders();
    }

    @Override
    public Providers getProviderById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProviderById'");
    }

    @Override
    public Providers getProviderByProviderDescriptionAndVersion(String providerDescription, String version) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProviderByProviderDescriptionAndVersion'");
    }

    @Override
    public Providers getProviderByProviderDescriptionAndTestVersion(String providerDescription, String next_version) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProviderByProviderDescriptionAndTestVersion'");
    }

    @Override
    public Providers getProviderByProviderDescriptionAndDemoVersion(String providerDescription, String demo_version) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProviderByProviderDescriptionAndDemoVersion'");
    }

    @Override
    public Providers update(Integer id, String providerDescription, String version, String next_version,
            String demo_version, Boolean isActive) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    @Transactional
    public Map<String, Object> delete(Integer id) {

        Map<String, Object> response = new HashMap<>();

        try {
            Optional<Providers> optionalProvider = providerRepository.findById(id);

            if (optionalProvider.isPresent()) {
                Providers provider = optionalProvider.get();
                providerRepository.deleteById(id);

                response.put("msg", "Se ha eliminado el registro con el id = " + id);
                response.put("removed", provider);
            } else {
                response.put("msg", "No se encontró el registro con el ID proporcionado");
            }
        } catch (EntityNotFoundException e) {
            // Manejar la excepción si no se encuentra la entidad con el ID proporcionado
            response.put("msg", "No se encontró el registro con el ID proporcionado");
        }

        return response;
    }
}



