package com.irhad.restwebshop.Repositories;

import com.irhad.restwebshop.Domain.Models.Shop;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ShopRepository extends CrudRepository<Shop, UUID> {
}
