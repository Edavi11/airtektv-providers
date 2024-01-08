package com.airtek.tv.channels.manager.airtektvchannelsmanager.services;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import com.airtek.tv.channels.manager.airtektvchannelsmanager.entities.Categories;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.models.CategoriesModel;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.Interfaces.ICategoriesService;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.repositories.CategoriesRepository;

@Service
public class CategoriesServiceImpl implements ICategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    public Map<String, Object> createCategory(CategoriesModel category) {

        Map<String, Object> response = new HashMap<>();

        try {
            Optional<Categories> optionalCategories = categoriesRepository.findCategoriesByCategoryDescription(category.getCategory_description());

            if (!optionalCategories.isPresent()) {
                Categories categoryDb = this.categoriesRepository.save(new Categories(category.getCategory_description()));
                response.put("msg", "Recurso creado exitosamente");
                response.put("new_value", categoryDb);
            } else {
                Categories categoriesDb = optionalCategories.get();
                CategoriesModel model = new CategoriesModel(categoriesDb.getId(),categoriesDb.getCategory_description());
                response.put("msg", "Registro ya existente");
                response.put("value", model);
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
    public Map<String, Object> getAllCategories() {

        Map<String, Object> response = new HashMap<>();

        List<CategoriesModel> models;

        try {
            List<Categories> categories = this.categoriesRepository.findAllCategories();

            if (!categories.isEmpty()) {
                models = categories.stream()
                        .map(category -> new CategoriesModel(category.getId(), category.getCategory_description()))
                        .collect(Collectors.toList());
                response.put("categories", models);
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
    public Map<String, Object> getCategoryId(Integer id) {

        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Categories> optionalCategories = categoriesRepository.findById(id);

            if (optionalCategories.isPresent()) {
                Categories category = optionalCategories.get();
                CategoriesModel model = new CategoriesModel(category.getId(), category.getCategory_description());
                response.put("category", model);
            } else {
                response.put("msg", "No se encontró el registro con el id = " + id);
            }

        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            response.put("err", "Ha ocurrido una execpcion: "+ e.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            response.put("err", "Ha ocurrido una execpcion: "+ ex.getMessage());
        }

        return response;
    }

    @Override
    public Map<String, Object> updateCategory(CategoriesModel body) {

        Map<String, Object> response = new HashMap<>();

        try {

            Optional<Categories> optionalCategory = categoriesRepository.findById(body.getId());

            if (optionalCategory.isPresent()) {

                Categories existingCategory = optionalCategory.get();
                Categories previousCategory = existingCategory.clone();

                existingCategory.setCategory_description(body.getCategory_description());

                CategoriesModel existingModel = new CategoriesModel(existingCategory.getId(),existingCategory.getCategory_description());
                CategoriesModel previousModel = new CategoriesModel(previousCategory.getId(), previousCategory.getCategory_description());

                response.put("msg", "Registro actualizado correctamente");
                response.put("updated", existingModel);
                response.put("previous", previousModel);

                this.categoriesRepository.save(existingCategory);

            } else {
                response.put("msg", "No se encontró el registro con el id = " + body.getId());
            }
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            response.put("err", "Ha ocurrido una execpcion: "+e.getMessage());
        }

        return response;
    }

    @Override
    public Map<String, Object> deleteCategory(Integer id) {

        Map<String, Object> response = new HashMap<>();

        try {
            Optional<Categories> optionalCategory = categoriesRepository.findById(id);

            if (optionalCategory.isPresent()) {

                Categories category = optionalCategory.get();
                categoriesRepository.deleteById(id);
                response.put("msg", "Se ha eliminado el registro con el id = " + id);
                CategoriesModel model = new CategoriesModel(category.getId(), category.getCategory_description());
                response.put("removed", model);

            } else {
                response.put("msg", "No se encontró el registro con el id = " + id);
            }

        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            response.put("err", "Ha ocurrido una execpcion: "+e.getMessage());
        }
        return response;
    }
}