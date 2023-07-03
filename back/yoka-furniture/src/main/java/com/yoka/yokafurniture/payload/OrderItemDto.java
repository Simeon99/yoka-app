package com.yoka.yokafurniture.payload;

import com.yoka.yokafurniture.entity.Article;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderItemDto {
    private long id;
    private int quantity;
    private double price;
    private ArticleDto articleDto;
//    private OrderDto orderDto;



}
