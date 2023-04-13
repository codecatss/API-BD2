/* doesn't work with source level 1.8:
module com.mycompany.api.bd2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.api.bd2 to javafx.fxml;
    exports com.mycompany.api.bd2;
}
*/
import javafx.scene.Parent;
import javafx.stage.Stage;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author dankoS
 */
public class Main {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Ola.fxml"));
    Parent root = fxmlLoader.load();
    Scene scene = new Scene(root);  
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.show();
    }
}

