package com.yoka.yokafurniture.repository;

import com.yoka.yokafurniture.entity.Dimension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DimensionRepository extends JpaRepository<Dimension, Long> {

    List<Dimension> findDimensionByArticlesId(Long articleId);

}
