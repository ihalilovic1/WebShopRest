package com.irhad.restwebshop.Domain.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.irhad.restwebshop.Domain.Models.Shop;
import com.irhad.restwebshop.Domain.Models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.hateoas.ResourceSupport;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopDTO extends ResourceSupport {
    @JsonProperty("id")
    private UUID shopId;
    private String name;
    private String description;
    private String adress;
    private UserBasicDTO owner;
    private Date createdAt;
    private Date updatedAt;
    private Boolean enabled;

    public ShopDTO(Shop shop){
        this(shop.getId(), shop.getName(), shop.getDescription(), shop.getAdress(), new UserBasicDTO(shop.getOwner()),
                shop.getCreatedAt(), shop.getUpdatedAt(), shop.getEnabled());
    }

    public static Set<ShopDTO> getShopDTOSet(Set<Shop> shopSet) {
        Set<ShopDTO> shopDTOS = new HashSet<>();
        for (Shop shop : shopSet) {
            shopDTOS.add(new ShopDTO(shop));
        }
        return shopDTOS;
    }

    public Shop createShopObject() {
        return Shop.builder().enabled(enabled).owner(User.builder().id(owner.getId()).build())
                .adress(adress).id(shopId).name(name).createdAt(createdAt).description(description)
                .updatedAt(updatedAt).build();
    }
}
