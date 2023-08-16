package com.yoka.yokafurniture.payload.Colour;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ColourResponse {
    private long id;
    private String name;
    private String mediaLink;
}
