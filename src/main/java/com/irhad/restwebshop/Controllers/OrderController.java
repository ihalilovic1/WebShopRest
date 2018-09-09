package com.irhad.restwebshop.Controllers;

import com.irhad.restwebshop.Domain.DTOs.OrderDTO;
import com.irhad.restwebshop.Domain.DTOs.OrderItemDTO;
import com.irhad.restwebshop.Domain.Models.User;
import com.irhad.restwebshop.Services.OrderItemService;
import com.irhad.restwebshop.Services.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/Order")
@Api(value="orders", description="Order resource...")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @ApiOperation(value = "Create new order", response = OrderDTO.class)
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public OrderDTO createOrder(@RequestBody @Valid Set<OrderItemDTO> model) {
        return null;
    }
}
