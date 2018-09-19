package com.irhad.restwebshop.Controllers;

import com.irhad.restwebshop.Domain.DTOs.CreateOrderItemDTO;
import com.irhad.restwebshop.Domain.DTOs.OrderDTO;
import com.irhad.restwebshop.Domain.DTOs.OrderItemDTO;
import com.irhad.restwebshop.Domain.Models.Order;
import com.irhad.restwebshop.Domain.Models.Shop;
import com.irhad.restwebshop.Domain.Models.User;
import com.irhad.restwebshop.Services.OrderItemService;
import com.irhad.restwebshop.Services.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/Order")
@Api(value="orders", description="Order resource...")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @ApiOperation(value = "Create new order", response = OrderDTO.class)
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public OrderDTO createOrder() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Order order = Order.builder().price(BigDecimal.ZERO)
                .shipped(false).userId(user).build();

        return new OrderDTO(orderService.createOrder(order));
    }
}
