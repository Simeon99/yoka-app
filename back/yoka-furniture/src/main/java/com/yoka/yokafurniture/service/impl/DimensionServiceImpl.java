package com.yoka.yokafurniture.service.impl;

import com.yoka.yokafurniture.entity.Article;
import com.yoka.yokafurniture.entity.Dimension;
import com.yoka.yokafurniture.exception.AppAPIExceptions;
import com.yoka.yokafurniture.exception.ResourceNotFoundException;
import com.yoka.yokafurniture.payload.Dimension.DimensionDto;
import com.yoka.yokafurniture.payload.Dimension.DimensionResponse;
import com.yoka.yokafurniture.payload.Dimension.DimensionType;
import com.yoka.yokafurniture.repository.ArticleRepository;
import com.yoka.yokafurniture.repository.DimensionRepository;
import com.yoka.yokafurniture.service.DimensionService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DimensionServiceImpl implements DimensionService {

    private DimensionRepository dimensionRepository;
    private ArticleRepository articleRepository;
    private ModelMapper modelMapper;

    public DimensionServiceImpl(DimensionRepository dimensionRepository, ArticleRepository articleRepository, ModelMapper modelMapper) {
        this.dimensionRepository = dimensionRepository;
        this.articleRepository = articleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DimensionDto createDimension(DimensionDto dimensionDto, long articleId) {

        Article article = articleRepository.findById(articleId).orElseThrow(()-> new ResourceNotFoundException("Article", "id", articleId));

        Dimension dimension = mapToDimension(dimensionDto);

        dimension.addArticles(article);

        Dimension dimensionResponse = dimensionRepository.save(dimension);

        return mapToDto(dimensionResponse);
    }

    @Override
    public DimensionResponse getAllDimensionsByArticleId(long articleId) {

        Article article = articleRepository.findById(articleId).orElseThrow(()->new ResourceNotFoundException("Article", "id", articleId));

        List<Dimension> dimensions = dimensionRepository.findDimensionByArticlesId(articleId);


        DimensionResponse dimensionResponse = new DimensionResponse();

        dimensionResponse.setWidths(dimensions.stream().filter(dimension -> dimension.getType().toString().equalsIgnoreCase("width")).map(dimension -> dimension.getDimension()).toList());
        dimensionResponse.setHeights(dimensions.stream().filter(dimension -> dimension.getType().toString().equalsIgnoreCase("height")).map(dimension -> dimension.getDimension()).toList());
        dimensionResponse.setLengths(dimensions.stream().filter(dimension -> dimension.getType().toString().equalsIgnoreCase("length")).map(dimension -> dimension.getDimension()).toList());

        return dimensionResponse;
    }

    @Override
    public void addDimensionToArticle(long dimensionId, long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(()->new ResourceNotFoundException("Article", "id", articleId));

        Dimension dimension = dimensionRepository.findById(dimensionId).orElseThrow(()-> new ResourceNotFoundException("Dimension","id", dimensionId));

        dimension.getArticles().forEach(article1 -> {
            if(article1.getId() == articleId) throw new AppAPIExceptions(HttpStatus.BAD_REQUEST, "Article already have this dimension.");
        });

        dimension.addArticles(article);

        dimensionRepository.save(dimension);

    }

    @Override
    public void deleteDimension(long dimensionId, long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(()->new ResourceNotFoundException("Article", "id", articleId));

        Dimension dimension = dimensionRepository.findById(dimensionId).orElseThrow(()-> new ResourceNotFoundException("Dimension","id", dimensionId));

        dimension.deleteArticle(article);

        dimensionRepository.save(dimension);

    }

    @Override
    public DimensionDto updateDimension(DimensionDto dimensionDto, long dimensionId) {

        Dimension dimension = dimensionRepository.findById(dimensionId).orElseThrow(()-> new ResourceNotFoundException("Dimension","id", dimensionId));

        dimension.setDimension(dimensionDto.getDimension());
        String type = dimensionDto.getType();
        dimension.setType(DimensionType.valueOf(type));
        dimensionRepository.save(dimension);
        return mapToDto(dimension);
    }

    private DimensionDto mapToDto(Dimension dimension){
        DimensionDto dimensionDto = modelMapper.map(dimension, DimensionDto.class);
        return dimensionDto;
    }

    private Dimension mapToDimension(DimensionDto dimensionDto){
        Dimension dimension = modelMapper.map(dimensionDto, Dimension.class);
        return dimension;
    }

}
