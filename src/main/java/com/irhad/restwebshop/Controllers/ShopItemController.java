package com.irhad.restwebshop.Controllers;

import com.irhad.restwebshop.Domain.DTOs.ShopDTO;
import com.irhad.restwebshop.Domain.DTOs.ShopItemDTO;
import com.irhad.restwebshop.Domain.Models.Shop;
import com.irhad.restwebshop.Domain.Models.ShopItem;
import com.irhad.restwebshop.Domain.Models.User;
import com.irhad.restwebshop.Domain.ResourceHelpers.ShopDTOAssembler;
import com.irhad.restwebshop.Services.AccountService;
import com.irhad.restwebshop.Services.ShopItemService;
import com.irhad.restwebshop.Services.ShopService;
import com.irhad.restwebshop.Services.UserAuthenticationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/Item")
@Api(value="items", description="Shop items...")
public class ShopItemController {
    @Autowired
    ShopService shopService;
    @Autowired
    ShopItemService shopItemService;
    @Autowired
    UserAuthenticationService authentication;
    @Autowired
    AccountService accountService;

    @ApiOperation(value = "Add items to shop", response = ShopDTO.class)
    @RequestMapping(value = "/Create/{shopId}", method = RequestMethod.POST)
    @ResponseBody
    public ShopItemDTO createShopItem(@PathVariable UUID shopId, @RequestBody @Valid ShopItemDTO model,
                                      final HttpServletResponse response) {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            Shop shop = shopService.findById(shopId);

            if(!user.getId().equals(shop.getOwner().getId())) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return null;
            }
            ShopItem shopItem = shopItemService.createItem(model.getName(), model.getDescription(), shop,
                    model.getPrice(), model.getCount(), model.getEnabled());

            return new ShopItemDTO(shopItem);
        } catch (IllegalArgumentException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }

    }

}
