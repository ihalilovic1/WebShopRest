package com.irhad.restwebshop.Services;

import com.google.common.collect.Sets;
import com.irhad.restwebshop.Domain.DTOs.ItemFilterDTO;
import com.irhad.restwebshop.Domain.Models.FileResource;
import com.irhad.restwebshop.Domain.Models.ItemCategory;
import com.irhad.restwebshop.Domain.Models.Shop;
import com.irhad.restwebshop.Domain.Models.ShopItem;
import com.irhad.restwebshop.Repositories.ShopItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Service
public class ShopItemServiceImpl implements ShopItemService {
    @Autowired
    ShopItemRepository shopItemRepository;

    @Override
    public ShopItem createItem(ShopItem shopItem) {
        return shopItemRepository.save(shopItem);
    }

    @Override
    public ShopItem createItem(String name, String description, Shop shop, BigDecimal price, Integer count,
                               Boolean enabled, Set<ItemCategory> categories, Set<FileResource> photos) {
        ShopItem shopItem = ShopItem.builder().name(name).description(description).price(price).count(count)
                .enabled(enabled).shop(shop).photos(photos).categories(categories).build();
        return createItem(shopItem);
    }

    @Override
    public Set<ShopItem> findByShop(Shop shop) {
        return shopItemRepository.findAllByShop(shop);
    }

    @Override
    public ShopItem findById(UUID uuid) {
        return shopItemRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("Shop item not found"));
    }

    @Override
    public Set<ShopItem> findAll(final ItemFilterDTO filter) {
        if(filter == null)
            throw new IllegalArgumentException("Filters not valid");
        Set<ShopItem> set = Sets.newHashSet(shopItemRepository.findAll());
        set.stream().filter(i -> (i.getName().contains(filter.getName())
                || i.getDescription().contains(filter.getName()))
                && !filter.getMaxPrice().equals(0)? true : filter.getMaxPrice().compareTo(i.getPrice()) >= 0
        );

        return set;
    }

    @Override
    public ShopItem updateShopItem(ShopItem shopItem) {
        return shopItemRepository.save(shopItem);
    }

    @Override
    public Boolean deleteShopItem(ShopItem shopItem) {
        try {
            shopItemRepository.delete(shopItem);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
