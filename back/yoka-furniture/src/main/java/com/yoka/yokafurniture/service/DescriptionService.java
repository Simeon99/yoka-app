package com.yoka.yokafurniture.service;

import com.yoka.yokafurniture.payload.Description.DescriptionDto;
import com.yoka.yokafurniture.payload.Description.DescriptionResponse;

import java.util.List;
import java.util.Locale;

public interface DescriptionService {
    public DescriptionDto createDescription(DescriptionDto descriptionDto, long articleId);
    public List<DescriptionResponse> getAllDescriptionsByArticleId(long articleId, Locale locale);
    public DescriptionResponse getDescriptionById(long articleId, long descriptionId, Locale locale);
    public DescriptionDto updateDescription(long articleId, long descriptionId, DescriptionDto descriptionDto);
    public void deleteDescription(long articleId, long descriptionId);
}
