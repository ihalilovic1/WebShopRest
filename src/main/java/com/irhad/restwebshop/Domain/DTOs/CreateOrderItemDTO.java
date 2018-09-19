package com.irhad.restwebshop.Domain.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class CreateOrderItemDTO {
    private UUID shopitem;
    private Integer amount;
}
