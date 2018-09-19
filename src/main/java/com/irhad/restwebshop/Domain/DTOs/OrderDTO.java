package com.irhad.restwebshop.Domain.DTOs;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.irhad.restwebshop.Domain.Models.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    @JsonProperty("id")
    private UUID orderId;
    private UserBasicDTO user;
    private Date createdAt;
    private Date updatedAt;
    private Boolean shipped;
    private BigDecimal price;

    public OrderDTO(Order order) {
        this(order.getId(), new UserBasicDTO(order.getUserId()), order.getCreatedAt(), order.getUpdatedAt(), order.getShipped(),
                order.getPrice());
    }
}
