package com.irhad.restwebshop.Services;

import com.irhad.restwebshop.Domain.DTOs.ShopDTO;
import com.irhad.restwebshop.Domain.Models.Shop;
import com.irhad.restwebshop.Domain.Models.User;

import java.util.Set;
import java.util.UUID;

public interface ShopService {
    ShopDTO createShop(Shop shop);

    ShopDTO createShop(String name, String description, String adress, User owner);

    Set<ShopDTO> findByOwner(User user);

    ShopDTO findById(UUID uuid);

}
