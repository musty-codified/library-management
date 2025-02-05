package org.mustycodified.bookui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.mustycodified.bookui.config.SceneManager;
import org.mustycodified.bookui.model.request.Login;
import org.mustycodified.bookui.service.ProxyClient;

public class LoginController {

    @FXML private TextField emailField;
    @FXML private TextField passwordField;

    @FXML
    private void loginUser() {
        Login login = new Login(emailField.getText(), passwordField.getText());
      boolean loginSuccess =  ProxyClient.loginUser(login);
        emailField.clear();
        passwordField.clear();
        if ( loginSuccess) {
            SceneManager.switchScene("book-view.fxml", "Book Table List");
        } else {
            showAlert("Login Failed", "Invalid email or password. Please try again.");

        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }
}


