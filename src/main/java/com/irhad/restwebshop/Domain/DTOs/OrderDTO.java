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
public class OrderDTO {
    @JsonProperty("id")
    private UUID orderId;
    private UserDTO user;
    private Date createdAt;
    private Date updatedAt;
    private Boolean shipped;
    private BigDecimal price;

}
