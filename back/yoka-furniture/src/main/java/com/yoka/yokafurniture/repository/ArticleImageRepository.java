package com.yoka.yokafurniture.repository;

import com.yoka.yokafurniture.entity.ArticleImage;
import com.yoka.yokafurniture.entity.Description;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleImageRepository extends JpaRepository<ArticleImage, Long> {

    List<ArticleImage> findByArticleId(long articleId);

}
