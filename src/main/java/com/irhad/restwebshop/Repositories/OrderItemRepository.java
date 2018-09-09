package com.irhad.restwebshop.Repositories;

import com.irhad.restwebshop.Domain.Models.OrderItem;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OrderItemRepository extends CrudRepository<OrderItem, UUID> {
}
