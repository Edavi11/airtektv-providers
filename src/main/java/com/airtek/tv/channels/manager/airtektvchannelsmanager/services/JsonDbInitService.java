package com.airtek.tv.channels.manager.airtektvchannelsmanager.services;

import java.util.Map;
import java.util.List;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;

import com.airtek.tv.channels.manager.airtektvchannelsmanager.Implements.IJsonDbInit;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.entities.Categories;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.repositories.JsonInitRepository;

@Service
public class JsonDbInitService implements IJsonDbInit {

    @Value("${json.path}")
    private String filePath;

    @Autowired
    private JsonInitRepository jsonInitRepository;

    @Autowired
    private CategoriesServiceImpl categoriesService;

    @Override
    public void insertInitialProviders() {
        throw new UnsupportedOperationException("Unimplemented method 'insertInitialProviders'");
    }

    @Override
    public void insertInitialCategories() {

        try {
            List<Map<String,Object>> categoriesList = jsonInitRepository.getAllCategories(filePath);
            categoriesList.forEach(element -> {
                Categories category = new Categories(element.get("category_description").toString());
                categoriesService.createCategory(category);
            });
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Map<String, Object>> getAllProviders() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllProviders'");
    }

    @Override
    public List<Map<String, Object>> getAllCateogies() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllCateogies'");
    }

    @Override
    public List<Map<String, Object>> getAllTitles() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllTitles'");
    }

    @Override
    public List<Map<String, Object>> getAllUrls() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllUrls'");
    }

    @Override
    public List<Map<String, Object>> getAllThumbnail() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllThumbnail'");
    }

    @Override
    public List<Map<String, Object>> getAllBackupUrl() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllBackupUrl'");
    }
}
