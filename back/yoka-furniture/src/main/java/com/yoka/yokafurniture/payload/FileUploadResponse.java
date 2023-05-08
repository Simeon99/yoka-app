package com.yoka.yokafurniture.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileUploadResponse {

    private String fileName;
    private String contentType;
    private String url;

}
