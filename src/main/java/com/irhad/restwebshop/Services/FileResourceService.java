package com.irhad.restwebshop.Services;

import com.irhad.restwebshop.Domain.Models.FileResource;
import com.irhad.restwebshop.Domain.Models.ShopItem;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

public interface FileResourceService {
    String uploadFile(byte[] content);

    FileResource createFile(String path, ShopItem shopItem);

    FileResource findById(UUID id);

    FileResource updateFile(FileResource fileResource);
}
