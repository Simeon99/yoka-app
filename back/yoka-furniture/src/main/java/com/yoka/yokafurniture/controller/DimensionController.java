package com.yoka.yokafurniture.controller;

import com.yoka.yokafurniture.payload.Dimension.DimensionDto;
import com.yoka.yokafurniture.payload.Dimension.DimensionResponse;
import com.yoka.yokafurniture.service.DimensionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class DimensionController {

    private DimensionService dimensionService;

    public DimensionController(DimensionService dimensionService) {
        this.dimensionService = dimensionService;
    }

    @PostMapping("articles/{articleId}/dimensions")
    public ResponseEntity<DimensionDto> createDimension(@RequestBody DimensionDto dimensionDto,
                                                        @PathVariable (name = "articleId") long articleId){
        return new ResponseEntity<>(dimensionService.createDimension(dimensionDto,articleId), HttpStatus.CREATED);
    }

    @CrossOrigin
    @GetMapping("articles/{articleId}/dimensions")
    public DimensionResponse getAllDimensionByArticleId(@PathVariable (name = "articleId") long articleId){

        return dimensionService.getAllDimensionsByArticleId(articleId);

    }

    @PostMapping("articles/{articleId}/dimensions/{dimensionId}")
    public ResponseEntity<String> addDimensionToArticle(@PathVariable (name = "articleId") long articleId,
                                                        @PathVariable (name = "dimensionId") long dimensionId){
        dimensionService.addDimensionToArticle(dimensionId, articleId);
        return new ResponseEntity<>("Dimension successfully added to article.", HttpStatus.OK);

    }

    @DeleteMapping("articles/{articleId}/dimensions/{dimensionId}")
    public ResponseEntity<String> deleteDimension(@PathVariable (name = "articleId") long articleId,
                                                  @PathVariable (name = "dimensionId") long dimensionId){
        dimensionService.deleteDimension(dimensionId,articleId);
        return new ResponseEntity<>("Dimension removed successfully from article.", HttpStatus.OK);

    }

    @PutMapping("dimensions/{dimensionId}")
    public ResponseEntity<DimensionDto> updateDimension(@PathVariable (name = "dimensionId") long dimensionId,
                                                        @RequestBody DimensionDto dimensionDto){
        return  new ResponseEntity<>(dimensionService.updateDimension(dimensionDto,dimensionId), HttpStatus.OK);
    }


}
