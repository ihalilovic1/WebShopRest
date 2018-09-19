package com.irhad.restwebshop.Domain.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ShopFilterDTO {
    private int pageSize;
    private int page;
}
