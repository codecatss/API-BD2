/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.api.bd2;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import models.TipoHora;
import javafx.collections.ObservableList;



public class LancamentoColaboradorController {
    
    @FXML
    private VBox node;
    @FXML
    private ImageView iconeUsuario;
    @FXML
    private Label label_usuario;
    @FXML
    private Label CR_FUNCAO;
    @FXML
    private Button mudarCR;
    @FXML
    private Button botaoSair;
    @FXML
    private TableView<?> tabelaLancamento;
    @FXML
    private TableColumn<?, ?> tabelaId;
    @FXML
    private TableColumn<?, ?> tabelaNome;
    @FXML
    private TableColumn<?, ?> tabelaTipo;
    @FXML
    private TableColumn<?, ?> tabelaStatus;
    @FXML
    private TableColumn<?, ?> tabelaDHInicio;
    @FXML
    private TableColumn<?, ?> tabelaDHFim;
    @FXML
    private TableColumn<?, ?> tabelaCR;
    @FXML
    private TableColumn<?, ?> tabelaCliente;
    @FXML
    private TableColumn<?, ?> tabelaProjeto;
    @FXML
    private TableColumn<?, ?> tabelaJustificativa;
    @FXML
    private TableColumn<?, ?> tabelaResp;
    @FXML
    private DatePicker dataInicio;
    @FXML
    private Spinner<?> horaInicio;
    @FXML
    private Spinner<?> minutoInicio;
    @FXML
    private ComboBox<?> selecaoJustificativa;
    @FXML
    private ComboBox<String> tipo_funcao;
    @FXML
    private TextField stringProjeto;
    @FXML
    private ComboBox<?> selecaoCliente;
    @FXML
    private DatePicker dataFim;
    @FXML
    private Spinner<?> horaFim;
    @FXML
    private Spinner<?> minutoFim;
    @FXML
    private Button botaoAdicionar;
    @FXML
    private Button botaoLimpar;

    private List<String> obs = new ArrayList<>();
    private String a = "a";
    private ObservableList<String> opcoes = FXCollections.observableArrayList();

@FXML
public void BotaoAdicionar() {
    label_usuario.setText("oi");
    System.out.println("foi");
}
    
    @FXML
    public void tipoFuncao(){
    obs.add("Hora"+ TipoHora.EXTRA.name().toLowerCase());
    obs.add(TipoHora.SOBREAVISO.name().toLowerCase());
    opcoes.setAll(obs);
    tipo_funcao.setItems(opcoes);
    }
    
}
