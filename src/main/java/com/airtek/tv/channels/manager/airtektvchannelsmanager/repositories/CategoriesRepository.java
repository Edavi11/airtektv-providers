package com.airtek.tv.channels.manager.airtektvchannelsmanager.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.airtek.tv.channels.manager.airtektvchannelsmanager.entities.Categories;

@Repository
public interface CategoriesRepository extends CrudRepository<Categories, Integer>{

    @Query("select c from Categories c")
    List<Categories> findAllCategories();

    @Query("select c from Categories c where c.category_description = ?1")
    Optional<Categories> findCategoriesByCategoryDescription(String category_description);
}
