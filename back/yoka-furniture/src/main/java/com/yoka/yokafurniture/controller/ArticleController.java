package com.yoka.yokafurniture.controller;

import com.yoka.yokafurniture.payload.ArticleDto;
import com.yoka.yokafurniture.payload.ArticleImageDto;
import com.yoka.yokafurniture.payload.ArticleResponse;
import com.yoka.yokafurniture.service.ArticleImageService;
import com.yoka.yokafurniture.service.ArticleService;
import com.yoka.yokafurniture.service.FileStoreService;
import com.yoka.yokafurniture.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private ArticleService articleService;
    private FileStoreService fileStoreService;

    private ArticleImageService articleImageService;

    public ArticleController(ArticleService articleService, FileStoreService fileStoreService, ArticleImageService articleImageService) {
        this.articleService = articleService;
        this.fileStoreService = fileStoreService;
        this.articleImageService = articleImageService;
    }

    @PostMapping("/category/{categoryId}")
    public ResponseEntity<ArticleDto> createArticle(@PathVariable long categoryId,
                                                    @RequestParam("name") String name,
                                                    @RequestParam("price") double price,
                                                    @RequestParam("discount") double discount,
                                                    @RequestParam("multipleFiles") MultipartFile[] files){

        ArticleDto articleDto = new ArticleDto();

        articleDto.setName(name);
        articleDto.setPrice(price);
        articleDto.setDiscount(discount);

        ArticleDto articleResponse  =  articleService.createArticle(articleDto, categoryId);


        for(int i  = 0 ; i < files.length ; i++){
            ArticleImageDto articleImageDto = new ArticleImageDto();
            String fileName = fileStoreService.storeFile(files[i]);

            String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/download/")
                    .path(fileName)
                    .toUriString();

            articleImageDto.setMediaLink(url);


            ArticleImageDto articleImageResponse = articleImageService.createArticleImage(articleImageDto,articleResponse.getId());
            articleResponse.getArticleImages().add(articleImageResponse);

        }


        return new ResponseEntity<>(articleResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ArticleResponse getAllArticles(
            @RequestParam(name = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NO,required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY,required = false)String sortBy,
            @RequestParam(name = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR,required = false)String sortDir){
        return articleService.getAllArticles(pageNo, pageSize, sortBy, sortDir);
    }

    @CrossOrigin
    @GetMapping("/category/{categoryId}")
    public List<ArticleDto> getAllArticlesByArticleId(@PathVariable long categoryId){
        return articleService.getAllArticlesByCategoryId(categoryId);
    }

    @CrossOrigin
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
