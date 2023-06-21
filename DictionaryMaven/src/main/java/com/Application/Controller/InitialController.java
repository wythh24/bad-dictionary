package com.Application.Controller;

import com.Application.Configuration.FileConfiguration;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


public class InitialController {

    private final FileConfiguration configuration;
    private String sourceFile;
    @FXML
    private TextField searchField;

    @FXML
    private ListView<String> wordList;
    @FXML

    private ObservableList<String> source = null;

    public InitialController(FileConfiguration configuration) {
        this.configuration = configuration;
    }

    private void InitialSourcePath() {
        configuration.createSourcePath(null);
    }

    private String getSourceFile() {

        String getProjectDir = System.getProperty("user.dir");
        if (sourceFile == null) sourceFile = getProjectDir;
        String filePath = "Source" + "\\" + "dictionary.csv";
        return sourceFile.concat(filePath);
    }

    @FXML
    protected void ReadFromCsv(){
        System.out.println(getSourceFile());
        wordList.getItems().add(searchField.getText().toString());

        var count = configuration.readAllExample(getSourceFile());
        System.out.println(count.size());

    }

}