package com.yoka.yokafurniture.repository;

import com.yoka.yokafurniture.entity.ArticlePrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArticlePriceRepository extends JpaRepository<ArticlePrice, Long> {

    @Query("SELECT ap FROM ArticlePrice ap JOIN ap.article a WHERE a.id = :articleId AND ap.width = :width AND ap.length = :length AND ap.height = :height")
    ArticlePrice findByArticleIdAndDimensions(@Param("articleId") Long articleId, @Param("width") double width, @Param("length") double length, @Param("height") double height);

}
