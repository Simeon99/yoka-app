package com.yoka.yokafurniture.payload.Dimension;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DimensionResponse {
    private List<Double> widths;
    private List<Double> lengths;
    private List<Double> heights;
}
