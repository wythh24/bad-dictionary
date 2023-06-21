package com.Application.Configuration;

import com.Application.Model.DictionaryEntity;
import com.opencsv.CSVParser;
import com.opencsv.exceptions.CsvValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Format;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;

import com.opencsv.exceptions.CsvException;


public class FileConfigurationImp implements FileConfiguration {
    public String createSourcePath(String filePath) {
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("File not found");
        }
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {

            System.out.println("Dir not found");
            parentDir.mkdirs();
        }
        try {
            if (!file.createNewFile()) {
                System.out.println("False to create source file");
            }
            System.out.println("File already created at : " + filePath);
            return filePath;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private List<String[]> readAllLines(Path filePath) {
        try (Reader reader = Files.newBufferedReader(filePath)) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                return csvReader.readAll();
            } catch (CsvException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String[]> readAllExample(String filePath) {
        System.out.println("in (readAllExample)");
        Path path = null;
        try {
            path = Paths.get(ClassLoader.getSystemResource(filePath).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("file path var : " + path);
        if (path == null) System.out.println("false : " + path + " " + filePath);
        return readAllLines(path);
    }
}

