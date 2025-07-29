package com.ecommerce.sb_project.service;

import com.ecommerce.sb_project.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;
public interface categoryService {
    List<Category> getCategories();
    void createCategory (Category category);
    String deleteCategory(long categoryId);
    void updateCategory(Category category, Long categoryId);
}
