package com.irhad.restwebshop.Services;

import com.irhad.restwebshop.Domain.DTOs.ShopDTO;
import com.irhad.restwebshop.Domain.Models.Shop;
import com.irhad.restwebshop.Domain.Models.User;
import com.irhad.restwebshop.Repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    ShopRepository shopRepository;

    @Override
    public ShopDTO createShop(Shop shop) {
        return new ShopDTO(shopRepository.save(shop));
    }

    @Override
    public ShopDTO createShop(String name, String description, String adress, User owner) {
        Shop shop = Shop.builder()
                .name(name)
                .description(description)
                .adress(adress)
                .owner(owner)
                .enabled(false)
                .build();
        return this.createShop(shop);
    }

    @Override
    public Set<ShopDTO> findByOwner(User user) {
        return ShopDTO.getShopDTOSet(shopRepository.findAllByOwner(user));
    }

    @Override
    public ShopDTO findById(UUID uuid) {
        Shop shop = shopRepository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Shop not found"));
        return new ShopDTO(shop);
    }
}
