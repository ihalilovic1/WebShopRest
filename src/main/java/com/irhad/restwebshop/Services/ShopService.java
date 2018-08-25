package com.irhad.restwebshop.Services;

import com.irhad.restwebshop.Domain.DTOs.ShopDTO;
import com.irhad.restwebshop.Domain.Models.Shop;
import com.irhad.restwebshop.Domain.Models.User;

import java.util.Set;
import java.util.UUID;

public interface ShopService {
    Shop createShop(Shop shop);

    Shop createShop(String name, String description, String adress, User owner);

    Set<Shop> findByOwner(User user);

    Shop findById(UUID uuid);

    Shop updateShop(Shop shop);

    Boolean deleteShop(Shop shop);

}
