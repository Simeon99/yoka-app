package com.yoka.yokafurniture.service;

import com.yoka.yokafurniture.payload.OrderItem.OrderItemDto;

public interface OrderItemService {
    public OrderItemDto createOrederItem(long articleId, int quantity, double width, double length, double height);
}
