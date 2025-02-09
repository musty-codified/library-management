package org.mustycodified.bookui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.mustycodified.bookui.config.SceneManager;
import org.mustycodified.bookui.model.request.UserDetailsRequestModel;
import org.mustycodified.bookui.service.RestClient;

public class UserController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField phoneNumberField;

    @FXML
    private void registerUser() {
        UserDetailsRequestModel newUser = new UserDetailsRequestModel(
                firstNameField.getText(),
                lastNameField.getText(),
                emailField.getText(),
                passwordField.getText(),
                phoneNumberField.getText());
        clearFormField();
      boolean isRegistered = RestClient.registerUser(newUser);
      if (isRegistered){
          // Switch to Log in screen
          SceneManager.switchScene("login-view.fxml", "User Login");
      }

    }

    private void clearFormField() {
        firstNameField.clear();
        lastNameField.clear();
        emailField.clear();
        passwordField.clear();
        passwordField.clear();
    }


}
