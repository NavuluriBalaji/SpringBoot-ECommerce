package com.ecommerce.sb_project.service;

import com.ecommerce.sb_project.payLoad.categoryDTO;
import com.ecommerce.sb_project.payLoad.categoryResponse;

public interface categoryService {
    categoryResponse getCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    void createCategory(categoryDTO categoryDTO);

    String deleteCategory(long categoryId);

    void updateCategory(categoryDTO categoryDTO, Long categoryId);
}
