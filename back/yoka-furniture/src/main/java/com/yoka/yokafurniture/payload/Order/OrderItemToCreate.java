package com.yoka.yokafurniture.payload.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemToCreate {

    private long articleId;
    private int quantity;
    private double width;
    private double height;
    private double length;

}
