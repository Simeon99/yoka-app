package com.yoka.yokafurniture.payload.ArticlePrice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticlePriceDto {
    private long id;

    private double width;
    private double length;
    private double height;
    private double price;
}
