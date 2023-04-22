package com.yoka.yokafurniture.service;

import com.yoka.yokafurniture.payload.ArticleDto;
import com.yoka.yokafurniture.payload.ArticleResponse;

import java.util.List;

public interface ArticleService {
    public ArticleDto createArticle(ArticleDto articleDto);
    public ArticleResponse getAllArticles(int pageNo, int pageSize,String sortBy, String sortDir);
    public ArticleDto getArticleById(long id);
    public ArticleDto upadteArticle(ArticleDto articleDto, long id);
    public void deleteArticle(long id);
}
