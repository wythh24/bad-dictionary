package com.Application.Configuration;

import java.io.*;

public class FileConfigurationImp implements FileConfiguration {
    public String createSourcePath(String filePath) {
        File file = new File(filePath.trim().toLowerCase());
        try {
            if (!file.exists()) {
                System.out.println("File not found");
            }
            File parentDir = file.getParentFile();

            if (!parentDir.exists()) {
                System.out.println("Dir not found");
                parentDir.mkdirs();
            }

            if (!file.createNewFile()) {
                System.out.println("False to create source file");
            }
            System.out.println("File already created at : " + filePath);
            return filePath;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

