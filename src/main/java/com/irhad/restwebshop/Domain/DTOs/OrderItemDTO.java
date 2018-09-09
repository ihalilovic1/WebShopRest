package com.irhad.restwebshop.Domain.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
    @JsonProperty("id")
    private UUID orderItemId;
    private ShopItemDTO shopItem;
    private OrderDTO order;
    private Date createdAt;
    private Date updatedAt;
    private BigDecimal price;
    private Integer amount;
}
