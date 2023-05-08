package com.yoka.yokafurniture.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleImageDto {
    private long id;
    private String name;
    private String mediaLink;
}
