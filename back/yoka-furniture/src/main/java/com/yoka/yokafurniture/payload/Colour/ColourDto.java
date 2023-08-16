package com.yoka.yokafurniture.payload.Colour;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColourDto {
    private long id;
    private String name;
    private String nameSr;
    private String mediaLink;
}
