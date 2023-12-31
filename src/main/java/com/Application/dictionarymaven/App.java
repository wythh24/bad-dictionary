package com.Application.dictionarymaven;

import com.Application.Controller.InitialController;
import com.Application.Configuration.FileConfigurationImp;
import com.Application.Configuration.FileConfiguration;
import com.Application.Features.FileFeature;
import com.Application.Features.FileFeatureImp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FileConfiguration configuration = new FileConfigurationImp();
        FileFeature fileFeature = new FileFeatureImp();

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("home-screen.fxml"));

        fxmlLoader.setControllerFactory(param -> new InitialController(configuration, fileFeature));

        Scene scene = new Scene(fxmlLoader.load(), 964, 616);

        stage.setTitle("DictionaryGodMode!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {

        launch();
    }
}