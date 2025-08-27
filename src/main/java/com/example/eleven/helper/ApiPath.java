package com.example.eleven.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiPath {
    @Value("${api.base-path}")
    private String basePath;

    @Value("${api.version}")
    private String version;

    public String getPath(String subPath) {
        return basePath + "/" + version + subPath;
    }
}
