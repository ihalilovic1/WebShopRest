package com.irhad.restwebshop.Controllers;

import com.irhad.restwebshop.Domain.DTOs.CreateItemDTO;
import com.irhad.restwebshop.Domain.DTOs.ItemFilterDTO;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("")
@Api(value="items", description="Shop items...")
public class ShopItemController {
    @Autowired
    ShopService shopService;
    @Autowired
    ShopItemService shopItemService;
    @Autowired
    AccountService accountService;
    @Autowired
    FileResourceService fileResourceService;



    @ApiOperation(value = "Filter all shop items", response = Set.class)
    @RequestMapping(value = "/ShopItem", method = RequestMethod.GET)
    @ResponseBody
    public Set<ShopItemDTO> getAll(ItemFilterDTO model,
                                      final HttpServletResponse response) {
        try {
            Set<ShopItem> shopItems = shopItemService.findAll(model == null? new ItemFilterDTO() : model);



            return ShopItemDTO.getShopItemDTOSet(shopItems);
        } catch (IllegalArgumentException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }

    }

    @ApiOperation(value = "Add item to shop", response = ShopItemDTO.class)
    @RequestMapping(value = "/Shop/{shopId}/Item", method = RequestMethod.POST)
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

            Set<ItemCategory> categories = model.getCategories().stream()
                    .map(c -> ItemCategory.builder().id(c).build()).collect(Collectors.toSet());

            ShopItem shopItem = shopItemService.createItem(model.getName(), model.getDescription(), shop,
                    model.getPrice(), model.getCount(), model.getEnabled(), categories, photos);

            for (UUID p : model.getPhotos()) {
                FileResource f = fileResourceService.findById(p);

                if (f != null) {
                    f.setShopItem(shopItem);
                    fileResourceService.updateFile(f);
                }

            }

            return new ShopItemDTO(shopItem);
        } catch (IllegalArgumentException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }

    }

    @ApiOperation(value = "Upload new file", response = ShopItemDTO.class)
    @RequestMapping(value = "/Shop/{shopId}/Item/UploadFile", method = RequestMethod.POST)
    @ResponseBody
    public FileResource uploadFile(@PathVariable UUID shopId, @RequestParam("file") MultipartFile file,
                                      final HttpServletResponse response) {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            Shop shop = shopService.findById(shopId);

            if(!user.getId().equals(shop.getOwner().getId())) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return null;
            }
           String path = fileResourceService.uploadFile(file.getBytes());
            return fileResourceService.createFile(path, null);
        } catch (IllegalArgumentException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }

    }

    @ApiOperation(value = "Update existing shop items", response = ShopItemDTO.class)
    @RequestMapping(value = "/Shop/{shopId}/Item/{id}", method = RequestMethod.PUT)
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
    @RequestMapping(value = "/Shop/{shopId}/Item/{id}", method = RequestMethod.DELETE)
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
    @RequestMapping(value = "/Shop/{shopId}/Item/{id}", method = RequestMethod.GET)
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
