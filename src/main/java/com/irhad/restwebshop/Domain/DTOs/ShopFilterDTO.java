package com.irhad.restwebshop.Domain.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ShopFilterDTO {
    private int pageSize = 20;
    private int page = 0;
}
