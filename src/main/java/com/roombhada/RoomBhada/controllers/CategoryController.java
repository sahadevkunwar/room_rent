package com.roombhada.RoomBhada.controllers;

import com.roombhada.RoomBhada.entities.Category;
import com.roombhada.RoomBhada.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//room category or room type
@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //add category
    @PostMapping("/")
    public ResponseEntity<?> addCategory(@RequestBody Category category) {

        Category category1 =  this.categoryService.createCategory(category);
        return ResponseEntity.ok(category1);
    }

    //update category
    @PutMapping("/")
    public Category updateCategory(@RequestBody Category category) {
        return  this.categoryService.updateCategory(category);
    }

    //get category by id
    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getCategory(@PathVariable("categoryId") Long categoryId) {
        return ResponseEntity.ok().body(this.categoryService.getCategoryById(categoryId));
    }

    //get all categories
    @GetMapping("/")
    public ResponseEntity<?> getCategories() {
        return ResponseEntity.ok(this.categoryService.getAllCategory());
    }

    //delete category by id
    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") Long categoryId) {
         this.categoryService.deleteCategory(categoryId);
    }



}
