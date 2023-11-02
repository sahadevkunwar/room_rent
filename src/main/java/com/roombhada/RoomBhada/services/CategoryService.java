package com.roombhada.RoomBhada.services;

import com.roombhada.RoomBhada.entities.Category;

import java.util.Set;

//room category or room type
public interface CategoryService {

    //create
    public Category createCategory(Category category);

    //update
     public Category updateCategory(Category category);

    //get by id
    public Category getCategoryById(Long categoryId);

    //get all
    public Set<Category> getAllCategory();

    //delete by id
    public void deleteCategory(Long categoryId);

}
