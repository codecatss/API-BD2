package com.mycompany.api.bd2;

import com.mycompany.api.bd2.models.Usuario;
import Conexao.Conexao;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class TelaLoginController implements Initializable {

    @FXML
    private TextField LoginUsuário;
    @FXML
    private PasswordField LoginSenha;
    @FXML
    private Button LoginBotaoEntrar;
    @FXML
    private Button LoginBotaoFechar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String user = LoginUsuário.getText();
        String senha = LoginSenha.getText();

        try (Connection connection = Conexao.createConnectionToMySQL()) {
            String query = "SELECT * FROM usuario WHERE username = ? AND senha = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user);
            statement.setString(2, senha);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // Usuário e senha são válidos, exibir próxima tela
                Usuario usuario = Usuario.getInstance();
                usuario.setUser_name(user);
                usuario.setSenha(senha);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Usuário ou senha inválidos");
                alert.setContentText("Por favor verifique suas credenciais e tente novamente.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro de banco de dados");
            alert.setContentText("Ocorreu um erro ao se comunicar com o banco de dados. Por favor tente novamente mais tarde.");
            alert.showAndWait();
            e.printStackTrace();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro desconhecido");
            alert.setContentText("Ocorreu um erro desconhecido. Por favor tente novamente mais tarde.");
            alert.showAndWait();
            e.printStackTrace();
        } 
    }

    @FXML
    private void handleFecharButtonAction(ActionEvent event) {
        // Fechar a tela de login
    }
}
