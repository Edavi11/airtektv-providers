package com.airtek.tv.channels.manager.airtektvchannelsmanager.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airtek.tv.channels.manager.airtektvchannelsmanager.Implements.ICategoriesService;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.entities.Categories;
import com.airtek.tv.channels.manager.airtektvchannelsmanager.models.CategoriesModel;

@RestController
@RequestMapping("/api/v1")
public class CategoriesController {

    @Autowired
    private ICategoriesService categoriesService;

    @GetMapping("/get/all/categories")
    public ResponseEntity<?> getAllCategories() {
        Map<String, Object> response = categoriesService.getAllCategories();
        return response.containsKey("categories") ? new ResponseEntity<>(response.get("categories"), HttpStatus.OK): new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/get/category")
    public ResponseEntity<?> getProvidersById(@Validated @RequestBody CategoriesModel body) {
        Map<String, Object> response = categoriesService.getCategoryId(body.getId());
        return response.containsKey("category") ? new ResponseEntity<>(response.get("category"), HttpStatus.OK) : new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create/category")
    public ResponseEntity<?> createProvider(@RequestBody CategoriesModel body) {
        Categories category = new Categories(body.getCategory_description());
        Map<String, Object> response = categoriesService.createCategory(category);
        return response.containsKey("new_value") ? new ResponseEntity<>(response.get("new_value"), HttpStatus.CREATED) : new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/update/category")
    public ResponseEntity<Map<String, Object>> updateProvider(@RequestBody CategoriesModel body) {
        Map<String,Object> response = categoriesService.updateCategory(body.getId(),body.getCategory_description());
        return response.containsKey("updated") ? new ResponseEntity<>(response, HttpStatus.OK) : new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/delete/category")
    public ResponseEntity<Map<String, Object>> postMethodName(@RequestBody CategoriesModel body) {
        Map<String, Object> response = categoriesService.deleteCategory(body.getId());
        return response.containsKey("removed") ? new ResponseEntity<>(response, HttpStatus.OK) : new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
