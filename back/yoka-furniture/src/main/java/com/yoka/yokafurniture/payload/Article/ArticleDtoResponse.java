package com.yoka.yokafurniture.payload.Article;

import com.yoka.yokafurniture.payload.ArticleImage.ArticleImageDto;
import com.yoka.yokafurniture.payload.Colour.ColourDto;
import com.yoka.yokafurniture.payload.Description.DescriptionDto;
import com.yoka.yokafurniture.payload.Dimension.DimensionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDtoResponse {
    private long id;
    private String name;
    private double discount;
    private Set<DescriptionDto> descriptions;
    private Set<ColourDto> colours;
    private Set<DimensionDto> dimensions;
    private Set<ArticleImageDto> articleImages = new HashSet<>();
}
