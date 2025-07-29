package com.ecommerce.sb_project.service;

import com.ecommerce.sb_project.model.Category;
import com.ecommerce.sb_project.payLoad.categoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;
public interface categoryService {
    categoryResponse getCategories();
    void createCategory (Category category);
    String deleteCategory(long categoryId);
    void updateCategory(Category category, Long categoryId);
}
