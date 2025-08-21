package com.ecommerce.sb_project.repos;

import com.ecommerce.sb_project.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface categoryRepository extends JpaRepository<Category, Long> {

    Category findByCategoryNameIgnoreCase(String categoryName);
}
