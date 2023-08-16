package com.yoka.yokafurniture.service;

import com.yoka.yokafurniture.payload.Article.ArticleDto;
import com.yoka.yokafurniture.payload.Article.ArticleDtoResponse;
import com.yoka.yokafurniture.payload.Article.ArticleResponse;

import java.util.List;
import java.util.Locale;

public interface ArticleService {
    public ArticleDto createArticle(ArticleDto articleDto, long categoryId);
    public ArticleResponse getAllArticles(int pageNo, int pageSize,String sortBy, String sortDir, Locale locale);
    public List<ArticleDtoResponse> getAllArticlesByCategoryId(long categoryId, Locale locale);
    public ArticleDtoResponse getArticleById(long id, Locale locale);
    public ArticleDto upadteArticle(ArticleDto articleDto, long id);
    public void deleteArticle(long id);
}
