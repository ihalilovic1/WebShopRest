package com.irhad.restwebshop.Services;

import com.irhad.restwebshop.Domain.Models.Order;
import com.irhad.restwebshop.Domain.Models.OrderItem;
import com.irhad.restwebshop.Domain.Models.ShopItem;
import com.irhad.restwebshop.Domain.Models.User;
import com.irhad.restwebshop.Repositories.OrderItemRepository;
import com.irhad.restwebshop.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;


    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order findOrder(UUID id) {
        return null;
    }

    @Override
    public Order findByUser(User user) {
        return orderRepository.findByUserId(user);
    }

    @Override
    public Boolean confirmOrder(UUID id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        order.setConfirmed(true);
        orderRepository.save(order);
        return true;
    }

    @Override
    public Order addItem(UUID orderId, OrderItem item) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        item.setOrderId(order);

        orderItemRepository.save(item);
        return order;
    }

    @Override
    public Order addMultipleItems(UUID orderId, Set<OrderItem> items) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        return null;
    }
}
