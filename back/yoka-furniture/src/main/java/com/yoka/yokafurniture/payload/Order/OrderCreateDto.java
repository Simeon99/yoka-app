package com.yoka.yokafurniture.payload.Order;

import com.yoka.yokafurniture.payload.DeliveryAdress.DeliveryAddressDto;
import com.yoka.yokafurniture.payload.DeliveryContact.DeliveryContactDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateDto {
    private List<OrderItemToCreate> orderItemToCreateList;
    private DeliveryAddressDto deliveryAddressDto;
    private DeliveryContactDto deliveryContactDto;
    private String isSpecial;

}
