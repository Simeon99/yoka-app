package com.yoka.yokafurniture.controller;

import com.yoka.yokafurniture.payload.ArticlePrice.ArticlePriceDto;
import com.yoka.yokafurniture.payload.ArticlePrice.ArticlePriceFIndByDimensions;
import com.yoka.yokafurniture.service.ArticlePriceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/articlePrice")
public class ArticlePriceController {

    private ArticlePriceService articlePriceService;

    public ArticlePriceController(ArticlePriceService articlePriceService) {
        this.articlePriceService = articlePriceService;
    }

    @PostMapping("/article/{articleId}")
    public ResponseEntity<ArticlePriceDto> createArticlePrice(@RequestBody ArticlePriceDto articlePriceDto, @PathVariable long articleId){
        return new ResponseEntity<>(articlePriceService.createArticlePrice(articlePriceDto, articleId), HttpStatus.CREATED);
    }

    @CrossOrigin
    @GetMapping("/price/article/{articleId}/dimensions/{width}/{height}/{length}")
    public ResponseEntity<ArticlePriceDto> getByDimensions(@PathVariable long articleId, @PathVariable double width,@PathVariable double height, @PathVariable double length){
        return new ResponseEntity<>(articlePriceService.findByDimensions(width,height,length, articleId), HttpStatus.OK);
    }

}
