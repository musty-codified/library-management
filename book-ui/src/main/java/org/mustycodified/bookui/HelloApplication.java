package org.mustycodified.bookui;

import javafx.application.Application;

import javafx.stage.Stage;
import org.mustycodified.bookui.config.SceneManager;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        SceneManager.setPrimaryStage(stage);
        SceneManager.switchScene("book-view.fxml", "Library Management System - User Registration");

    }

    public static void main(String[] args) {
        launch();
    }

}