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
import models.Hora;



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
    /*horaDAO horaDao = new horaDAO();
    Hora hora = new Hora();*/
@FXML
public void BotaoAdicionar() throws ParseException {
    label_usuario.setText("oi");
    System.out.println("foi");
    /*horaDao.save(hora);*/
}
    
    @FXML
    public void tipoFuncao() throws ParseException{
    obs.add("Hora "+ TipoHora.EXTRA.name().toLowerCase());
    obs.add(TipoHora.SOBREAVISO.name().toLowerCase());
    opcoes.setAll(obs);
    tipo_funcao.setItems(opcoes);
    /*hora.setUsername_lancador("Geniscleita");
    hora.setData_hora_inicio("2023-02-12 13:34:01");
    hora.setData_hora_fim("2023-02-12 13:34:01");
    hora.setTipo(obs.get(1));
    hora.setCentro_resultado("euffdg");
    hora.setJustificativa("just");
    hora.setProjeto("trampos");*/
    }
    
    
    
}
