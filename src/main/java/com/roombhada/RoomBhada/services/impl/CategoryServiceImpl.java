package com.roombhada.RoomBhada.services.impl;

import com.roombhada.RoomBhada.entities.Category;
import com.roombhada.RoomBhada.repositories.CategoryRepository;
import com.roombhada.RoomBhada.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

//room category or room type
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {

        return this.categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return this.categoryRepository.findById(categoryId).get();
    }

    @Override
    public Set<Category> getAllCategory() {
        return new LinkedHashSet<> (this.categoryRepository.findAll());
    }

    @Override
    public void deleteCategory(Long categoryId) {
        this.categoryRepository.deleteById(categoryId);
    }
}
