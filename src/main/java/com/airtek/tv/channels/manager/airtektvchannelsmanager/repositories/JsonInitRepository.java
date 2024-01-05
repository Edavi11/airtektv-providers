package com.airtek.tv.channels.manager.airtektvchannelsmanager.repositories;

import java.io.File;
import java.util.Map;
import java.util.Set;

import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class JsonInitRepository {

    // public static void main(String[] args) throws IOException {
    // // readAndSaveChannelsFromJson("./src/main/resources/data/init.json");
    // List<Map<String,Object>> categoriesList = getAllCategories(filePath);
    // }

    // public static void readAndSaveChannelsFromJson(String filePath) throws IOException {

    //     Map<String, Object> dataMap = new HashMap<>();
    //     List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

    //     ObjectMapper objectMapper = new ObjectMapper();
    //     JsonNode nodes = objectMapper.readTree(new File(filePath));

    //     for (JsonNode node : nodes) {
    //         System.out.println(node.get("categories").get("tags"));
    //     }

    //     // Iterar sobre los elementos del JSON y realizar las operaciones necesarias
    //     for (JsonNode channelNode : nodes) {
    //         // Obtener los valores usando métodos de JsonNode
    //         String categories = channelNode.get("categories").get("tags").asText();
    //         String title = channelNode.get("title").asText();
    //         String url = channelNode.get("url").asText();

    //         // Realizar las operaciones necesarias con los datos obtenidos
    //         // Puedes usar channelRepository.save() para guardar en la base de datos, por
    //         // ejemplo
    //         System.out.println("Categories: " + categories);
    //     }
    // }

    // this method return the list of objects with the initial data for categories
    // table
    public List<Map<String, Object>> getAllCategories(String filePath) throws IOException {

        List<Map<String, Object>> categoriesList = new ArrayList<Map<String, Object>>();

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode nodes = objectMapper.readTree(new File(filePath));

        Set<String> categories = getSetCategories(nodes);

        categories.forEach(category -> {
            Map<String, Object> categoryMap = new HashMap<>();
            categoryMap.put("category_description", category);
            categoriesList.add(categoryMap);
        });

        return categoriesList;
    }

    // util mathod to get a set from categories in json file (this method is used in
    // getAllCategories)
    private Set<String> getSetCategories(JsonNode nodes) {
        Set<String> categories = new HashSet<>();
        for (JsonNode node : nodes) {
            String category = node.get("categories").get("tags").asText();
            categories.add(category);
        }
        return categories;
    }

}
