package com.irhad.restwebshop.Controllers;

import com.irhad.restwebshop.Domain.DTOs.RegisterDTO;
import com.irhad.restwebshop.Domain.DTOs.UserDTO;
import com.irhad.restwebshop.Domain.Models.User;
import com.irhad.restwebshop.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/Account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/Register", method = RequestMethod.POST)
    @ResponseBody
    public UserDTO register(@ModelAttribute @Valid RegisterDTO model) {
        UserDTO user = new UserDTO();
        user.setFirstName("Ime");
        user.setLastName("Prezime");
        return user;
    }

    @RequestMapping(value = "/GetCurrentUser", method = RequestMethod.GET)
    @ResponseBody
    public UserDTO getCurrentUser() {
        User user = accountService.login();
        UserDTO userDTO = new UserDTO();
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        return userDTO;
    }
}
