package com.airtek.tv.channels.manager.airtektvchannelsmanager.repositories;

import java.util.Map;
import java.util.Set;
import java.util.Optional;

import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.entities.Categories;

import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;


@Repository
public class JsonInitRepository {

    @Autowired
    private ResourceLoader resourceLoader;


    @Autowired
    CategoriesRepository categoriesRepository;

    // this method return the list of objects with the initial data for categories table
    public List<Map<String, Object>> getAllCategories() throws IOException {
        List<Map<String, Object>> categoriesList = new ArrayList<Map<String, Object>>();

        try {
            Resource resource = resourceLoader.getResource("classpath:data/init.json");
            InputStream filePath = resource.getInputStream();

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode nodes = objectMapper.readTree(filePath);

            Set<String> categories = getSetCategories(nodes);

            categories.forEach(category -> {
                Map<String, Object> categoryMap = new HashMap<>();
                categoryMap.put("category_description", category);
                categoriesList.add(categoryMap);
            });

            return categoriesList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoriesList;
    }

    // util mathod to get a set from categories in json file (this method is used in getAllCategories
    private Set<String> getSetCategories(JsonNode nodes) {
        Set<String> categories = new HashSet<>();
        for (JsonNode node : nodes) {
            String category = node.get("categories").get("tags").asText();
            categories.add(category);
        }
        return categories;
    }

    public List<Map<String, String>> getAllProviders() throws IOException {
        List<Map<String, String>> dataList = new ArrayList<>();

        try {
            Resource resource = resourceLoader.getResource("classpath:data/providers.json");
            InputStream providersFilePath = resource.getInputStream();

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode nodes = objectMapper.readTree(providersFilePath);
            dataList = objectMapper.convertValue(nodes, new TypeReference<>() {});

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataList;
    }

    public List<Map<String, String>> getAllChannels() throws IOException {

        List<Map<String, String>> channelList = new ArrayList<Map<String, String>>();

        try {
            Resource resource = resourceLoader.getResource("classpath:data/init.json");
            InputStream filePath = resource.getInputStream();

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode nodes = objectMapper.readTree(filePath);
            for (JsonNode node : nodes) {

                Map<String, String> channelMap = new HashMap<>();
                Optional<Categories> category = categoriesRepository.findCategoriesByCategoryDescription(node.get("categories").get("tags").asText().toLowerCase());

                if (category.isPresent()) {
                    channelMap.put("title", node.get("title").asText());
                    channelMap.put("fk_category", category.get().getId().toString());
                    channelMap.put("url", node.get("url").asText());
                    channelMap.put("backup_url", node.hasNonNull("backup_url") ? node.get("backup_url").asText() : null);
                    channelMap.put("thumbnail", node.hasNonNull("thumbnail") ? node.get("thumbnail").asText() : null);
                    channelList.add(channelMap);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return channelList;
    }
}
