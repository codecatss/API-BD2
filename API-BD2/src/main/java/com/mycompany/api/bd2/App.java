package com.mycompany.api.bd2;

import com.mycompany.api.bd2.daos.usuarioDAO;
import com.mycompany.api.bd2.models.Usuario;
import testando_view_exemplo.testeLeitura;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CadastroUsuarioADM.fxml"));
        Parent root = fxmlLoader.load();
        scene = new Scene(root, 1300, 650);
        stage.setScene(scene);
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
