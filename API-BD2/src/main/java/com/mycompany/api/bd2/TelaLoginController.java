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
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
            if (usuario!=null && usuario.getUsername().equals( user) && usuario.getSenha().equals(senha) && usuario.getCargo()=="colaborador") {
                
                System.out.println("Logado");
                System.out.println(usuario.getNome());
                LoginSenha.setText("");
                // Usuário e senha são válidos, exibir próxima tela
                usuariologado.setUsername(user);
                //usuario1.setSenha(senha);
                App.setRoot("LancamentoColaborador");
                
            } else{
                if (usuario!=null && usuario.getUsername().equals( user) && usuario.getSenha().equals(senha) && usuario.getCargo() == "gestor") {
                
                System.out.println("Logado como gestor");
                System.out.println(usuario.getNome());
                LoginSenha.setText("");
                // Usuário e senha são válidos, exibir próxima tela
                App.setRoot("LancamentoColaborador");
                
            } else{
                if (usuario!=null && usuario.getUsername().equals( user) && usuario.getSenha().equals(senha) && usuario.getCargo() == "admin") {
                
                System.out.println("Logado como administrador");
                System.out.println(usuario.getNome());
                LoginSenha.setText("");
                // Usuário e senha são válidos, exibir próxima tela
                App.setRoot("CadastroUsuarioADM");
                }
                else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Usuário ou senha inválidos");
                alert.setContentText("Por favor verifique suas credenciais e tente novamente.");
                alert.showAndWait();
            }
            }
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
       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       stage.close();
    }
}
