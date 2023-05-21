package com.yoka.yokafurniture.controller;

import com.yoka.yokafurniture.payload.ArticleDto;
import com.yoka.yokafurniture.payload.ArticleResponse;
import com.yoka.yokafurniture.service.ArticleService;
import com.yoka.yokafurniture.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/category/{categoryId}")
    public ResponseEntity<ArticleDto> createArticle(@RequestBody ArticleDto articleDto,
                                                    @PathVariable long categoryId){
        return new ResponseEntity<>(articleService.createArticle(articleDto, categoryId), HttpStatus.CREATED);
    }

    @GetMapping
    public ArticleResponse getAllArticles(
            @RequestParam(name = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NO,required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY,required = false)String sortBy,
            @RequestParam(name = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR,required = false)String sortDir){
        return articleService.getAllArticles(pageNo, pageSize, sortBy, sortDir);
    }

    @GetMapping("/category/{categoryId}")
    public List<ArticleDto> getAllArticlesByArticleId(@PathVariable long categoryId){
        return articleService.getAllArticlesByCategoryId(categoryId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDto> getArticleById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(articleService.getArticleById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleDto> updateArticle(@RequestBody ArticleDto articleDto, @PathVariable(name = "id") long id){
        return new ResponseEntity<>(articleService.upadteArticle(articleDto,id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable(name = "id") long id){
        articleService.deleteArticle(id);
        return new ResponseEntity<>("Article successfully deleted.", HttpStatus.OK);
    }
}
