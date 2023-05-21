package com.yoka.yokafurniture.repository;

import com.yoka.yokafurniture.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByCategoryId(long categoryId);

}
