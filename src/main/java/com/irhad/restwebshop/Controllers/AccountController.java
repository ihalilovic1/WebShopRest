package com.irhad.restwebshop.Controllers;

import com.irhad.restwebshop.Domain.Models.User;
import com.irhad.restwebshop.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping("/Login")
    @ResponseBody
    public User login(@RequestParam User userReq) {
        User user = new User();
        user.setFirstName("Ime");
        user.setLastName("Prezume");
        return user;
    }
}
