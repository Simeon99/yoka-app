package com.yoka.yokafurniture.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateDto {
    Map<Long, Integer> articlesMap;
    DeliveryAddressDto deliveryAddressDto;
}
