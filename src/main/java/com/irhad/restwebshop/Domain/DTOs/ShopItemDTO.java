package com.irhad.restwebshop.Domain.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.irhad.restwebshop.Domain.Models.ShopItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopItemDTO extends ResourceSupport {
    @JsonProperty("id")
    private UUID shopItemId;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer count;
    private Date createdAt;
    private Date updatedAt;
    private Boolean enabled;
    private ShopDTO shop;

    public ShopItemDTO(ShopItem shopItem) {
        this(shopItem.getId(), shopItem.getName(), shopItem.getDescription(), shopItem.getPrice(), shopItem.getCount(),
                shopItem.getCreatedAt(), shopItem.getUpdatedAt(), shopItem.getEnabled(), new ShopDTO(shopItem.getShop()));
    }
}
