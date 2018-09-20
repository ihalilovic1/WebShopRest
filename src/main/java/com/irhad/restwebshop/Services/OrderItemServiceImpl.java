package com.irhad.restwebshop.Services;

import com.irhad.restwebshop.Domain.Models.OrderItem;
import com.irhad.restwebshop.Repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Override
    public OrderItem createOrderItem(OrderItem item) {
        return orderItemRepository.save(item);
    }
}
