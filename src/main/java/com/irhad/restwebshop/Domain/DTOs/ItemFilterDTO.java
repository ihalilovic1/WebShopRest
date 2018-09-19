package com.irhad.restwebshop.Domain.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class ItemFilterDTO {
    private int pageSize = 20;
    private int page = 0;
    private String name = "";
    private BigDecimal maxPrice = BigDecimal.ZERO;
    private BigDecimal minPrice = BigDecimal.ZERO;
}
