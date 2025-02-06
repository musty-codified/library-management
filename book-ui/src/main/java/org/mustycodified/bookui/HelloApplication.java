package org.mustycodified.bookui;

import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.mustycodified.bookui.config.SceneManager;
import org.mustycodified.bookui.controller.BookController;
import org.mustycodified.bookui.controller.UserController;


import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/org/mustycodified/bookui/main-view.fxml"));
        SceneManager.setPrimaryStage(stage);
        SceneManager.switchScene("register-view.fxml", "Library Management System - User Registration");

    }

    public static void main(String[] args) {
        launch();
    }

}