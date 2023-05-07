package com.mycompany.api.bd2;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {


    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaLogin.fxml"));
      Parent root = loader.load();
      Scene cena = new Scene(root);
      stage.setScene(cena);
      stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static Scene getScene() {
        return scene;
    }

    public static void setRoot(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent root = fxmlLoader.load();
        scene.setRoot(root);
    }

    public static void setScene(Scene newScene) {
        scene = newScene;
    }

}