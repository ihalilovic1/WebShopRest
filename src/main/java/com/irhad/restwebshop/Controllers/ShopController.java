package com.irhad.restwebshop.Controllers;

import com.irhad.restwebshop.Domain.DTOs.ShopDTO;
import com.irhad.restwebshop.Domain.Models.User;
import com.irhad.restwebshop.Services.AccountService;
import com.irhad.restwebshop.Services.ShopService;
import com.irhad.restwebshop.Services.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.Set;
import java.util.UUID;

import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.removeStart;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/Shop")
public class ShopController {
    @Autowired
    ShopService shopService;
    @Autowired
    UserAuthenticationService authentication;
    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/CreateShop", method = RequestMethod.POST)
    @ResponseBody
    public ShopDTO createShop(@RequestBody @Valid ShopDTO model, final HttpServletRequest request) {
        final String param = ofNullable(request.getHeader(AUTHORIZATION))
                .orElse(request.getParameter("t"));

        final String token = removeStart(param, "Bearer").trim();

        User user = authentication.findByToken(token).get();
        ShopDTO shopDTO = shopService.createShop(model.getName(), model.getDescription(), model.getAdress(),
                user);

        return shopDTO;
    }

    @RequestMapping(value = "/UserShops", method = RequestMethod.GET)
    @ResponseBody
    public Set<ShopDTO> shopsByUser(UUID id) {

        User user = accountService.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));

        return shopService.findByOwner(user);
    }

}
