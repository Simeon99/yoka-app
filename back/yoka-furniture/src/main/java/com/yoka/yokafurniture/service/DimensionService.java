package com.yoka.yokafurniture.service;

import com.yoka.yokafurniture.payload.Dimension.DimensionDto;
import com.yoka.yokafurniture.payload.Dimension.DimensionResponse;


public interface DimensionService {

    public DimensionDto createDimension(DimensionDto dimensionDto, long articleId);
    public DimensionResponse getAllDimensionsByArticleId(long articleId);
    public void addDimensionToArticle(long dimensionId, long articleId);
    public void deleteDimension(long dimensionId, long articleId);
    public DimensionDto updateDimension(DimensionDto dimensionDto, long dimensionId);
}
