package org.mustycodified.bookui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.mustycodified.bookui.UIUtils;
import org.mustycodified.bookui.config.SceneManager;
import org.mustycodified.bookui.model.request.UserLoginRequestModel;
import org.mustycodified.bookui.service.Consumer;

public class LoginController {

    public Button loginButton;
    @FXML private TextField emailField;
    @FXML private TextField passwordField;

    @FXML
    private void loginUser() {
        UserLoginRequestModel login = new UserLoginRequestModel(emailField.getText(), passwordField.getText());
        boolean loginSuccess =  Consumer.loginUser(login);
        if ( loginSuccess) {
            SceneManager.switchScene("book-view.fxml", "Library Management System");
        } else {
           UIUtils.showErrorDialog("Login Failed", "Invalid email or password. Please try again.", "");

        }
    }

}


