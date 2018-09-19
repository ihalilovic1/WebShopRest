package com.irhad.restwebshop.Services;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.irhad.restwebshop.Domain.Models.FileResource;
import com.irhad.restwebshop.Domain.Models.ShopItem;
import com.irhad.restwebshop.Repositories.FileResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class FileResourceServiceImpl implements FileResourceService {

    @Autowired
    FileResourceRepository fileResourceRepository;

    private final List<String> stubImages = Arrays.asList("https://lh3.google.com/u/0/d/11a8yqYavrBGrqu3Uj0Y57gR7rrCzXEQj",
            "https://lh3.google.com/u/0/d/1LJ5nb6RQJNiW8YiBM_3XIN52H9GdR_U2",
            "https://lh3.google.com/u/0/d/1_XmDtucJedrb1FxZwZLI3XL3792X1zGY",
            "https://lh3.google.com/u/0/d/1ROYkq9HyzMoSPilw-U_kgIl6SslmUSWu");


    @Override
    public String uploadFile(byte[] content) {
        return stubImages.get(ThreadLocalRandom.current().nextInt(0, 4));
    }

    @Override
    public FileResource createFile(String path, ShopItem shopItem) {
        return fileResourceRepository.save(FileResource.builder().path(path).shopItem(shopItem).build());
    }

    @Override
    public FileResource findById(UUID id) {
        return fileResourceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("File not found"));
    }

    @Override
    public FileResource updateFile(FileResource fileResource) {
        return fileResourceRepository.save(fileResource);
    }
}
