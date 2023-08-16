package com.yoka.yokafurniture.service.impl;

import com.yoka.yokafurniture.entity.Article;
import com.yoka.yokafurniture.entity.ArticleImage;
import com.yoka.yokafurniture.exception.AppAPIExceptions;
import com.yoka.yokafurniture.exception.ResourceNotFoundException;
import com.yoka.yokafurniture.payload.ArticleImage.ArticleImageDto;
import com.yoka.yokafurniture.repository.ArticleImageRepository;
import com.yoka.yokafurniture.repository.ArticleRepository;
import com.yoka.yokafurniture.service.ArticleImageService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleImageServiceImpl implements ArticleImageService {

    private ArticleRepository articleRepository;
    private ArticleImageRepository articleImageRepository;
    private ModelMapper modelMapper;

    public ArticleImageServiceImpl(ArticleRepository articleRepository, ArticleImageRepository articleImageRepository, ModelMapper modelMapper) {
        this.articleRepository = articleRepository;
        this.articleImageRepository = articleImageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ArticleImageDto createArticleImage(ArticleImageDto articleImageDto, long articleId) {

        Article article = articleRepository.findById(articleId).orElseThrow(()-> new ResourceNotFoundException("Article", "id", articleId));

        ArticleImage articleImage = mapToArticleImage(articleImageDto);

        articleImage.setArticle(article);

        ArticleImage articleImageResponse = articleImageRepository.save(articleImage);


        return mapToDto(articleImageResponse);
    }

    @Override
    public ArticleImageDto createImage(ArticleImageDto articleImageDto) {
        ArticleImage articleImage = mapToArticleImage(articleImageDto);

        System.out.println("AAAAAAAAAAAAAAAAAAA: "+articleImage.getArticle().toString());

        ArticleImage articleImageResponse = articleImageRepository.save(articleImage);


        return mapToDto(articleImageResponse);
    }

    @Override
    public List<ArticleImageDto> getAllByArticleId(long articleId) {

        List<ArticleImage> articles = articleImageRepository.findByArticleId(articleId);

        return articles.stream().map(articleImage -> mapToDto(articleImage)).toList();
    }

    @Override
    public void deleteArticleImage(long imageId, long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(()-> new ResourceNotFoundException("Article", "id", articleId));

        ArticleImage articleImage = articleImageRepository.findById(imageId).orElseThrow(()-> new ResourceNotFoundException("Article image", "id", imageId));

        if(articleImage.getArticle().getId() != article.getId()){
            throw new AppAPIExceptions(HttpStatus.BAD_REQUEST, "Image does not belong to this article.");
        }

        articleImageRepository.delete(articleImage);

    }

    private ArticleImage mapToArticleImage(ArticleImageDto articleImageDto){
        ArticleImage articleImage = modelMapper.map(articleImageDto, ArticleImage.class);

        return articleImage;
    }

    private ArticleImageDto mapToDto(ArticleImage articleImage){
        ArticleImageDto articleImageDto =modelMapper.map(articleImage, ArticleImageDto.class);

        return articleImageDto;
    }





}
