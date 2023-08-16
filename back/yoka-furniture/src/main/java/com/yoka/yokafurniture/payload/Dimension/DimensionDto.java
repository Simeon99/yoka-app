package com.yoka.yokafurniture.payload.Dimension;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DimensionDto {
    private long id;
    private double dimension;
    private String type;
}
