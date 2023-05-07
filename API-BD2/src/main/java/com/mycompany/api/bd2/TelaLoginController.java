package com.mycompany.api.bd2;

import Conexao.Conexao;
import com.mycompany.api.bd2.daos.usuarioDAO;
import com.mycompany.api.bd2.models.Usuario;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TelaLoginController implements Initializable {

    @FXML
    private TextField LoginUsuário;
    @FXML
    private PasswordField LoginSenha;
    @FXML
    private Button LoginBotaoEntrar;
    @FXML
    private Button LoginBotaoFechar;

    public static Usuario getUsuario1() {
        return usuariologado;
    }

    public static Usuario usuariologado = new Usuario();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String user = LoginUsuário.getText();
        String senha = LoginSenha.getText();

        try (Connection connection = Conexao.createConnectionToMySQL()) {
            Usuario usuario = new usuarioDAO().getUsuario(user, senha);
            if (usuario != null && usuario.getUsername().equals(user) && usuario.getSenha().equals(senha) && usuario.getCargo() == "colaborador") {

                System.out.println("Logado");
                System.out.println(usuario.getNome());
                LoginSenha.setText("");

                // Transição de tela
                FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.3), ((Node) event.getSource()).getScene().getRoot());
                fadeOut.setFromValue(1);
                fadeOut.setToValue(0);
                fadeOut.setOnFinished((ActionEvent event1) -> {
                    try {
                        // Usuário e senha são válidos, exibir próxima tela
                        usuariologado.setUsername(user);
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("LancamentoColaborador.fxml"));
                        Parent root = loader.load();
                        Scene cena = new Scene(root);
                        Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
                        stage.setScene(cena);
                        stage.centerOnScreen();
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                fadeOut.play();

            } else {
                if (usuario != null && usuario.getUsername().equals(user) && usuario.getSenha().equals(senha) && usuario.getCargo() == "gestor") {

                    System.out.println("Logado como gestor");
                    System.out.println(usuario.getNome());
                    LoginSenha.setText("");

                    // Transição de tela
                    FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), ((Node) event.getSource()).getScene().getRoot());
                    fadeOut.setFromValue(1);
                    fadeOut.setToValue(0);
                    fadeOut.setOnFinished((ActionEvent event1) -> {
                        try {
                            // Usuário e senha são válidos, exibir próxima tela
                            usuariologado.setUsername(user);
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("LancamentoColaborador.fxml"));
                            Parent root = loader.load();
                            Scene cena = new Scene(root);
                            Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
                            stage.setScene(cena);
                            stage.centerOnScreen();
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                    fadeOut.play();

                } else if (usuario != null && usuario.getUsername().equals(user) && usuario.getSenha().equals(senha) && usuario.getCargo() == "admin") {

                    System.out.println("Logado como administrador");
                    System.out.println(usuario.getNome());
                    LoginSenha.setText("");

                    // Transição de tela
                    FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), ((Node) event.getSource()).getScene().getRoot());
                    fadeOut.setFromValue(1);
                    fadeOut.setToValue(0);
                    fadeOut.setOnFinished((ActionEvent event1) -> {
                        try {
                            // Usuário e senha são válidos, exibir próxima tela
                            usuariologado.setUsername(user);
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("CadastroUsuarioADM.fxml"));
                            Parent root = loader.load();
                            Scene cena = new Scene(root);
                            Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
                            stage.setScene(cena);
                            stage.centerOnScreen();
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                    fadeOut.play();

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                
            
         
            }
        }

    }  catch (SQLException e) {
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
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
