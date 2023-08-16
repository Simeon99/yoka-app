package com.yoka.yokafurniture.controller;

import com.yoka.yokafurniture.payload.DeliveryAdress.DeliveryAddressDto;
import com.yoka.yokafurniture.service.DeliveryAddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/deliveryAddresses")
public class DeliveryAddressController {
    private DeliveryAddressService deliveryAddressService;

    public DeliveryAddressController(DeliveryAddressService deliveryAddressService) {
        this.deliveryAddressService = deliveryAddressService;
    }

    @PostMapping("/order/{orderId}")
    public ResponseEntity<DeliveryAddressDto> createDeliveryAddress(@RequestBody DeliveryAddressDto deliveryAddressDto, @PathVariable long orderId){
        return new ResponseEntity<>(deliveryAddressService.createDeliveryAddress(deliveryAddressDto, orderId), HttpStatus.CREATED);
    }
}
