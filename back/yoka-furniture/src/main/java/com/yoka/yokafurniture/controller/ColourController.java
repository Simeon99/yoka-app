package com.yoka.yokafurniture.controller;

import com.yoka.yokafurniture.payload.ColourDto;
import com.yoka.yokafurniture.payload.ColourResponse;
import com.yoka.yokafurniture.service.ColourService;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/")
public class ColourController {

    private ColourService colourService;

    public ColourController(ColourService colourService) {
        this.colourService = colourService;
    }

    @PostMapping("articles/{articleId}/colours")
    public ResponseEntity<ColourDto> createColour(@RequestBody ColourDto colourDto,
                                                  @PathVariable(name = "articleId") long articleId){

        return new ResponseEntity<>(colourService.createColour(colourDto,articleId), HttpStatus.CREATED);

    }

    @GetMapping("articles/{articleId}/colours")
    public List<ColourResponse> getColourByArticleId (@PathVariable (name = "articleId") long articleId,
                                                      @RequestHeader(name = "Accept-Language", required = false) Locale locale){
        return colourService.getColorsByArticleId(articleId, LocaleContextHolder.getLocale());
    }

    @PostMapping("articles/{articleId}/colours/{colourId}")
    public ResponseEntity<String> addColourToArticle(@PathVariable (name = "articleId") long articleId,
                                                     @PathVariable (name = "colourId") long colourId){
        colourService.addColourToArticle(colourId,articleId);
        return new ResponseEntity<>("Colour added successfully to article.",HttpStatus.OK);
    }

    @PutMapping("colours/{colourId}")
    public  ResponseEntity<ColourDto> updateColour(@RequestBody ColourDto colourDto,
                                                   @PathVariable (name = "colourId") long colourId){
        ColourDto colourDtoResponse = colourService.updateColour(colourDto, colourId);

        return new ResponseEntity<>(colourDtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("articles/{articleId}/colours/{colourId}")
    public ResponseEntity<String> deleteColour(@PathVariable (name = "articleId") long articleId,
                                               @PathVariable (name = "colourId") long colourId){
        colourService.deleteColour(colourId,articleId);
        return new ResponseEntity<>("Colour removed successfully from article.",HttpStatus.OK);
    }


}
