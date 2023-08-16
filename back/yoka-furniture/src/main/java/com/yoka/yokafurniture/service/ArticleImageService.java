package com.yoka.yokafurniture.service;

import com.yoka.yokafurniture.payload.ArticleImage.ArticleImageDto;

import java.util.List;

public interface ArticleImageService {

    public ArticleImageDto createArticleImage(ArticleImageDto articleImageDto, long articleId);
    public ArticleImageDto createImage(ArticleImageDto articleImageDto);
    public List<ArticleImageDto> getAllByArticleId(long articleId);
    public void deleteArticleImage(long imageId, long articleId);

}
