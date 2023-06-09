package com.yoka.yokafurniture.service.impl;

import com.yoka.yokafurniture.entity.Article;
import com.yoka.yokafurniture.entity.Category;
import com.yoka.yokafurniture.exception.ResourceNotFoundException;
import com.yoka.yokafurniture.payload.ArticleDto;
import com.yoka.yokafurniture.payload.ArticleResponse;
import com.yoka.yokafurniture.repository.ArticleRepository;
import com.yoka.yokafurniture.repository.CategoryRepository;
import com.yoka.yokafurniture.service.ArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;
    private CategoryRepository categoryRepository;
    private ModelMapper mapper;

    public ArticleServiceImpl(ArticleRepository articleRepository, CategoryRepository categoryRepository, ModelMapper mapper) {
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public ArticleDto createArticle(ArticleDto articleDto, long categoryId) {

        Article article = mapToEntity(articleDto);

        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id", categoryId));

        article.setCategory(category);

        Article newArticle = articleRepository.save(article);

        ArticleDto articleResponse = mapToDto(newArticle);

        return articleResponse;
    }

    @Override
    public ArticleResponse getAllArticles(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);

        Page<Article> articles= articleRepository.findAll(pageable);

        List<Article> articleList = articles.getContent();

        List<ArticleDto> content = articleList.stream().map(article -> mapToDto(article)).collect(Collectors.toList());

        ArticleResponse articleResponse = new ArticleResponse();
        articleResponse.setArticleDtos(content);
        articleResponse.setPageNo(articles.getNumber());
        articleResponse.setPageSize(articles.getSize());
        articleResponse.setTotalElements(articles.getTotalElements());
        articleResponse.setTotalPages(articles.getTotalPages());
        articleResponse.setLast(articles.isLast());

        return articleResponse;
    }

    @Override
    public List<ArticleDto> getAllArticlesByCategoryId(long categoryId) {

        List<Article> articles= articleRepository.findByCategoryId(categoryId);

        return articles.stream().map(article -> mapToDto(article)).collect(Collectors.toList());
    }

    @Override
    public ArticleDto getArticleById(long id) {

        Article article = articleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Article","id", id));

        return mapToDto(article);
    }

    @Override
    public ArticleDto upadteArticle(ArticleDto articleDto, long id) {

        Article article = articleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Article","id", id));

        article.setName(articleDto.getName());
        article.setPrice(articleDto.getPrice());
        article.setDiscount(articleDto.getDiscount());

        articleRepository.save(article);

        return mapToDto(article);
    }

    @Override
    public void deleteArticle(long id) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Article","id", id));
        articleRepository.delete(article);
    }

    private ArticleDto mapToDto(Article article){

        ArticleDto articleDto = mapper.map(article, ArticleDto.class);

        return articleDto;
    }

    private Article mapToEntity(ArticleDto articleDto){

        Article article = mapper.map(articleDto, Article.class);

        return article;

    }
}
