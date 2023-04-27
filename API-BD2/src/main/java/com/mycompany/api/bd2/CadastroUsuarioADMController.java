/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.api.bd2;

import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import models.TipoHora;

/**
 * FXML Controller class
 *
 * @author conta
 */
public class CadastroUsuarioADMController {

    @FXML
    private Button fecharTela;
    @FXML
    private Button minimizarTela;
    @FXML
    private Label nometelaatual;
    @FXML
    private ImageView imagemUser;
    @FXML
    private ImageView imagemCR;
    @FXML
    private Label nomeUsuario;
    @FXML
    private Label CR_Funcao;
    @FXML
    private Button botaoSair;
    @FXML
    private Button botaoAlterarCR;
    @FXML
    private TextField entradaNome;
    @FXML
    private TextField entradaMatricula;
    @FXML
    private ComboBox<String> selecaoFuncao;
    @FXML
    private Button botaoAdicionar;
    @FXML
    private Button botaoLimpar;
    @FXML
    private Button botaoEditar;
    @FXML
    private Button botaoInativar;
    @FXML
    private Button botaoAtivar;
    @FXML
    private TableView<?> tabelaUsuarios;
    
        
    private List<String> obs = new ArrayList<>();
    private ObservableList<String> opcoes = FXCollections.observableArrayList();
    
    
    public void initialize() {
        nomeUsuario.setText("*nome do usuário*");
        
        botaoLimpar.setOnAction(event -> limparCampos());
        
        
    }    

    
    @FXML
    public void tipoFuncao(){
        obs.add("ADM");
        obs.add("Gestor");
        obs.add("Colaborador");
        opcoes.setAll(obs);
        selecaoFuncao.setItems(opcoes);
    }
    

    @FXML
    public void limparCampos(){
        selecaoFuncao.getSelectionModel().clearSelection();        
    }
    
    
    @FXML
    private void BotaoAdicionar(ActionEvent event) {
    }
}
