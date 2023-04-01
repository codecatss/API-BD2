import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TelaBrancaComBotao extends Application {

    /**
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        // Criar o botão
        Button button = new Button("Clique aqui!");

        // Criar o layout da tela com o botão centralizado
        StackPane root = new StackPane();
        root.getChildren().add(button);

        // Criar a cena com o layout e uma largura e altura definidas
        Scene scene = new Scene(root, 300, 250);

        // Definir a cena como a cena principal do palco (stage)
        primaryStage.setScene(scene);

        // Definir o título da janela
        primaryStage.setTitle("Tela branca com botão");

        // Mostrar a janela
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
