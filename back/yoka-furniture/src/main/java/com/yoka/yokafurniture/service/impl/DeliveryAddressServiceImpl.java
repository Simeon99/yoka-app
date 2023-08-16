package com.yoka.yokafurniture.service.impl;

import com.yoka.yokafurniture.entity.DeliveryAddress;
import com.yoka.yokafurniture.entity.Order;
import com.yoka.yokafurniture.exception.ResourceNotFoundException;
import com.yoka.yokafurniture.payload.DeliveryAdress.DeliveryAddressDto;
import com.yoka.yokafurniture.repository.DeliveryAddressRepository;
import com.yoka.yokafurniture.repository.OrderRepository;
import com.yoka.yokafurniture.service.DeliveryAddressService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DeliveryAddressServiceImpl implements DeliveryAddressService {

    private DeliveryAddressRepository deliveryAddressRepository;
    private OrderRepository orderRepository;
    private ModelMapper modelMapper;

    public DeliveryAddressServiceImpl(DeliveryAddressRepository deliveryAddressRepository, OrderRepository orderRepository, ModelMapper modelMapper) {
        this.deliveryAddressRepository = deliveryAddressRepository;
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DeliveryAddressDto createDeliveryAddress(DeliveryAddressDto deliveryAddressDto, long orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));

        DeliveryAddress deliveryAddress = mapToDeliveryAddress(deliveryAddressDto);

        deliveryAddress.setOrder(order);


        DeliveryAddress deliveryAddressResponse = deliveryAddressRepository.save(deliveryAddress);

        return mapToDto(deliveryAddressResponse);
    }

    private DeliveryAddress mapToDeliveryAddress(DeliveryAddressDto deliveryAddressDto){
        DeliveryAddress deliveryAddress = modelMapper.map(deliveryAddressDto, DeliveryAddress.class);
        return deliveryAddress;
    }
    private DeliveryAddressDto mapToDto(DeliveryAddress deliveryAddress){
        DeliveryAddressDto deliveryAddressDto = modelMapper.map(deliveryAddress, DeliveryAddressDto.class);
        return deliveryAddressDto;
    }
}
