/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.api.bd2;

import static com.mycompany.api.bd2.TelaLoginController.usuariologado;
import com.mycompany.api.bd2.models.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author csous
 */
public class ApontamentoGestor implements Initializable {

    @FXML
    private Button fecharTela;
    @FXML
    private Button minimizarTela;
    @FXML
    private Label nometelaatual;
    @FXML
    private ImageView imagemUser;
    @FXML
    private Label nomeUsuario;
    @FXML
    private Button botaoSair;
    @FXML
    private Button menuLancamento;
    @FXML
    private Button menuApontamento;
    @FXML
    private Button menuRelatorio;
    @FXML
    private TableColumn<?, ?> tabela_aprovacao;
    @FXML
    private Button botaoAprovar1;
    @FXML
    private Button botaoReprovar1;
    
    public static Usuario getUsuario1() {
        return usuariologado;
    }
    public static Usuario usuariologado = new Usuario();
    private String usuario = TelaLoginController.usuariologado.getUsername();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nomeUsuario.setText(usuario);
    }    
    public void fechaTela(){
    Platform.exit();
    }
    
}
