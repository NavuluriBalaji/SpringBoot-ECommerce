package com.ecommerce.sb_project.service;

import com.ecommerce.sb_project.excpetion.APIException;
import com.ecommerce.sb_project.excpetion.ResourceNotFoundException;
import com.ecommerce.sb_project.model.Category;
import com.ecommerce.sb_project.payLoad.categoryResponse;
import com.ecommerce.sb_project.repos.categoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class categoryServiceImpl implements categoryService {

    public long nextId;

//    private final List<Category> categories = new ArrayList<>();
    private final categoryRepository categoryRepository;
//    @Autowired
//    private ModelMapper;
    

    public categoryServiceImpl(com.ecommerce.sb_project.repos.categoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public categoryResponse getCategories() {
        categoryResponse ls = categoryRepository.findAll();
        if (ls.isEmpty()) {
            throw new APIException("No category created t ill now");
        }
        return ls;
    }
//        if()
//        return categoryRepository.findAll();
//    }


    @Override
    public void createCategory(Category category) {
        Category savedCategory=categoryRepository.findByCategoryName(category.getCategoryName());
        if (savedCategory!=null){
            throw new APIException("Category with"+category.getCategoryName()+"Already Exist");
        }
        category.setCategoryId(nextId++);
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(long categoryId) {
        Object httpStatus;
        List<Category> categories=categoryRepository.findAll();
        Category category = categories.stream().filter(c->c.getCategoryId().equals(categoryId)).
                findFirst()
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource Not Found"));
//        if category == null
//                return "not found";
        categoryRepository.delete(category);
        return "Deleted Succesfully";
    }

    @Override
    public void updateCategory(Category category, Long categoryId) {
        Category categoryToUpdate = categoryRepository.findById(categoryId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found"));
                .orElseThrow(() -> new ResourceNotFoundException("Category","categoryID",categoryId));
        categoryToUpdate.setCategoryName(category.getCategoryName());
        categoryRepository.save(categoryToUpdate);
    }


}
