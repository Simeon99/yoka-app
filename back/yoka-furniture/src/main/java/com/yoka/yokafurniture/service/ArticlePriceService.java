package com.yoka.yokafurniture.service;

import com.yoka.yokafurniture.payload.ArticlePrice.ArticlePriceDto;

public interface ArticlePriceService {
    public ArticlePriceDto createArticlePrice(ArticlePriceDto articlePriceDto, long articleId);
    public ArticlePriceDto findByDimensions(double width, double height, double length, long articleId);
}
