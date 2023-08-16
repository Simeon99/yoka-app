package com.yoka.yokafurniture.service;

import com.yoka.yokafurniture.payload.DeliveryAdress.DeliveryAddressDto;


public interface DeliveryAddressService {
    public DeliveryAddressDto createDeliveryAddress(DeliveryAddressDto deliveryAddressDto, long orderId);
}
