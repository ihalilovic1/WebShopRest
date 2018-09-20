package com.irhad.restwebshop.Domain.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.irhad.restwebshop.Domain.Models.Shop;
import com.irhad.restwebshop.Domain.Models.User;
import lombok.*;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.hateoas.ResourceSupport;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@ToString
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

    public ShopDTO(UUID shopId, String name, String description, String adress, UserBasicDTO owner, Date createdAt, Date updatedAt, Boolean enabled) {
        this.shopId = shopId;
        this.name = name;
        this.description = description;
        this.adress = adress;
        this.owner = owner;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.enabled = enabled;
    }

    public ShopDTO(Shop shop){
        this(shop.getId(), shop.getName(), shop.getDescription(), shop.getAdress(), new UserBasicDTO(shop.getOwner()),
                shop.getCreatedAt(), shop.getUpdatedAt(), shop.getEnabled());
    }

    public static Set<ShopDTO> getShopDTOSet(Set<Shop> shopSet) {
        try {
        Set<ShopDTO> shopDTOS =  shopSet.stream().map(
                s -> new ShopDTO(s)).collect(Collectors.toSet());
        
        return shopDTOS;
        } catch (Exception e) {
            return null;
        }
    }

    public Shop createShopObject() {
        return Shop.builder().enabled(enabled).owner(User.builder().id(owner.getId()).build())
                .adress(adress).id(shopId).name(name).createdAt(createdAt).description(description)
                .updatedAt(updatedAt).build();
    }
}
