package com.irhad.restwebshop.Services;

import com.irhad.restwebshop.Domain.Models.FileResource;
import com.irhad.restwebshop.Domain.Models.ShopItem;
import org.springframework.web.multipart.MultipartFile;

public interface FileResourceService {
    String uploadFile(byte[] content);

    FileResource createFile(String path, ShopItem shopItem);
}
