package com.yoka.yokafurniture.payload.DeliveryAdress;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAddressDto {
    private long id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String country;
}
