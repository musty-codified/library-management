package org.mustycodified.bookui.config;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {
    private static Stage window;

    public static void setPrimaryStage(Stage stage) {
        window = stage;
    }

    public static void switchScene(String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/org/mustycodified/bookui/" + fxmlFile));
            Parent root = loader.load();
            window.setTitle(title);

            Scene scene = new Scene(root);

            // Add CSS file
            scene.getStylesheets().add(SceneManager.class.getResource("/org/mustycodified/bookui/styles.css").toExternalForm());
            scene.getStylesheets().add(SceneManager.class.getResource("/org/mustycodified/bookui/users.css").toExternalForm());

            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logout() {
        System.out.println("User logged out.");
        switchScene("login-view.fxml", "User Login");
    }
}
