package com.yoka.yokafurniture.service;

import com.yoka.yokafurniture.entity.Colour;
import com.yoka.yokafurniture.payload.ColourDto;

import java.util.List;

public interface ColourService {
    public ColourDto createColour(ColourDto colourDto, long articleId);
    public List<ColourDto> getColorsByArticleId(long articleId);
    public void addColourToArticle(long colourId, long articleId);
    public void deleteColour(long colourId, long articleId);
    public ColourDto updateColour(ColourDto colourDto, long colourId);
}
