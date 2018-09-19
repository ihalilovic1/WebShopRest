package com.irhad.restwebshop.Repositories;

import com.irhad.restwebshop.Domain.Models.Order;
import com.irhad.restwebshop.Domain.Models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OrderRepository extends CrudRepository<Order, UUID> {
    Order findByUserId(User user);
}
