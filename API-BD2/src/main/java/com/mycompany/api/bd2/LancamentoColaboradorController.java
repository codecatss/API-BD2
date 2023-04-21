/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.api.bd2;

import daos.horaDAO;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.collections.ObservableList;
import models.Hora;

import models.*;



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
    private Spinner<Integer> horaInicio;
    @FXML
    private Spinner<Integer> minutoInicio;
 
    private ComboBox<String> selecaoJustificativa;
    @FXML
    private ComboBox<String> tipo_funcao;
    @FXML
    private TextField stringProjeto;
    @FXML
    private ComboBox<String> selecaoCliente;
    @FXML
    private DatePicker dataFim;
    @FXML
    private Spinner<Integer> horaFim;
    @FXML
    private Spinner<Integer> minutoFim;
    @FXML
    private Button botaoAdicionar;
    @FXML
    private Button botaoLimpar;

    private List<String> obs = new ArrayList<>();
    private ObservableList<String> opcoes = FXCollections.observableArrayList();
    
    private List<String> cli = new ArrayList<>();
    private ObservableList<String> opCli = FXCollections.observableArrayList();
    
        public void initialize() {
        minutoInicio.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
        horaInicio.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 24));
        minutoFim.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
        horaFim.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 24));
    }

    @FXML
    public void BotaoAdicionar() {
        label_usuario.setText("*nome do usuário*");
        System.out.println("foi");
    }
    
    @FXML
    public void tipoFuncao() throws ParseException{
    obs.add("Hora "+ TipoHora.EXTRA.name().toLowerCase());
    obs.add(TipoHora.SOBREAVISO.name().toLowerCase());
    opcoes.setAll(obs);
    tipo_funcao.setItems(opcoes);
    }
    
    @FXML
    public void forneceCliente(){
        cli.add("EMBRAER");
        cli.add("ITAU");
        cli.add("SANSUNG");
        opCli.setAll(cli);
        selecaoCliente.setItems(opCli); 
    }
    
    @FXML
    public void limpaCampos(){
        dataInicio.setValue(null);
        dataFim.setValue(null);
        selecaoCliente.getSelectionModel().clearSelection();
        tipo_funcao.getSelectionModel().clearSelection();
        minutoInicio.getValueFactory().setValue(null);
        horaInicio.getValueFactory().setValue(null);
        minutoFim.getValueFactory().setValue(null);
        horaFim.getValueFactory().setValue(null);
    }
}
