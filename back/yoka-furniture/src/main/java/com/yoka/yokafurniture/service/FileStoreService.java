package com.yoka.yokafurniture.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStoreService {

    String storeFile(MultipartFile file);

    Resource downloadFile(String fileName);

}
