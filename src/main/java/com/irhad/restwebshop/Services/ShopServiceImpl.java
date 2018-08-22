package com.irhad.restwebshop.Services;

import com.irhad.restwebshop.Repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    ShopRepository shopRepository;
}
