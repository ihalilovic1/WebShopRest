package com.irhad.restwebshop.Services;

import com.google.common.collect.Sets;
import com.irhad.restwebshop.Domain.DTOs.ShopDTO;
import com.irhad.restwebshop.Domain.DTOs.ShopFilterDTO;
import com.irhad.restwebshop.Domain.Models.Shop;
import com.irhad.restwebshop.Domain.Models.User;
import com.irhad.restwebshop.Repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
    public Set<Shop> findAll(ShopFilterDTO filters) {
        return Sets.newHashSet(shopRepository.findAll(new PageRequest(filters.getPage(), filters.getPageSize())));
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
