package com.yoka.yokafurniture.payload;

import com.yoka.yokafurniture.payload.Description.DescriptionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {
    private long id;
    private String name;
    private String bodyInside;
    private String bodyOutside;
    private String baseAndLegs;
    private String systemMechanism;
    private double price;
    private double discount;
    private Set<DescriptionDto> descriptions;
    private Set<ColourDto> colours;
    private Set<DimensionDto> dimensions;
}
