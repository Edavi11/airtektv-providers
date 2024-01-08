package com.airtek.tv.channels.manager.airtektvchannelsmanager.repositories;

import java.io.File;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.entities.Categories;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.models.ChannelsModel;
import com.fasterxml.jackson.core.type.TypeReference;

import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Repository
public class JsonInitRepository {

    @Value("${json.path}")
    private String filePath;

    @Value("${json.providers}")
    private String providersFilePath;

    @Autowired
    CategoriesRepository categoriesRepository;

    // public static void main(String[] args) throws IOException {
    // // readAndSaveChannelsFromJson("./src/main/resources/data/init.json");
    // // List<Map<String,Object>> categoriesList = getAllCategories(filePath);
    // getAllProviders();

    // }

    // public static void readAndSaveChannelsFromJson(String filePath) throws
    // IOException {

    // Map<String, Object> dataMap = new HashMap<>();
    // List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

    // ObjectMapper objectMapper = new ObjectMapper();
    // JsonNode nodes = objectMapper.readTree(new File(filePath));

    // for (JsonNode node : nodes) {
    // System.out.println(node.get("categories").get("tags"));
    // }

    // // Iterar sobre los elementos del JSON y realizar las operaciones necesarias
    // for (JsonNode channelNode : nodes) {
    // // Obtener los valores usando métodos de JsonNode
    // String categories = channelNode.get("categories").get("tags").asText();
    // String title = channelNode.get("title").asText();
    // String url = channelNode.get("url").asText();

    // // Realizar las operaciones necesarias con los datos obtenidos
    // // Puedes usar channelRepository.save() para guardar en la base de datos, por
    // // ejemplo
    // System.out.println("Categories: " + categories);
    // }
    // }

    // this method return the list of objects with the initial data for categories table
    public List<Map<String, Object>> getAllCategories() throws IOException {

        try {
            List<Map<String, Object>> categoriesList = new ArrayList<Map<String, Object>>();

            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode nodes = objectMapper.readTree(new File(this.filePath));

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
        return null;
    }

    // util mathod to get a set from categories in json file (this method is used in (getAllCategories)
    private Set<String> getSetCategories(JsonNode nodes) {
        Set<String> categories = new HashSet<>();
        for (JsonNode node : nodes) {
            String category = node.get("categories").get("tags").asText();
            categories.add(category);
        }
        return categories;
    }

    public List<Map<String, String>> getAllProviders() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode nodes = objectMapper.readTree(new File(providersFilePath));
        List<Map<String,String>> dataList = objectMapper.convertValue(nodes, new TypeReference<>(){});
        return dataList;
    }

    public List<Map<String, String>> getAllChannels() throws IOException {

        List<Map<String, String>> channelList = new ArrayList<Map<String, String>>();

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode nodes = objectMapper.readTree(new File(filePath));

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
        
        return channelList;
    }
}
