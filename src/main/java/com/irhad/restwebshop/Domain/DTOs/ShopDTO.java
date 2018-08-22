package com.irhad.restwebshop.Domain.DTOs;

import com.irhad.restwebshop.Domain.Models.Shop;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopDTO {
    private UUID id;
    private String name;
    private String description;
    private String adress;
    private UserDTO owner;
    private Date createdAt;
    private Date updatedAt;
    private Boolean enabled;

    public ShopDTO(Shop shop){
        this(shop.getId(), shop.getName(), shop.getDescription(), shop.getAdress(), new UserDTO(shop.getOwner()),
                shop.getCreatedAt(), shop.getUpdatedAt(), shop.getEnabled());
    }

    public static Set<ShopDTO> getShopDTOSet(Set<Shop> shopSet) {
        Set<ShopDTO> shopDTOS = new HashSet<>();
        for (Shop shop : shopSet) {
            shopDTOS.add(new ShopDTO(shop));
        }
        return shopDTOS;
    }
}
