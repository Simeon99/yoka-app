package com.yoka.yokafurniture.repository;

import com.yoka.yokafurniture.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Long> {
}
