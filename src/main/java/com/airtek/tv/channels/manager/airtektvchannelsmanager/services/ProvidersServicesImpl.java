package com.airtek.tv.channels.manager.airtektvchannelsmanager.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airtek.tv.channels.manager.airtektvchannelsmanager.Interfaces.IProvidersService;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.entities.Providers;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.models.ProvidersModel;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.repositories.ProviderRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ProvidersServicesImpl implements IProvidersService {

    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public Map<String, Object> createProvider(ProvidersModel provider) {

        Map<String, Object> response = new HashMap<>();

        try {
            Optional<Providers> optionalProviders = providerRepository.findByProviderDescription(provider.getProvider_description());

            if (!optionalProviders.isPresent()) {
                Providers providerDb = this.providerRepository.save(new Providers(provider.getProvider_description(),provider.getVersion(),provider.getNext_version(),provider.getDemo_version()));
                response.put("msg", "Recurso creado exitosamente");
                response.put("new_value", providerDb);
            } else {
                Providers providerDb = optionalProviders.get();
                ProvidersModel model = new ProvidersModel(providerDb.getId(), providerDb.getProviderDescription(),providerDb.getVersion(), providerDb.getNext_version(), providerDb.getDemo_version(),providerDb.getIsActive());
                response.put("msg", "Registro ya existente");
                response.put("value", model);
            }
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return response;
    }

    @Override
    public Map<String, Object> getAllProviders() {

        Map<String, Object> response = new HashMap<>();
        List<ProvidersModel> models;

        try {
            List<Providers> providers = this.providerRepository.findAllProviders();

            if (!providers.isEmpty()) {
                models = providers.stream().map(provider -> new ProvidersModel(provider.getId(), provider.getProviderDescription(),provider.getVersion(), provider.getNext_version(), provider.getDemo_version(),provider.getIsActive())).collect(Collectors.toList());
                response.put("providers", models);
            } else {
                response.put("msg", "No existen registros en la tabla");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.put("err", "Ha ocurrido una execpcion: "+e.getMessage());
        }
        return response;
    }

    @Override
    public Map<String, Object> getProviderById(Integer id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Providers> optionalProvider = providerRepository.findById(id);
            if (optionalProvider.isPresent()) {
                Providers provider = optionalProvider.get();
                ProvidersModel model = new ProvidersModel(provider.getId(), provider.getProviderDescription(),provider.getVersion(), provider.getNext_version(), provider.getDemo_version(),provider.getIsActive());
                response.put("provider", model);
            } else {
                response.put("msg", "No se encontró el registro con el id = " + id);
            }
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            response.put("err", "Ha ocurrido una execpcion: "+e.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            response.put("err", "Ha ocurrido una execpcion: "+ex.getMessage());
        }
        return response;
    }

    @Override
    @Transactional
    public Map<String, Object> updateProvider(ProvidersModel provider) {

        Map<String, Object> response = new HashMap<>();

        try {
            Optional<Providers> optionalProvider = providerRepository.findById(provider.getId());

            if (optionalProvider.isPresent()) {

                Providers existingProvider = optionalProvider.get();
                Providers previousProvider = existingProvider.clone();

                existingProvider.setProviderDescription(provider.getProvider_description());
                existingProvider.setVersion(provider.getVersion());
                existingProvider.setNext_version(provider.getNext_version());
                existingProvider.setDemo_version(provider.getDemo_version());
                existingProvider.setIsActive(provider.getIs_active());

                ProvidersModel existingModel = new ProvidersModel(existingProvider.getId(),existingProvider.getProviderDescription(), existingProvider.getVersion(),existingProvider.getNext_version(), existingProvider.getDemo_version(),existingProvider.getIsActive());
                ProvidersModel previousModel = new ProvidersModel(previousProvider.getId(),previousProvider.getProviderDescription(), previousProvider.getVersion(),previousProvider.getNext_version(), previousProvider.getDemo_version(),previousProvider.getIsActive());

                response.put("msg", "Registro actualizado correctamente");
                response.put("updated", existingModel);
                response.put("previous", previousModel);

                this.providerRepository.save(existingProvider);

            } else {
                response.put("msg", "No se encontró el registro con el id = " + provider.getId());
            }
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            response.put("err", "Ha ocurrido una execpcion: "+e.getMessage());
        }
        return response;
    }

    @Override
    @Transactional
    public Map<String, Object> deleteProvider(Integer id) {

        Map<String, Object> response = new HashMap<>();

        try {
            Optional<Providers> optionalProvider = providerRepository.findById(id);

            if (optionalProvider.isPresent()) {
                Providers provider = optionalProvider.get();
                providerRepository.deleteById(id);
                response.put("msg", "Se ha eliminado el registro con el id = " + id);
                ProvidersModel model = new ProvidersModel(provider.getId(), provider.getProviderDescription(),provider.getVersion(), provider.getNext_version(), provider.getDemo_version(),provider.getIsActive());
                response.put("removed", model);
            } else {
                response.put("msg", "No se encontró el registro con el ID proporcionado");
            }

        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            response.put("err", "Ha ocurrido una execpcion: "+e.getMessage());
        }

        return response;
    }
}
