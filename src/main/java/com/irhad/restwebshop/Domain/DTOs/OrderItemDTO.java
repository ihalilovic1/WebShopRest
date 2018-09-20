package com.irhad.restwebshop.Domain.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.irhad.restwebshop.Domain.Models.OrderItem;
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

    public OrderItemDTO(OrderItem orderItem) {
        this(orderItem.getId(), null, new OrderDTO(orderItem.getOrderId()),
                orderItem.getCreatedAt(), null, orderItem.getPrice(), orderItem.getAmount());
    }

}
