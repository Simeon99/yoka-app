package com.yoka.yokafurniture.repository;

import com.yoka.yokafurniture.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
