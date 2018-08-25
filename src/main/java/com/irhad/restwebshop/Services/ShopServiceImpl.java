package com.irhad.restwebshop.Services;

import com.irhad.restwebshop.Domain.Models.Shop;
import com.irhad.restwebshop.Domain.Models.User;
import com.irhad.restwebshop.Repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    ShopRepository shopRepository;

    @Override
    public Shop createShop(Shop shop) {
        return shopRepository.save(shop);
    }

    @Override
    public Shop createShop(String name, String description, String adress, User owner) {
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
    public Set<Shop> findByOwner(User user) {
        return shopRepository.findAllByOwner(user);
    }

    @Override
    public Shop findById(UUID uuid) {
        Shop shop = shopRepository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Shop not found"));
        return shop;
    }

    @Override
    public Shop updateShop(Shop shop) {
        return shopRepository.save(shop);
    }

    @Override
    public Boolean deleteShop(Shop shop) {
        shopRepository.delete(shop);
        return true;
    }
}
