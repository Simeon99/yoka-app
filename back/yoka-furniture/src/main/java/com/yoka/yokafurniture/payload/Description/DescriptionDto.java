package com.yoka.yokafurniture.payload.Description;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DescriptionDto {
    private long id;
    private String description;
    private String descriptionSr;
}
