package com.ecommerce.sb_project.service;

import com.ecommerce.sb_project.excpetion.APIException;
import com.ecommerce.sb_project.excpetion.ResourceNotFoundException;
import com.ecommerce.sb_project.model.Category;
import com.ecommerce.sb_project.payLoad.categoryDTO;
import com.ecommerce.sb_project.payLoad.categoryResponse;
import com.ecommerce.sb_project.repos.categoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class categoryServiceImpl implements categoryService {

    private final categoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public categoryServiceImpl(categoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public categoryResponse getCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new APIException("No categories found.");
        }

        List<categoryDTO> categoryDTOs = categories.stream()
                .map(category -> modelMapper.map(category, categoryDTO.class))
                .collect(Collectors.toList());

        return new categoryResponse(categoryDTOs);
    }

    @Override
    public void createCategory(Category category) {
        if (categoryRepository.findByCategoryName(category.getCategoryName()) != null) {
            throw new APIException("Category with name '" + category.getCategoryName() + "' already exists.");
        }
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "ID", categoryId));
        categoryRepository.delete(category);
        return "Category deleted successfully.";
    }

    @Override
    public void updateCategory(Category category, Long categoryId) {
        Category categoryToUpdate = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "ID", categoryId));
        categoryToUpdate.setCategoryName(category.getCategoryName());
        categoryRepository.save(categoryToUpdate);
    }
}