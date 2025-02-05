package org.mustycodified.bookui.controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.mustycodified.bookui.model.User;
import org.mustycodified.bookui.service.RestClient;

public class UserController {
    private GridPane gridPane = new GridPane();
    private TextField firstNameField = new TextField();
    private TextField lastNameField = new TextField();
    private TextField emailField = new TextField();
    private TextField passwordField = new TextField();
    private TextField phoneNumberField = new TextField();
    @FXML
    private Button registerButton = new Button("Register");

    public UserController() {
        setupGrid();
        setupActions();
    }


    private void setupGrid() {
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(8);
        gridPane.setHgap(8);

        //First name input
        Label firstNameLabel = new Label("First Name");
        GridPane.setConstraints(firstNameLabel, 0, 0);

        // Last name input
        Label lastNameLabel = new Label("last Name");
        GridPane.setConstraints(lastNameLabel, 0, 0);

        //email input
        Label emailLabel = new Label("Email");
        GridPane.setConstraints(emailLabel, 0, 0);

        //password input
        Label passwordLabel = new Label();
        GridPane.setConstraints(passwordLabel, 0, 0);

        //phone input
        Label phoneLabel = new Label("Phone");
        GridPane.setConstraints(phoneLabel, 0, 0);

        GridPane.setConstraints(registerButton, 1, 2);
        gridPane.getChildren().addAll(firstNameLabel, lastNameLabel, emailLabel, passwordLabel, phoneLabel, registerButton);

    }
    private void setupActions() {
        registerButton.setOnAction(e -> registerUser());
        firstNameField.setPromptText("FirstName");
        firstNameField.setMinWidth(100);

        lastNameField.setPromptText("LastName");
        lastNameField.setMinWidth(100);

        emailField.setPromptText("Email");
        emailField.setMinWidth(100);

        passwordField.setPromptText("Password");
        passwordField.setMinWidth(100);

        phoneNumberField.setPromptText("Phone");
        phoneNumberField.setMinWidth(100);


    }

    private void registerUser() {
        User newUser = new User(firstNameField.getText(), lastNameField.getText(), emailField.getText(), passwordField.getText(), phoneNumberField.getText());
        RestClient.registerUser(newUser);
        firstNameField.clear();
        lastNameField.clear();
        emailField.clear();
        passwordField.clear();
        passwordField.clear();
    }

    public VBox getForm() {
        return new VBox(firstNameField, lastNameField, emailField, passwordField, phoneNumberField, registerButton);
    }

}
