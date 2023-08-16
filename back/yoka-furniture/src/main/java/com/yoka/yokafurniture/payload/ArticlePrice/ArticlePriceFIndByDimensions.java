package com.yoka.yokafurniture.payload.ArticlePrice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticlePriceFIndByDimensions {
    private double width;
    private double length;
    private double height;
}
