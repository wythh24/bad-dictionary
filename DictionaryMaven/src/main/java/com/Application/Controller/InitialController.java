package com.Application.Controller;

import com.Application.Configuration.FileConfiguration;
import com.Application.Features.FileFeature;
import com.Application.Model.DictionaryEntity;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;


public class InitialController implements Initializable {

    private final FileConfiguration configuration;
    @FXML
    public ListView<String> wordList;
    private final FileFeature fileFeature;
    @FXML
    public ComboBox<String> listRecent;
    @FXML
    public Button previousBtn;
    @FXML
    public Button nextBtn;
    private String sourceFile = null;

    @FXML
    private TextField searchField;

    private int currentIndex = -1;

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
        var banana = new DictionaryEntity("PineApple", "eyes tree ever", "noun");

        fileFeature.addToDictionary(sourcePrivate, banana);
        fileFeature.saveDictionary(sourcePrivate, path);

    }

    private String getSourceFilePath(String fileName) {
        //get locate of dir of project
        String getProjectDir = System.getProperty("user.dir");
        if (sourceFile == null) sourceFile = getProjectDir;
        String filePath = "\\Source" + "\\" + fileName + ".dat";
        return sourceFile.concat(filePath);
    }

    @FXML
    protected void searchWord() {
        for (var word : sourcePrivate) {
            if (word.getWord().equalsIgnoreCase(searchField.getText())) {
                wordList.getSelectionModel().select(word.getWord());
                addToRecent(listRecent, word.getWord());
            }
        }
    }

    private Vector<DictionaryEntity> getSource() {
        return fileFeature.loadDictionary(getSourceFilePath(sourceName));
    }

    private void initialDictionaryData() {
        for (var word : sourcePrivate) {
            wordList.getItems().add(word.getWord());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initialDictionaryData();
        previousBtn.setDisable(true);

        //comboBox action
        listRecent.setOnAction(event -> {
            String value = listRecent.getValue();
            selectListItem(value);
        });

        //ListView action
        wordList.getSelectionModel().selectedItemProperty().addListener((ObservableList, oldValue, newValue) -> {
            if (newValue != null) {
                addToRecent(listRecent, newValue);
                buttonState();
            }
        });
        //previous button
        previousBtn.setOnAction(event -> {

            int currentIndex = wordList.getSelectionModel().getSelectedIndex();
            if (currentIndex > 0) {
                int previousIndex = findPreviousSelectedIndex(currentIndex);
                if (previousIndex >= 0) {
                    wordList.getSelectionModel().select(previousIndex);
                    wordList.scrollTo(previousIndex);
                }
            }
        });
        //next button
        nextBtn.setOnAction(event -> {
            int currentIndex = wordList.getSelectionModel().getSelectedIndex();
            int itemCount = wordList.getItems().size();
            if (currentIndex < itemCount - 1) {
                int nextIndex = findNextSelectedIndex(currentIndex);
                if (nextIndex >= 0) {
                    wordList.getSelectionModel().select(nextIndex);
                    wordList.scrollTo(nextIndex);
                }
            }
        });
    }

    private void addToRecent(ComboBox<String> comboBox, String value) {
        if (!isContain(comboBox, value)) {
            comboBox.getItems().add(value);
        }
    }

    private boolean isContain(ComboBox<String> comboBox, String value) {
        if (!comboBox.getItems().contains(value)) {
            return false;
        }
        return true;
    }

    private void selectListItem(String value) {
        wordList.getSelectionModel().select(value);
    }

    private void buttonState() {
        int currentIndex = wordList.getSelectionModel().getSelectedIndex();
        int countItem = wordList.getItems().size();
        nextBtn.setDisable(currentIndex >= countItem - 1);
        previousBtn.setDisable(currentIndex <= 0);
    }

    private int findNextSelectedIndex(int currentIndex) {
        int itemCount = wordList.getItems().size();
        for (int i = currentIndex + 1; i < itemCount; i++) {
            if (wordList.getSelectionModel().isSelected(i)) {
                return i;
            }
        }
        return -1;
    }

    private int findPreviousSelectedIndex(int currentIndex) {
        for (int i = currentIndex - 1; i >= 0; i--) {
            if (wordList.getSelectionModel().isSelected(i)) {
                return i;
            }
        }
        return -1;
    }
}