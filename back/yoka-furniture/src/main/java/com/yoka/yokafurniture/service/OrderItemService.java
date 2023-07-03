package com.yoka.yokafurniture.service;

import com.yoka.yokafurniture.entity.Order;
import com.yoka.yokafurniture.payload.OrderItemDto;

public interface OrderItemService {
    public OrderItemDto createOrederItem(long articleId, int quantity);
}
