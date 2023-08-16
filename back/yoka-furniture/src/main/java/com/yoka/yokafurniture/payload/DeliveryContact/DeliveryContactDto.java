package com.yoka.yokafurniture.payload.DeliveryContact;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryContactDto {

    private long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String companyName;
    private long pib;
    private long identificationNumber;

}
