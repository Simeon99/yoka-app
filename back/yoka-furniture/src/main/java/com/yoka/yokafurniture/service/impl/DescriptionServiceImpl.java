package com.yoka.yokafurniture.service.impl;

import com.yoka.yokafurniture.entity.Article;
import com.yoka.yokafurniture.entity.Description;
import com.yoka.yokafurniture.exception.AppAPIExceptions;
import com.yoka.yokafurniture.exception.ResourceNotFoundException;
import com.yoka.yokafurniture.payload.Description.DescriptionDto;
import com.yoka.yokafurniture.payload.Description.DescriptionResponse;
import com.yoka.yokafurniture.repository.ArticleRepository;
import com.yoka.yokafurniture.repository.DescriptionRepository;
import com.yoka.yokafurniture.service.DescriptionService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class DescriptionServiceImpl implements DescriptionService {

    private DescriptionRepository descriptionRepository;
    private ArticleRepository articleRepository;

    private ModelMapper modelMapper;

    public DescriptionServiceImpl(DescriptionRepository descriptionRepository, ArticleRepository articleRepository, ModelMapper modelMapper) {
        this.descriptionRepository = descriptionRepository;
        this.articleRepository = articleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DescriptionDto createDescription(DescriptionDto descriptionDto, long articleId) {

        Article article = articleRepository.findById(articleId).orElseThrow(()-> new ResourceNotFoundException("Article", "id", articleId));

        Description description = mapToDescription(descriptionDto);

        description.setArticle(article);

        Description descriptionResponse = descriptionRepository.save(description);


        return mapToDto(descriptionResponse);
    }

    @Override
    public List<DescriptionResponse> getAllDescriptionsByArticleId(long articleId, Locale locale) {

        List<Description> descriptions = descriptionRepository.findByArticleId(articleId);

        if(locale.toString().equalsIgnoreCase("sr")){
            return descriptions.stream().map(description -> mapToDtoSr(description)).toList();
        }else if(locale.toString().equalsIgnoreCase("en")){
            return descriptions.stream().map(description -> mapToDtoEn(description)).toList();
        }

        return descriptions.stream().map(description -> mapToDtoEn(description)).toList();

    }

    @Override
    public DescriptionResponse getDescriptionById(long articleId, long descriptionId, Locale locale) {

        Article article = articleRepository.findById(articleId).orElseThrow(()-> new ResourceNotFoundException("Article", "id", articleId));

        Description description = descriptionRepository.findById(descriptionId).orElseThrow(()-> new ResourceNotFoundException("Description", "id",descriptionId));

        if(description.getArticle().getId() != article.getId()){
            throw new AppAPIExceptions(HttpStatus.BAD_REQUEST, "Description does not belong to article.");
        }else if (locale.toString().equalsIgnoreCase("sr")){
            return mapToDtoSr(description);
        }else if(locale.toString().equalsIgnoreCase("en")){
            return  mapToDtoEn(description);
        }

        return mapToDtoEn(description);
    }

    @Override
    public DescriptionDto updateDescription(long articleId, long descriptionId, DescriptionDto descriptionDto) {
        Article article = articleRepository.findById(articleId).orElseThrow(()-> new ResourceNotFoundException("Article", "id", articleId));

        Description description = descriptionRepository.findById(descriptionId).orElseThrow(()-> new ResourceNotFoundException("Description", "id",descriptionId));

        description.setDescription(descriptionDto.getDescription());
        description.setDescriptionSr(descriptionDto.getDescriptionSr());

        Description updatedDescription = descriptionRepository.save(description);

        return mapToDto(description);
    }

    @Override
    public void deleteDescription(long articleId, long descriptionId) {
        Article article = articleRepository.findById(articleId).orElseThrow(()-> new ResourceNotFoundException("Article", "id", articleId));

        Description description = descriptionRepository.findById(descriptionId).orElseThrow(()-> new ResourceNotFoundException("Description", "id",descriptionId));

        if(description.getArticle().getId() != article.getId()){
            throw new AppAPIExceptions(HttpStatus.BAD_REQUEST, "Description does not belong to this article.");
        }

        descriptionRepository.delete(description);
    }

    private DescriptionDto mapToDto(Description description){
        DescriptionDto descriptionDto = modelMapper.map(description, DescriptionDto.class);
        return descriptionDto;
    }

    private DescriptionResponse mapToDtoSr(Description description){
        DescriptionResponse descriptionResponse = new DescriptionResponse();
        descriptionResponse.setDescription(description.getDescriptionSr());
        descriptionResponse.setId(description.getId());
        return descriptionResponse;
    }

    private DescriptionResponse mapToDtoEn(Description description){
        DescriptionResponse descriptionResponse = modelMapper.map(description, DescriptionResponse.class);
        return descriptionResponse;
    }

    private Description mapToDescription(DescriptionDto descriptionDto){
        Description description = modelMapper.map(descriptionDto, Description.class);
        return description;
    }


}
