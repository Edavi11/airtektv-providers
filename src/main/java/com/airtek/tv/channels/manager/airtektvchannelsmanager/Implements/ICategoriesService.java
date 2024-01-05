package com.airtek.tv.channels.manager.airtektvchannelsmanager.Implements;

import java.util.Map;

import com.airtek.tv.channels.manager.airtektvchannelsmanager.entities.Categories;

public interface ICategoriesService {

    Map<String, Object> createCategory(Categories category);

    Map<String, Object> getAllCategories();

    Map<String, Object> getCategoryId(Integer id);

    Map<String,Object> updateCategory(Integer id, String category_description);

    Map<String,Object> deleteCategory(Integer id);
}
