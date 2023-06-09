package com.yoka.yokafurniture.controller;

import com.yoka.yokafurniture.payload.Description.DescriptionDto;
import com.yoka.yokafurniture.payload.Description.DescriptionResponse;
import com.yoka.yokafurniture.service.DescriptionService;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/")
public class DescriptionController {
    private DescriptionService descriptionService;

    public DescriptionController(DescriptionService descriptionService) {
        this.descriptionService = descriptionService;
    }
    @PostMapping("articles/{articleId}/descriptions")
    public ResponseEntity<DescriptionDto> createDescription(@RequestBody DescriptionDto descriptionDto,
                                                            @PathVariable(name = "articleId") long articleId){

        return new ResponseEntity<>(descriptionService.createDescription(descriptionDto, articleId), HttpStatus.CREATED);

    }

    @CrossOrigin
    @GetMapping("articles/{articleId}/descriptions")
    public List<DescriptionResponse> getDescriptionsByArticleId(@PathVariable (name = "articleId") long articleId,
                                                                @RequestHeader(name = "Accept-Language", required = false) String localeString){
        Locale locale = new Locale(localeString);
        return descriptionService.getAllDescriptionsByArticleId(articleId, LocaleContextHolder.getLocale());
    }

    @GetMapping("articles/{articleId}/descriptions/{descriptionId}")
    public ResponseEntity<DescriptionResponse> getDescriptionById(@PathVariable (name = "articleId") long articleId,
                                                                   @PathVariable (name = "descriptionId") long descriptionId,
                                                                   @RequestHeader(name = "Accept-Language", required = false) Locale locale) {

        DescriptionResponse descriptionResponse = descriptionService.getDescriptionById(articleId,descriptionId,LocaleContextHolder.getLocale());

        return ResponseEntity.ok(descriptionResponse);
    }

    @PostMapping("articles/{articleId}/descriptions/{descriptionId}")
    public ResponseEntity<DescriptionDto> updateDescription(@PathVariable (name = "articleId") long articleId,
                                                            @PathVariable (name = "descriptionId") long descriptionId,
                                                            @RequestBody DescriptionDto descriptionDto){
        DescriptionDto descriptionResponse = descriptionService.updateDescription(articleId,descriptionId,descriptionDto);

        return ResponseEntity.ok(descriptionResponse);
    }

    @DeleteMapping("articles/{articleId}/descriptions/{descriptionId}")
    public ResponseEntity<String> deleteDescription(@PathVariable (name = "articleId") long articleId,
                                                    @PathVariable (name = "descriptionId") long descriptionId){
        descriptionService.deleteDescription(articleId, descriptionId);
        return new ResponseEntity<>("Description removed successfully from article.",HttpStatus.OK);
    }

}
