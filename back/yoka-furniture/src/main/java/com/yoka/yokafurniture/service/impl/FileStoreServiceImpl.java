package com.yoka.yokafurniture.service.impl;

import com.yoka.yokafurniture.exception.FileNotFoundException;
import com.yoka.yokafurniture.exception.FileStoreException;
import com.yoka.yokafurniture.service.FileStoreService;
import com.yoka.yokafurniture.utils.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStoreServiceImpl implements FileStoreService {

    private Path fileStoragePath;

    private String fileStorageLocation;

    public FileStoreServiceImpl(@Value("${file.upload-dir}") String fileStorageLocation){

        this.fileStorageLocation = fileStorageLocation;

        fileStoragePath = Paths.get(fileStorageLocation).toAbsolutePath().normalize();

        try {
            Files.createDirectories(fileStoragePath);
        } catch (IOException e){
            throw new FileStoreException("Issue in creating file directory.");
        }
    }


    @Override
    public String storeFile(MultipartFile file) {

        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
        String[] originalFileNameArray = originalFileName.split("\\.");

        String fileName = Utils.generateRandomString(8) + "-" + System.currentTimeMillis() + "." + originalFileNameArray[originalFileNameArray.length - 1];

        Path filePath = Paths.get(fileStoragePath+ "\\" + fileName);

        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new FileStoreException("Issue in storing the file.", e);
        }

        return fileName;
    }

    @Override
    public Resource downloadFile(String fileName) {

        Path path = Paths.get(fileStorageLocation).toAbsolutePath().resolve(fileName);

        Resource resource;

        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Issue in reading the file.", e);
        }

        if(resource.exists() && resource.isReadable()){
            return resource;
        }
        else throw new FileNotFoundException("the file doesn't exist or not readable");

    }
}
