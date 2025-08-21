package com.ecommerce.sb_project.service;

import com.ecommerce.sb_project.excpetion.APIException;
import com.ecommerce.sb_project.excpetion.ResourceNotFoundException;
import com.ecommerce.sb_project.model.Category;
import com.ecommerce.sb_project.payLoad.categoryDTO;
import com.ecommerce.sb_project.payLoad.categoryResponse;
import com.ecommerce.sb_project.repos.categoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public categoryResponse getCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort categorySort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, categorySort);
        Page<Category> categoryPage = categoryRepository.findAll(pageDetails);
        List<Category> categories = categoryPage.getContent();

        if (categories.isEmpty()) {
            throw new APIException("No categories found.");
        }

        List<categoryDTO> categoryDTOs = categories.stream()
                .map(category -> modelMapper.map(category, categoryDTO.class))
                .collect(Collectors.toList());

        categoryResponse response = new categoryResponse();
        response.setContent(categoryDTOs);
        response.setPageNumber(categoryPage.getNumber());
        response.setPageSize(categoryPage.getSize());
        response.setTotalElements(categoryPage.getTotalElements());
        response.setTotalPages(categoryPage.getTotalPages());
        response.setLastPage(categoryPage.isLast());

        return response;
    }

    @Override
    public void createCategory(categoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        if (categoryRepository.findByCategoryNameIgnoreCase(category.getCategoryName()) != null) {
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
    public void updateCategory(categoryDTO categoryDTO, Long categoryId) {
        Category categoryToUpdate = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "ID", categoryId));

        if (categoryRepository.findByCategoryNameIgnoreCase(categoryDTO.getCategory()) != null && !categoryToUpdate.getCategoryId().equals(categoryDTO.getCategoryID())) {
            throw new APIException("Category with name '" + categoryDTO.getCategory() + "' already exists.");
        }

        categoryToUpdate.setCategoryName(categoryDTO.getCategory());
        categoryRepository.save(categoryToUpdate);
    }
}