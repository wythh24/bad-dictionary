package com.Application.Controller;

import com.Application.Configuration.FileConfiguration;
import com.Application.Features.FileFeature;
import com.Application.Model.DictionaryEntity;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.Vector;


public class InitialController {

    private final FileConfiguration configuration;
    @FXML
    public ListView<String> wordList;
    private final FileFeature fileFeature;
    private String sourceFile = null;

    @FXML
    private TextField searchField;

    private final String sourceName = "dictionarySource";
    private Vector<DictionaryEntity> sourcePrivate = new Vector<>();

    public InitialController(FileConfiguration configuration, FileFeature fileFeature) {
        this.configuration = configuration;
        this.fileFeature = fileFeature;
        sourcePrivate = getSource();
    }

    private void InitialSourcePath() {

        String path = configuration.createSourcePath(getSourceFilePath(sourceName));

        var initValue = new DictionaryEntity("Apple", "small tree ", "noun");

        fileFeature.addToDictionary(sourcePrivate, initValue);
        fileFeature.saveDictionary(sourcePrivate, path);

    }

    private String getSourceFilePath(String fileName) {
        //get locate of dir of project
        String getProjectDir = System.getProperty("user.dir");

        if (sourceFile == null) sourceFile = getProjectDir;

        String filePath = "\\Source" + "\\" + fileName + ".dat";
        return sourceFile.concat(filePath);
    }

    protected Vector<DictionaryEntity> ReadFromSource() {
        return fileFeature.loadDictionary(sourceName);
    }

    @FXML
    protected void searchWord() {
        for (var word : sourcePrivate) {
            if (word.getWord().equalsIgnoreCase(searchField.getText())) {
                wordList.getItems().removeAll(word.getWord());
                wordList.getItems().add(word.getWord());
            }
        }
    }

    private Vector<DictionaryEntity> getSource() {
        return fileFeature.loadDictionary(getSourceFilePath(sourceName));
    }

    private void testOutput() {
        for (var word : sourcePrivate) {
            System.out.println(word.getWord());
            wordList.getItems().add(word.getWord());
        }
    }

}