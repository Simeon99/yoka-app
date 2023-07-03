package com.yoka.yokafurniture.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private long id;
    private Date date;
    private double totalPrice;

    private Set<OrderItemDto> orderItems = new HashSet<>();
    private DeliveryAddressDto deliveryAddressDto;


}
