package com.irhad.restwebshop.Services;

import com.irhad.restwebshop.Domain.Models.FileResource;
import com.irhad.restwebshop.Domain.Models.ItemCategory;
import com.irhad.restwebshop.Domain.Models.Shop;
import com.irhad.restwebshop.Domain.Models.ShopItem;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

public interface ShopItemService {
    ShopItem createItem(ShopItem shopItem);

    ShopItem createItem(String name, String description, Shop shop, BigDecimal price, Integer count, Boolean enabled,
                        Set<ItemCategory> categories, Set<FileResource> photos);

    Set<ShopItem> findByShop(Shop shop);

    ShopItem findById(UUID uuid);

    ShopItem updateShopItem(ShopItem shopItem);

    Boolean deleteShopItem(ShopItem shopItem);
}
