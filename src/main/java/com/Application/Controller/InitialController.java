package com.Application.Controller;

import com.Application.Configuration.FileConfiguration;
import com.Application.Features.FileFeature;
import com.Application.Model.DictionaryEntity;
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

    //Generated variables
    @FXML
    public ListView<String> wordList;
    @FXML
    private TextField searchField;
    public ListView detailList;
    @FXML
    public ComboBox<String> listRecent;
    @FXML
    public Button previousBtn;
    @FXML
    public Button nextBtn;

    //Initial variables
    private Vector<DictionaryEntity> sourcePrivate = new Vector<>();
    private final FileFeature fileFeature;
    private String sourceFile = null;
    private final FileConfiguration configuration;
    private final String sourceName = "dictionarySource";

    public InitialController(FileConfiguration configuration, FileFeature fileFeature) {
        this.configuration = configuration;
        this.fileFeature = fileFeature;
        sourcePrivate = getSource();

    }

    private void InitialSourcePath() {

        String path = configuration.createSourcePath(getSourceFilePath(sourceName));

        var apple = new DictionaryEntity("Apple", "small tree ", "noun");
        var banana = new DictionaryEntity("Banana", "eyes tree ever", "noun");
        var orange = new DictionaryEntity("Orange", "damn ma bui", "noun");

        fileFeature.addToDictionary(sourcePrivate, orange);
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
        String searchTermFormat = searchField.getText().toLowerCase().trim();
        for (var word : sourcePrivate) {
            if (word.getWord().toLowerCase().contains(searchTermFormat)) {
                wordList.getSelectionModel().select(word.getWord());
                addToRecent(listRecent, word.getWord());
            }
        }
        searchField.clear();
    }

    private Vector<DictionaryEntity> getSource() {
        return fileFeature.loadDictionary(getSourceFilePath(sourceName));
    }

    private void initialLoadDictionaryData() {
        for (var word : sourcePrivate) {
            wordList.getItems().add(word.getWord());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initialLoadDictionaryData();
        initStateButton(true);

        //comboBox action
        listRecent.setOnAction(event -> {
            String value = listRecent.getValue();
            selectListItem(value);
            buttonState();
        });

        //ListView action
        wordList.getSelectionModel().selectedItemProperty().addListener((ObservableList, oldValue, newValue) -> {
            if (newValue != null) {
                addToRecent(listRecent, newValue);
                listRecent.getSelectionModel().select(newValue);
                addToDetail(sourcePrivate, newValue);
            }
        });
        //previous button
        previousBtn.setOnAction(event -> {
            int currentIndex = listRecent.getSelectionModel().getSelectedIndex();
            if (currentIndex > 0) {
                int previousIndex = findSelectedIndex(currentIndex, false);

                if (previousIndex >= 0) {
                    listRecent.getSelectionModel().select(previousIndex);
                    wordList.scrollTo(listRecent.getItems().indexOf(previousIndex));
                }
            }
        });
        //next button
        nextBtn.setOnAction(event -> {
            int currentIndex = listRecent.getSelectionModel().getSelectedIndex();
            int itemCount = listRecent.getItems().size();

            //itemCount - 1 = convert value as index
            if (currentIndex < itemCount - 1) {
                int nextIndex = findSelectedIndex(currentIndex, true);
                if (nextIndex >= 0) {
                    listRecent.getSelectionModel().select(nextIndex);
                }
            }
        });
    }

    private void initStateButton(boolean enable) {
        if (enable) {
            previousBtn.setDisable(true);
            nextBtn.setDisable(true);
        }
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
        int currentIndex = listRecent.getSelectionModel().getSelectedIndex();
        int countItem = listRecent.getItems().size();

        nextBtn.setDisable(currentIndex >= countItem - 1);
        previousBtn.setDisable(currentIndex <= 0);
    }

    private int findSelectedIndex(int currentIndex, boolean toNext) {
        for (int i = currentIndex; i >= 0; i--) {
            if (listRecent.getSelectionModel().isSelected(i)) {
                return !toNext ? i - 1 : i + 1;
            }
        }
        return -1;
    }

    private void addToDetail(Vector<DictionaryEntity> source, String value) {
        for (var word : source) {
            if (word.getWord().equalsIgnoreCase(value)) {
                detailList.getItems().clear();
                detailList.getItems().add(word.getWord() + " (" + word.getSubject() + ")");
                detailList.getItems().add(word.getDescription());
            }
        }
    }
}