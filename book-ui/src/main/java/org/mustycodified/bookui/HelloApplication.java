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

//        UserController registerView = new UserController();
//        VBox vBox2 = new VBox(registerView.getForm());
//        vBox2.setPadding(new Insets(20, 20, 20, 20));
//        vBox2.setSpacing(20);
//        Scene registerScene = new Scene(vBox2, 400, 400);
//        stage.setScene(registerScene);
//        stage.setTitle("Library Management System");
//        stage.show();


//        BookController bookTableView = new BookController();
//        HBox hBox = new HBox(bookTableView.getForm());
//        hBox.setPadding(new Insets(20, 20,20,20));
//        hBox.setSpacing(10);
//        VBox vBox = new VBox(bookTableView.getTableView(), hBox);
//        vBox.setPadding(new Insxets(20,20,20,20));
//        Scene scene = new Scene(vBox, 800, 600);
//        stage.setScene(scene);
//        stage.setTitle("Library Management System");
//        stage.show();

        SceneManager.setPrimaryStage(stage);
        SceneManager.switchScene("book-view.fxml", "Library Management System - User Registration");

    }

    public static void main(String[] args) {
        launch();
    }

}