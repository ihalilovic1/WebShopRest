package com.irhad.restwebshop.Controllers;

import com.irhad.restwebshop.Services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Shop")
public class ShopController {
    @Autowired
    ShopService shopService;

    
}
