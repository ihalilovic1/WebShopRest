package com.irhad.restwebshop.Controllers;

import com.irhad.restwebshop.Domain.Models.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {
    @RequestMapping("/")
    public String index() {
        return "Hello!";
    }

}
