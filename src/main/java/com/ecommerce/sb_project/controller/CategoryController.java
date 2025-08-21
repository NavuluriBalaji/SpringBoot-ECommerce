package com.ecommerce.sb_project.controller;

import com.ecommerce.sb_project.config.appConstants;
import com.ecommerce.sb_project.payLoad.categoryDTO;
import com.ecommerce.sb_project.payLoad.categoryResponse;
import com.ecommerce.sb_project.service.categoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    private final categoryService categoryService;

    @Autowired
    public CategoryController(categoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/api/public/categories")
    public categoryResponse getCategories(
            @RequestParam(name = "pageNumber", defaultValue = appConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = appConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = "categoryId", required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = appConstants.SORT_DIR, required = false) String sortDir) {
        return categoryService.getCategories(pageNumber, pageSize, sortBy, sortDir);
    }

    @PostMapping("api/public/addcategory")
    public ResponseEntity<String> createCategory(@Valid @RequestBody categoryDTO categoryDTO) {
        categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>("Category Added Successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("api/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable long categoryId) {
        String status = categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PutMapping("api/admin/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@Valid @RequestBody categoryDTO categoryDTO, @PathVariable Long categoryId) {
        categoryService.updateCategory(categoryDTO, categoryId);
        return new ResponseEntity<>("Category Updated Successfully", HttpStatus.OK);
    }
}
