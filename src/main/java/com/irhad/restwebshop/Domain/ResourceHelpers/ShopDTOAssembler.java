package com.irhad.restwebshop.Domain.ResourceHelpers;

import com.irhad.restwebshop.Controllers.ShopController;
import com.irhad.restwebshop.Domain.DTOs.ShopDTO;
import com.irhad.restwebshop.Domain.Models.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class ShopDTOAssembler extends ResourceAssembler<Shop, ShopDTO> {
    @Autowired
    protected EntityLinks entityLinks;
    private static final String UPDATE_REL = "Update";
    private static final String DELETE_REL = "Delete";
    @Override
    public ShopDTO toResource(Shop shop) {
        ShopDTO resource = new ShopDTO(shop);
        final LinkBuilder selfLink = ControllerLinkBuilder.linkTo(ShopController.class);
        resource.add(selfLink.slash(shop).withSelfRel());
        resource.add(selfLink.slash(UPDATE_REL).withRel(UPDATE_REL));
        resource.add(selfLink.slash(DELETE_REL).slash(shop).withRel(DELETE_REL));
        return resource;
    }
}