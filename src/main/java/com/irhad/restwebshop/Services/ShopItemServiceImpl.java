package com.irhad.restwebshop.Services;

import com.irhad.restwebshop.Domain.Models.Shop;
import com.irhad.restwebshop.Domain.Models.ShopItem;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Service
public class ShopItemServiceImpl implements ShopItemService {
    @Override
    public ShopItem createItem(ShopItem shopItem) {
        return null;
    }

    @Override
    public ShopItem createItem(String name, String description, Shop shop, BigDecimal price, Integer count, Boolean enabled) {
        return null;
    }

    @Override
    public Set<ShopItem> findByShop(Shop shop) {
        return null;
    }

    @Override
    public ShopItem findById(UUID uuid) {
        return null;
    }

    @Override
    public ShopItem updateShopItem(ShopItem shopItem) {
        return null;
    }

    @Override
    public Boolean deleteShopItem(ShopItem shopItem) {
        return null;
    }
}
