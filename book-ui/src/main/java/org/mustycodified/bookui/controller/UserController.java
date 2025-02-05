package org.mustycodified.bookui.controller;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.mustycodified.bookui.config.SceneManager;
import org.mustycodified.bookui.model.request.User;
import org.mustycodified.bookui.service.RestClient;

public class UserController {

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    @FXML private TextField passwordField;
    @FXML private TextField phoneNumberField;

    @FXML
    private void registerUser() {
        User newUser = new User(
                firstNameField.getText(),
                lastNameField.getText(),
                emailField.getText(),
                passwordField.getText(),
                phoneNumberField.getText());
        RestClient.registerUser(newUser);

        //Clear form
        firstNameField.clear();
        lastNameField.clear();
        emailField.clear();
        passwordField.clear();
        passwordField.clear();

        // Switch to Log in screen
        SceneManager.switchScene("login-view.fxml", "User Login");
    }


}
