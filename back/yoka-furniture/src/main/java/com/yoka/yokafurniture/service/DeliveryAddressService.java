package com.yoka.yokafurniture.service;

import com.yoka.yokafurniture.payload.DeliveryAddressDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DeliveryAddressService {
    public DeliveryAddressDto createDeliveryAddress(DeliveryAddressDto deliveryAddressDto, long orderId);
}
