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
 * @author danko
 */
public class Main {
    public static void main(String[] args) {
        Parent root = FXMLLoader.load(getClass().getResource("tela_grafico.fxml"));
        TelaBrancaComBotao tela = new TelaBrancaComBotao();
        Stage stage = new Stage();
        tela.start("tela_grafico.fxml");
    }
}

