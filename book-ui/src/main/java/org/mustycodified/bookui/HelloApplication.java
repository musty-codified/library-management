package org.mustycodified.bookui;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.mustycodified.bookui.controller.BookController;


import java.io.IOException;

public class HelloApplication extends Application  {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("resources/org/mustycodified/bookui/main-view.fxml"));

        BookController bookTableView = new BookController();
        VBox root = new VBox(bookTableView.getTableView(), bookTableView.getForm());
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Library Management System");
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }





}