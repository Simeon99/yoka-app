package com.yoka.yokafurniture.service;

import com.yoka.yokafurniture.payload.DeliveryAdress.DeliveryAddressDto;
import com.yoka.yokafurniture.payload.DeliveryContact.DeliveryContactDto;
import com.yoka.yokafurniture.payload.Order.OrderDto;
import com.yoka.yokafurniture.payload.Order.OrderItemToCreate;
import com.yoka.yokafurniture.payload.Order.OrderResponse;

import java.util.List;
import java.util.Map;

public interface OrderService {

    public OrderDto createOrder(List<OrderItemToCreate> orderItemToCreateList, DeliveryAddressDto deliveryAddressDto, DeliveryContactDto deliveryContactDto, boolean isSpecial);
    public void removeOrderItem(long orderId, long orderItemId);
    public OrderResponse getAllOrders(int pageNo, int pageSize, String sortBy, String sortDir);


}
