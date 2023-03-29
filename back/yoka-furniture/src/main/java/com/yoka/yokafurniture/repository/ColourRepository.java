package com.yoka.yokafurniture.repository;

import com.yoka.yokafurniture.entity.Colour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColourRepository extends JpaRepository<Colour, Long> {
    List<Colour> findColoursByArticlesId(Long articleId);

}
