package com.yoka.yokafurniture.service.impl;

import com.yoka.yokafurniture.entity.Article;
import com.yoka.yokafurniture.entity.Colour;
import com.yoka.yokafurniture.exception.AppAPIExceptions;
import com.yoka.yokafurniture.exception.ResourceNotFoundException;
import com.yoka.yokafurniture.payload.Colour.ColourDto;
import com.yoka.yokafurniture.payload.Colour.ColourResponse;
import com.yoka.yokafurniture.repository.ArticleRepository;
import com.yoka.yokafurniture.repository.ColourRepository;
import com.yoka.yokafurniture.service.ColourService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class ColourServiceImpl implements ColourService {

    private ColourRepository colourRepository;
    private ArticleRepository articleRepository;
    private ModelMapper modelMapper;

    public ColourServiceImpl(ColourRepository colourRepository, ArticleRepository articleRepository, ModelMapper modelMapper) {
        this.colourRepository = colourRepository;
        this.articleRepository = articleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ColourDto createColour(ColourDto colourDto, long articleId) {

        Article article = articleRepository.findById(articleId).orElseThrow(()-> new ResourceNotFoundException("Article", "id", articleId));

        Colour colour = mapToColour(colourDto);

        colour.addArticles(article);

        Colour colourResponse = colourRepository.save(colour);

        return mapToDto(colourResponse);
    }

    @Override
    public List<ColourResponse> getColorsByArticleId(long articleId, Locale locale) {

        Article article = articleRepository.findById(articleId).orElseThrow(()->new ResourceNotFoundException("Article", "id", articleId));

        List<Colour> colours = colourRepository.findColoursByArticlesId(articleId);

        if(locale.toString().equalsIgnoreCase("sr")){
            return colours.stream().map(colour -> mapToDtoSr(colour)).toList();
        }else if(locale.toString().equalsIgnoreCase("en")){
            return colours.stream().map(colour -> mapToDtoEn(colour)).toList();
        }


        return colours.stream().map( colour -> mapToDtoEn(colour)).toList();
    }

    @Override
    public ColourResponse getColourById(long colourId, Locale locale) {
        Colour colour = colourRepository.findById(colourId).orElseThrow(()->new ResourceNotFoundException("Colour", "id", colourId));

        if(locale.toString().equalsIgnoreCase("sr")){
            return mapToDtoSr(colour);
        }else if(locale.toString().equalsIgnoreCase("en")){
            return mapToDtoEn(colour);
        }

        return mapToDtoEn(colour);
    }

    @Override
    public void addColourToArticle(long colourId, long articleId) {

        Article article = articleRepository.findById(articleId).orElseThrow(()->new ResourceNotFoundException("Article", "id", articleId));

        Colour colour = colourRepository.findById(colourId).orElseThrow(()->new ResourceNotFoundException("Colour", "id", colourId));

        colour.getArticles().forEach(article1 -> {
            if(article1.getId() == articleId) throw new AppAPIExceptions(HttpStatus.BAD_REQUEST, "Article already have this colour.");
        });

        colour.addArticles(article);
        colourRepository.save(colour);

    }

    @Override
    public void deleteColour(long colourId, long articleId) {

        Article article = articleRepository.findById(articleId).orElseThrow(()->new ResourceNotFoundException("Article", "id", articleId));

        Colour colour = colourRepository.findById(colourId).orElseThrow(()->new ResourceNotFoundException("Colour", "id", colourId));

        colour.deleteArticles(article);

        colourRepository.save(colour);

    }

    @Override
    public ColourDto updateColour(ColourDto colourDto, long colourId) {

        Colour colour = colourRepository.findById(colourId).orElseThrow(()->new ResourceNotFoundException("Colour", "id", colourId));

        colour.setName(colourDto.getName());
        colour.setNameSr(colourDto.getNameSr());

        Colour updatedColour = colourRepository.save(colour);

        return mapToDto(updatedColour);
    }

    private ColourDto mapToDto(Colour colour){
        ColourDto colourDto = modelMapper.map(colour, ColourDto.class);
        return colourDto;
    }

    private ColourResponse mapToDtoSr(Colour colour){
        ColourResponse colourResponse = new ColourResponse();
        colourResponse.setId(colour.getId());
        colourResponse.setName(colour.getNameSr());
        colourResponse.setMediaLink(colour.getMediaLink());

        return colourResponse;
    }

    private ColourResponse mapToDtoEn(Colour colour){
        ColourResponse colourResponse = modelMapper.map(colour, ColourResponse.class);
        return colourResponse;
    }

    private Colour mapToColour(ColourDto colourDto){
        Colour colour = modelMapper.map(colourDto, Colour.class);
        return colour;
    }

}
