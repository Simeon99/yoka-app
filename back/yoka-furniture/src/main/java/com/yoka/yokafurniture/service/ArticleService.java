package com.yoka.yokafurniture.service;

import com.yoka.yokafurniture.payload.ArticleDto;
import com.yoka.yokafurniture.payload.ArticleResponse;

import java.util.List;
import java.util.Locale;

public interface ArticleService {
    public ArticleDto createArticle(ArticleDto articleDto, long categoryId);
    public ArticleResponse getAllArticles(int pageNo, int pageSize,String sortBy, String sortDir);
    public List<ArticleDto> getAllArticlesByCategoryId(long categoryId);
    public ArticleDto getArticleById(long id);
    public ArticleDto upadteArticle(ArticleDto articleDto, long id);
    public void deleteArticle(long id);
}
