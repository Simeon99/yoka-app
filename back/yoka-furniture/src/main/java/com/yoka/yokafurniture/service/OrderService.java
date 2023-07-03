package com.yoka.yokafurniture.service;

import com.yoka.yokafurniture.payload.DeliveryAddressDto;
import com.yoka.yokafurniture.payload.OrderDto;
import com.yoka.yokafurniture.payload.OrderResponse;

import java.util.List;
import java.util.Map;

public interface OrderService {

    public OrderDto createOrder(Map<Long, Integer> articlesMap, DeliveryAddressDto deliveryAddressDto);
    public void removeOrderItem(long orderId, long orderItemId);
    public OrderResponse getAllOrders(int pageNo, int pageSize, String sortBy, String sortDir);


}
