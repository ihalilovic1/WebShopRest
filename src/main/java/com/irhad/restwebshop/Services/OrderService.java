package com.irhad.restwebshop.Services;

import com.irhad.restwebshop.Domain.Models.Order;
import com.irhad.restwebshop.Domain.Models.OrderItem;
import com.irhad.restwebshop.Domain.Models.ShopItem;
import com.irhad.restwebshop.Domain.Models.User;

import java.util.Set;
import java.util.UUID;

public interface OrderService {
    Order createOrder(Order order);

    Order findOrder(UUID id);

    Order findByUser(User user);

    Boolean confirmOrder(UUID id);

    Order addItem(UUID orderId, OrderItem item);

    Order addMultipleItems(UUID orderId, Set<OrderItem> items);
}
