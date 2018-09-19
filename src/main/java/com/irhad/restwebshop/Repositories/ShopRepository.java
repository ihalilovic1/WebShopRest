package com.irhad.restwebshop.Repositories;

import com.irhad.restwebshop.Domain.Models.Shop;
import com.irhad.restwebshop.Domain.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Set;
import java.util.UUID;

public interface ShopRepository extends PagingAndSortingRepository<Shop, UUID> {
    Set<Shop> findAllByOwner(User owner);
}
