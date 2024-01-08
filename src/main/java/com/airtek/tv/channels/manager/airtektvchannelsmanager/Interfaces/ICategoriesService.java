package com.airtek.tv.channels.manager.airtektvchannelsmanager.Interfaces;

import java.util.Map;

import com.airtek.tv.channels.manager.airtektvchannelsmanager.models.CategoriesModel;

public interface ICategoriesService {

    Map<String, Object> createCategory(CategoriesModel category);

    Map<String, Object> getAllCategories();

    Map<String, Object> getCategoryId(Integer id);

    Map<String,Object> updateCategory(CategoriesModel category);

    Map<String,Object> deleteCategory(Integer id);
}
