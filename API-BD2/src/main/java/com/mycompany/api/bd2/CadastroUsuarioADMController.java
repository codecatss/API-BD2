/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.api.bd2;

import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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
    private Label nomeUsuario;
    @FXML
    private Button botaoSair;
    @FXML
    private Button menuUsuario;
    @FXML
    private Button menuCR;
    @FXML
    private Button menuCliente;
    @FXML
    private Button menuAprovar;
    @FXML
    private Button menuRelatorio;
    @FXML
    private TextField entradaNome;
    @FXML
    private TextField entradaMatricula;
    @FXML
    private TextField entradaUsername;
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
    private TableView<?> tabelaCadastroUsuarios;
    @FXML
    private TableColumn<?, ?> colunaNome;
    @FXML
    private TableColumn<?, ?> colunaUsername;
    @FXML
    private TableColumn<?, ?> colunaFuncao;
    @FXML
    private TableColumn<?, ?> colunaMatricula;
    
    
    private List<String> obs = new ArrayList<>();
    private ObservableList<String> opcoes = FXCollections.observableArrayList();
    
    
    
    
    
    public void initialize() {
        nomeUsuario.setText("*nome do usuário*");
        
        botaoLimpar.setOnAction(event -> limparCampos());
    }
  

    @FXML
    private void BotaoAdicionar(ActionEvent event) {
    }

    
    @FXML
    public void limparCampos(){
        selecaoFuncao.getSelectionModel().clearSelection();        
    }
    
    
    @FXML
    public void tipoFuncao(){
        obs.add("Administrador");
        obs.add("Gestor");
        obs.add("Colaborador");
        opcoes.setAll(obs);
        selecaoFuncao.setItems(opcoes);
    }
    
}



        
        

    
    
 