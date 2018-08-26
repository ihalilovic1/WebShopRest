package com.irhad.restwebshop.Repositories;

import com.irhad.restwebshop.Domain.Models.Shop;
import com.irhad.restwebshop.Domain.Models.ShopItem;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;
import java.util.UUID;

public interface ShopItemRepository extends CrudRepository<ShopItem, UUID> {
    Set<ShopItem> findAllByShop(Shop shop);
}
