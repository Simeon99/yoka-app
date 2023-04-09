package com.yoka.yokafurniture.payload.Description;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DescriptionResponse {
    private long id;
    private String description;
}
