package com.irhad.restwebshop.Controllers;

import com.irhad.restwebshop.Domain.DTOs.ShopDTO;
import com.irhad.restwebshop.Domain.Models.Shop;
import com.irhad.restwebshop.Domain.Models.User;
import com.irhad.restwebshop.Domain.ResourceHelpers.ShopDTOAssembler;
import com.irhad.restwebshop.Services.AccountService;
import com.irhad.restwebshop.Services.ShopService;
import com.irhad.restwebshop.Services.UserAuthenticationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.util.Set;
import java.util.UUID;

import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.removeStart;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/Shop")
@Api(value="shops", description="Shops...")
public class ShopController {
    @Autowired
    ShopService shopService;
    @Autowired
    UserAuthenticationService authentication;
    @Autowired
    AccountService accountService;
    @Autowired
    ShopDTOAssembler shopDTOAssembler;

    @ApiOperation(value = "Create new shop", response = ShopDTO.class)
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ShopDTO createShop(@RequestBody @Valid ShopDTO model, final HttpServletRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ShopDTO shopDTO = new ShopDTO(shopService.createShop(model.getName(), model.getDescription(), model.getAdress(),
                user));

        return shopDTO;
    }
    @ApiOperation(value = "Get shops for the logged in user", response = Set.class)
    @RequestMapping(value = "/UserShops", method = RequestMethod.GET)
    @ResponseBody
    public Set<ShopDTO> getShopsByUser(@RequestParam UUID id) {

        User user = accountService.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));

        return ShopDTO.getShopDTOSet(shopService.findByOwner(user));
    }

    @ApiOperation(value = "Get shop details", response = ShopDTO.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ShopDTO getShop(@PathVariable UUID id, final HttpServletResponse response) {
        try {
            ShopDTO shop = shopDTOAssembler.toResource(shopService.findById(id));
            return shop;
        } catch (IllegalArgumentException ex) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }

    }

    @ApiOperation(value = "Delete", response = ShopDTO.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ShopDTO deleteShop(@PathVariable UUID id, final HttpServletResponse response) {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            Shop shop = shopService.findById(id);

            if(user.getId().equals(shop.getOwner().getId())) {
                if(!shopService.deleteShop(shop))
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
            else {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
        } catch (IllegalArgumentException ex) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return null;
    }

    @ApiOperation(value = "Update shop", response = ShopDTO.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ShopDTO updateShop(@PathVariable UUID id, @RequestBody @Valid ShopDTO model, final HttpServletResponse response) {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            Shop shop = shopService.findById(model.getShopId());

            if(shop.getOwner().getId() != user.getId()){
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return null;
            }

            shop.setName(model.getName());
            shop.setAdress(model.getAdress());
            shop.setDescription(model.getDescription());

            return shopDTOAssembler.toResource(shopService.updateShop(shop));

        } catch (IllegalArgumentException ex) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }

    }



}
