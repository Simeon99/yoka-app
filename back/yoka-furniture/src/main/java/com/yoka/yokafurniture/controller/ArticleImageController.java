package com.yoka.yokafurniture.controller;

import com.yoka.yokafurniture.payload.ArticleImage.ArticleImageDto;
import com.yoka.yokafurniture.service.ArticleImageService;
import com.yoka.yokafurniture.service.FileStoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ArticleImageController {

    private ArticleImageService articleImageService;
    private FileStoreService fileStoreService;

    public ArticleImageController(ArticleImageService articleImageService, FileStoreService fileStoreService) {
        this.articleImageService = articleImageService;
        this.fileStoreService = fileStoreService;
    }

    @PostMapping("articles/{articleId}/articleImage")
    public ResponseEntity<ArticleImageDto> createArticleImage( @PathVariable (value = "articleId") long articleId,
                                                               @RequestParam(name = "file") MultipartFile file){

        String fileName = fileStoreService.storeFile(file);

        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(fileName)
                .toUriString();

        ArticleImageDto articleImageDto = new ArticleImageDto();
        articleImageDto.setMediaLink(url);

        return new ResponseEntity<>(articleImageService.createArticleImage(articleImageDto,articleId), HttpStatus.CREATED);

    }

    @GetMapping("articles/{articleId}/articleImage")
    private List<ArticleImageDto> getAllArticleImages(@PathVariable (value = "articleId") long articleId){

        return articleImageService.getAllByArticleId(articleId);

    }

    @DeleteMapping("articles/{articleId}/articleImage/{imageId}")
    public ResponseEntity<String> deleteImage(@PathVariable (value = "articleId") long articleId,
                                              @PathVariable (value = "imageId") long imageId){
        articleImageService.deleteArticleImage(imageId,articleId);

        return ResponseEntity.ok("Image deleted successfully.");
    }


}
