package com.Application.Configuration;

public interface FileConfiguration {
    default String createSourcePath(String filePath) {
        return filePath;
    }
}
