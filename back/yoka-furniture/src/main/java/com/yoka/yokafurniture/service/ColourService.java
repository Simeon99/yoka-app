package com.yoka.yokafurniture.service;

import com.yoka.yokafurniture.payload.ColourDto;
import com.yoka.yokafurniture.payload.ColourResponse;

import java.util.List;
import java.util.Locale;

public interface ColourService {
    public ColourDto createColour(ColourDto colourDto, long articleId);
    public List<ColourResponse> getColorsByArticleId(long articleId, Locale locale);
    public void addColourToArticle(long colourId, long articleId);
    public void deleteColour(long colourId, long articleId);
    public ColourDto updateColour(ColourDto colourDto, long colourId);
}
