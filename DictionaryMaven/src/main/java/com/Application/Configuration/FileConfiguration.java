package com.Application.Configuration;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public interface FileConfiguration {
    default String createSourcePath(String filePath) {
        return filePath;
    }

    default List<String[]> readAllExample(String filePath)  {
        return new ArrayList<>();
    }
}
