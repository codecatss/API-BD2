/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.api.bd2;

import com.mycompany.api.bd2.daos.clienteDAO;
import com.mycompany.api.bd2.models.Cliente;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
public class CadastroClienteADMController {

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
    private TextField entradaCNPJ;
    @FXML
    private TextField entradaRS;
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
    private TableView<?> tabelaCadastroCliente;
    @FXML
    private TableColumn<?, ?> colunaCNPJ;
    @FXML
    private TableColumn<?, ?> colunaRS;
    
    
    private List<String> obs = new ArrayList<>();
    private ObservableList<String> opcoes = FXCollections.observableArrayList();
    
    
    public void initialize() {
        nomeUsuario.setText("*nome do usuário*");
        
        botaoLimpar.setOnAction(event -> limparCampos());
    }
  

    @FXML
    private void BotaoAdicionar(ActionEvent event) {
        System.out.println("botão adicionar");
        if(entradaCNPJ.getText().isEmpty() || entradaRS.getText().isEmpty()){        
        System.out.println("Preencha todos os campos - cadastro de cliente");
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Preencha todos os campos");
        alert.setHeaderText(null);
        alert.setContentText("Alguns dos campos não foi preenchido");
        alert.showAndWait();
        }
        else {
        Cliente cliente = new Cliente();
        cliente.setCnpj(Long.parseLong(entradaCNPJ.getText()));
        System.out.println("set cnpj ok");
        cliente.setRazao_social(entradaRS.getText());
        System.out.println("set rs ok");
        cliente.setStatus_cliente("ativo");
        System.out.println("set status ok");
        
        clienteDAO clienteDao = new clienteDAO();
        clienteDao.save(cliente);
        System.out.println("save ok");
        }
    }

    
    @FXML
    public void limparCampos(){     
        System.out.println("botão limpar");
        entradaCNPJ.clear();
        entradaRS.clear();
    }
    
    @FXML
    private void BotaoEditar(ActionEvent event) {
        System.out.println("botão editar");
    }

    @FXML
    private void BotaoInativar(ActionEvent event) {
        System.out.println("botão inativar");
    }

    @FXML
    private void BotaoAtivar(ActionEvent event) {
        System.out.println("botão ativar");
    }
        @FXML
    private void BotaoFechar(ActionEvent event) {
        System.out.println("botão sair");
    }
}



        
        

    
    
 