package com.yoka.yokafurniture.repository;

import com.yoka.yokafurniture.entity.Description;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DescriptionRepository extends JpaRepository<Description, Long> {

    List<Description> findByArticleId(long articleId);

}
