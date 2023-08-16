package com.yoka.yokafurniture.payload.OrderItem;

import com.yoka.yokafurniture.payload.Article.ArticleDto;
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
