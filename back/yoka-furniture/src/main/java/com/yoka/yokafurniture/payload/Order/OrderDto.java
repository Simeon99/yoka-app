package com.yoka.yokafurniture.payload.Order;

import com.yoka.yokafurniture.entity.DeliveryContact;
import com.yoka.yokafurniture.payload.DeliveryAdress.DeliveryAddressDto;
import com.yoka.yokafurniture.payload.DeliveryContact.DeliveryContactDto;
import com.yoka.yokafurniture.payload.OrderItem.OrderItemDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
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
    private DeliveryContactDto deliveryContactDto;


}
