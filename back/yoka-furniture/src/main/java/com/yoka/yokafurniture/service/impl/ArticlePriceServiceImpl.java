package com.yoka.yokafurniture.service.impl;

import com.yoka.yokafurniture.entity.Article;
import com.yoka.yokafurniture.entity.ArticlePrice;
import com.yoka.yokafurniture.exception.ResourceNotFoundException;
import com.yoka.yokafurniture.payload.ArticlePrice.ArticlePriceDto;
import com.yoka.yokafurniture.repository.ArticlePriceRepository;
import com.yoka.yokafurniture.repository.ArticleRepository;
import com.yoka.yokafurniture.service.ArticlePriceService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ArticlePriceServiceImpl implements ArticlePriceService {

    private ArticlePriceRepository articlePriceRepository;
    private ArticleRepository articleRepository;
    private ModelMapper mapper;

    public ArticlePriceServiceImpl(ArticlePriceRepository articlePriceRepository, ArticleRepository articleRepository, ModelMapper mapper) {
        this.articlePriceRepository = articlePriceRepository;
        this.articleRepository = articleRepository;
        this.mapper = mapper;
    }

    @Override
    public ArticlePriceDto createArticlePrice(ArticlePriceDto articlePriceDto, long articleId) {

        ArticlePrice articlePrice = mapDtoToArticlePrice(articlePriceDto);

        Article article = articleRepository.findById(articleId).orElseThrow(() -> new ResourceNotFoundException("Article","id", articleId));

        articlePrice.setArticle(article);
        article.getArticlePrice().add(articlePrice);

        ArticlePrice response = articlePriceRepository.save(articlePrice);


        return mapArticlePriceToDto(response);
    }

    @Override
    public ArticlePriceDto findByDimensions(double width, double height, double length, long articleId) {

        ArticlePrice articlePrice = articlePriceRepository.findByArticleIdAndDimensions(articleId,width,length,height);


        return mapArticlePriceToDto(articlePrice);
    }

    private ArticlePriceDto mapArticlePriceToDto(ArticlePrice articlePrice){
        ArticlePriceDto articlePriceDto = mapper.map(articlePrice, ArticlePriceDto.class);
        return articlePriceDto;
    }

    private ArticlePrice mapDtoToArticlePrice(ArticlePriceDto articlePriceDto){
        ArticlePrice articlePrice = mapper.map(articlePriceDto, ArticlePrice.class);
        return  articlePrice;
    }

}
