package com.yoka.yokafurniture.controller;

import com.yoka.yokafurniture.payload.Colour.ColourDto;
import com.yoka.yokafurniture.payload.Colour.ColourResponse;
import com.yoka.yokafurniture.service.ColourService;
import com.yoka.yokafurniture.service.FileStoreService;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/")
public class ColourController {

    private ColourService colourService;
    private FileStoreService fileStoreService;

    public ColourController(ColourService colourService, FileStoreService fileStoreService) {
        this.colourService = colourService;
        this.fileStoreService = fileStoreService;
    }

    @PostMapping("articles/{articleId}/colours")
    public ResponseEntity<ColourDto> createColour(@PathVariable(name = "articleId") long articleId,
                                                  @RequestParam(name = "name")String name,
                                                  @RequestParam(name = "nameSr")String nameSr,
                                                  @RequestParam(name = "file") MultipartFile file){

        String fileName = fileStoreService.storeFile(file);

        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(fileName)
                .toUriString();

        ColourDto colourDto = new ColourDto();
        colourDto.setName(name);
        colourDto.setNameSr(nameSr);
        colourDto.setMediaLink(url);

        return new ResponseEntity<>(colourService.createColour(colourDto,articleId), HttpStatus.CREATED);

    }

    @CrossOrigin
    @GetMapping("articles/{articleId}/colours")
    public List<ColourResponse> getColourByArticleId (@PathVariable (name = "articleId") long articleId,
                                                      @RequestHeader(name = "Accept-Language", required = false) Locale locale){
        return colourService.getColorsByArticleId(articleId, LocaleContextHolder.getLocale());
    }

    @CrossOrigin
    @GetMapping("colours/{colourId}")
    public ColourResponse getColourById (@PathVariable (name = "colourId") long colourId,
                                                      @RequestHeader(name = "Accept-Language", required = false) Locale locale){
        return colourService.getColourById(colourId, LocaleContextHolder.getLocale());
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
