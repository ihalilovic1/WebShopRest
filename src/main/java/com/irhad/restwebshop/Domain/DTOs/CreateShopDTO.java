package com.irhad.restwebshop.Domain.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateShopDTO {
    private String name;
    private String description;
    private String adress;
}
