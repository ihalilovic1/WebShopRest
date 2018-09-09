package com.irhad.restwebshop.Controllers;

import com.irhad.restwebshop.Domain.DTOs.CreateItemDTO;
import com.irhad.restwebshop.Domain.DTOs.ShopDTO;
import com.irhad.restwebshop.Domain.DTOs.ShopItemDTO;
import com.irhad.restwebshop.Domain.Models.*;
import com.irhad.restwebshop.Domain.ResourceHelpers.ShopDTOAssembler;
import com.irhad.restwebshop.Services.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;
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
    @Autowired
    FileResourceService fileResourceService;

    @ApiOperation(value = "Add items to shop", response = ShopItemDTO.class)
    @RequestMapping(value = "/Create/{shopId}", method = RequestMethod.POST)
    @ResponseBody
    public ShopItemDTO createShopItem(@PathVariable UUID shopId, @RequestBody @Valid CreateItemDTO model,
                                      final HttpServletResponse response) {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            Shop shop = shopService.findById(shopId);

            if(!user.getId().equals(shop.getOwner().getId())) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return null;
            }
            Set<FileResource> photos = new HashSet<>();
            model.getPhotos().stream().forEach(file -> {
                String path = fileResourceService.uploadFile(null);
                photos.add(FileResource.builder().path(path).build());
            });

            ShopItem shopItem = shopItemService.createItem(model.getName(), model.getDescription(), shop,
                    model.getPrice(), model.getCount(), model.getEnabled(), null, photos);



            return new ShopItemDTO(shopItem);
        } catch (IllegalArgumentException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }

    }

    @ApiOperation(value = "Update existing shop items", response = ShopItemDTO.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ShopItemDTO updateShopItem(@PathVariable UUID id, @RequestBody @Valid ShopItemDTO model,
                                      final HttpServletResponse response) {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            ShopItem shopItem = shopItemService.findById(model.getShopItemId());

            if(!user.getId().equals(shopItem.getShop().getOwner().getId())) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return null;
            }
            shopItem.setCount(model.getCount());
            shopItem.setDescription(model.getDescription());
            shopItem.setName(model.getName());
            shopItem.setEnabled(model.getEnabled());
            shopItem.setPrice(model.getPrice());

            return new ShopItemDTO(shopItemService.updateShopItem(shopItem));
        } catch (IllegalArgumentException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }

    }

    @ApiOperation(value = "Delete shop items")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ShopItemDTO deleteShopItem(@PathVariable UUID id,
                                      final HttpServletResponse response) {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            ShopItem shopItem = shopItemService.findById(id);

            if(!user.getId().equals(shopItem.getShop().getOwner().getId())) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return null;
            }
            if (!shopItemService.deleteShopItem(shopItem)) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (IllegalArgumentException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return null;
    }

    @ApiOperation(value = "Get shop item details", response = ShopItemDTO.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ShopItemDTO getShopItem(@PathVariable UUID id,
                                      final HttpServletResponse response) {
        try {
            ShopItem shopItem = shopItemService.findById(id);
            return new ShopItemDTO(shopItem);
        } catch (IllegalArgumentException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return null;
    }
}
