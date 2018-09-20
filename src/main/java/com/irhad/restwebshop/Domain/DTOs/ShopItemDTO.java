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
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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
    private Set<CategoryDTO> categories;
    private Set<String> photos;

    
    public ShopItemDTO(ShopItem shopItem) {
        this(shopItem.getId(), shopItem.getName(), shopItem.getDescription(), shopItem.getPrice(), shopItem.getCount(),
                shopItem.getCreatedAt(), shopItem.getUpdatedAt(), shopItem.getEnabled(),
                new ShopDTO(shopItem.getShop()), CategoryDTO.getCategoryDTOSet(shopItem.getCategories()),
                shopItem.getPhotos().stream().map(p -> p.getPath()).collect(Collectors.toSet()));
    }

    public static Set<ShopItemDTO> getShopItemDTOSet(Set<ShopItem> shopItems) {
        Set<ShopItemDTO> itemDTOS = new HashSet<>();
        for (ShopItem item : shopItems) {
            itemDTOS.add(new ShopItemDTO(item));
        }

        return itemDTOS;
    }
}
