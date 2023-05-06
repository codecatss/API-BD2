package com.mycompany.api.bd2;

import com.mycompany.api.bd2.daos.clienteDAO;
import com.mycompany.api.bd2.daos.crDAO;
import com.mycompany.api.bd2.daos.horaDAO;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.text.ParseException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.collections.ObservableList;
import java.time.LocalDate;
import java.sql.Timestamp;
import com.mycompany.api.bd2.models.Hora;
import javafx.scene.control.Alert.AlertType;
//import daos.horaDAO;


import com.mycompany.api.bd2.models.*;


public class LancamentoColaboradorController {
    
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
    private Label Acionamento;
    @FXML
    private Label errodata;
    @FXML
    private Label  erroproj;
    @FXML
    private Button botaoSair;
    @FXML
    private TableView<Hora> tabelaLancamento;
    @FXML
    private TableColumn<?, ?> tabelaN;
    @FXML
    private TableColumn<Hora, String> tabelaTipo;
    @FXML
    private TableColumn<?, ?> tabelaStatus;
    @FXML
    private TableColumn<?, ?> tabelaInicio;
    @FXML
    private TableColumn<?, ?> tabelaFim;
    @FXML
    private TableColumn<?, ?> tabelaCR;
    @FXML
    private TableColumn<?, ?> tabelaCliente;
    @FXML
    private TableColumn<?, ?> tabelaProjeto;   
    @FXML
    private TableColumn<?,?> tabelaSolicitante;
    @FXML
    private TableColumn<?, ?> tabelaJustificativa;
    @FXML
    private Label errohoraI;
    @FXML
    private Label errohoraII;
    @FXML
    private DatePicker dataInicio;
    @FXML
    private DatePicker dataFim;
    @FXML
    private Spinner<Integer> horaInicio;
    @FXML
    private Spinner<Integer> minutoInicio;
    @FXML
    private Spinner<Integer> horaFim;
    @FXML
    private Spinner<Integer> minutoFim;
    @FXML
    private ComboBox<String> horaTipo;
    @FXML
    private ComboBox<String> selecaoCliente;
    @FXML
    private ComboBox<String> selecaoCR;
    @FXML
    private Button botaoAdicionar;
    @FXML
    private Button botaoLimpar;
    @FXML
    private TextField entradaProjeto;
    @FXML
    private TextField entradaJustificativa;
    @FXML
    private TextField entradaSolicitante;
    
    private String usuario = TelaLoginController.usuariologado.getUsername();
    @FXML
    private TextField entradaAcionamento;
    
    private List<String> obs = new ArrayList<>();
    private ObservableList<String> opcoes = FXCollections.observableArrayList();
    
    private List<String> cli = new ArrayList<>();
    private ObservableList<String> opCli = FXCollections.observableArrayList();
    
    private List<Hora> lishoras = new ArrayList<>();
    private ObservableList<Hora> observablelisthoras = FXCollections.observableArrayList();

    private List<String> centro_r = new ArrayList<>();
    private ObservableList<String> opCr = FXCollections.observableArrayList();
    
    public void initialize() {
        
        nomeUsuario.setText(usuario);
        
        horaInicio.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0, 1));
        minutoInicio.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
        horaInicio.getValueFactory().setWrapAround(true);
        minutoInicio.getValueFactory().setWrapAround(true);
        
        horaFim.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0, 1));
        minutoFim.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
        horaFim.getValueFactory().setWrapAround(true);
        minutoFim.getValueFactory().setWrapAround(true);
        
        botaoLimpar.setOnAction(event -> limparCampos());
        carregarTabelaLancamento();
        
        horaTipo.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
        if (newVal != null && newVal.equals(TipoHora.SOBREAVISO.name().toLowerCase())) {
            entradaAcionamento.setVisible(true);
            Acionamento.setVisible(true);
 
        } else {
            entradaAcionamento.setVisible(false);
            Acionamento.setVisible(false);
        }
        });
    }
    
    
    @FXML
    public void limparCampos(){
        limmparFormatacao();
        
        horaInicio.getValueFactory().setValue(0);
        minutoInicio.getValueFactory().setValue(0);
        horaFim.getValueFactory().setValue(0);
        minutoFim.getValueFactory().setValue(0);
        horaTipo.getSelectionModel().clearSelection();
        selecaoCliente.getSelectionModel().clearSelection();
        selecaoCR.getSelectionModel().clearSelection();
        entradaProjeto.clear();
        entradaJustificativa.clear();
        entradaSolicitante.clear();
        entradaAcionamento.clear();
        Acionamento.setVisible(false);
        
    }
    
    
    @FXML
    public void tipoHora() throws ParseException{
    obs.add("Hora-"+ TipoHora.EXTRA.name().toLowerCase());
    obs.add(TipoHora.SOBREAVISO.name().toLowerCase());
    opcoes.setAll(obs);
    horaTipo.setItems(opcoes);
    }
    
    
    private String erro = "-fx-border-color:#E06469";
    @FXML
    public void botaoAdicionar() {
        if(dataInicio.getValue()==null||horaInicio.getValue()==null||minutoInicio.getValue()==null||dataFim.getValue()==null||horaFim.getValue()==null||minutoFim.getValue()==null){
        System.out.println("Preencha todos os campos - tela de lançamento");
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Preencha todos os campos");
        alert.setHeaderText(null);
        alert.setContentText("Alguns dos campos não foi preenchido");
        alert.showAndWait();}
        else{
            boolean testedata = false;
            boolean salvar = true;
            boolean testeseq = false;
            if(dataInicio.getValue().isEqual(dataFim.getValue())){
                testedata = true;
                }
            else{
                if(dataFim.getValue().isBefore(dataInicio.getValue())){
                dataInicio.setStyle(erro);
                dataFim.setStyle(erro);
                errodata.setText("Data inválida");
                }
                else{
                    testeseq = true;
                }
            }
            if(testedata){
                if((horaFim.getValue()>horaInicio.getValue())||((horaInicio.getValue().equals(horaFim.getValue()))&&minutoFim.getValue()>minutoInicio.getValue())){
                    salvar = true;
                }else{
                    System.out.println("qubra aqui");
                    errohoraI.setText("Hora inválida");
                    errohoraII.setText("Hora inválida");
                    horaInicio.setStyle(erro);
                    minutoInicio.setStyle(erro);
                    horaFim.setStyle(erro);
                    minutoFim.setStyle(erro);


                }
            }
            else{
                if(testeseq){
                    salvar = true;
                }
            }
            
            if(salvar&&(!entradaProjeto.getText().isEmpty())){
                try{
                    LocalDate data_inicio = dataInicio.getValue();
                    int hora_inicio = horaInicio.getValue();
                    int min_inicio = minutoInicio.getValue();
                    String data_hora_inicio = data_inicio.getYear() + "-" + data_inicio.getMonthValue() + "-" + data_inicio.getDayOfMonth() + " " + hora_inicio + ":" + min_inicio + ":00";
                    Timestamp timestamp_inicio = Timestamp.valueOf(data_hora_inicio);

                    LocalDate data_fim = dataFim.getValue();            
                    int hora_fim = horaFim.getValue();
                    int min_fim = minutoFim.getValue();
                    String data_hora_fim = data_fim.getYear() + "-" + data_fim.getMonthValue() + "-" + data_fim.getDayOfMonth() + " " + hora_fim + ":" + min_fim + ":00";
                    Timestamp timestamp_fim = Timestamp.valueOf(data_hora_fim);
                    
                    String nome_cliente = selecaoCliente.getSelectionModel().getSelectedItem();
                    clienteDAO cliente = new clienteDAO();
                    
                    String nome_cr = selecaoCR.getSelectionModel().getSelectedItem();
                    crDAO cr = new crDAO();
                    
                    Hora hora = new Hora();
                    Cliente cli = new Cliente();
                    hora.setProjeto(entradaProjeto.getText());
                    hora.setCod_cr(cr.getCr(nome_cr).getCodigo_cr());
                    hora.setData_hora_inicio(timestamp_inicio.toString());
                    hora.setData_hora_fim(timestamp_fim.toString());
                    hora.setUsername_lancador(nomeUsuario.getText());
                    hora.setCnpj_cliente(cliente.getCliente(nome_cliente).getCnpj());
                    hora.setJustificativa_lancamento(entradaJustificativa.getText());
                    hora.setStatus_aprovacao("pendente");
                    hora.setTipo(horaTipo.getSelectionModel().getSelectedItem());
                    horaDAO hrDAO = new horaDAO();

                    hrDAO.save(hora);

                    System.out.println("Salvo");
                    carregarTabelaLancamento();

                }catch (Exception e){
                    System.out.println("Houve um erro ao salvar");
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Houve um erro ao salvar");
                    alert.setHeaderText(null);
                    alert.setContentText("O bloco 'try' responsavél por salvar a nova hora para o lançamento apresentou alguma falha");
                    alert.showAndWait();
                }  
            }else{
                if(entradaProjeto.getText().isEmpty()){
                    entradaProjeto.setStyle(erro);
                    erroproj.setText("Informe o projeto");
                }
            }
        }
        limparCampos();
         
    }
    
    
    @FXML
    public void carregarTabelaLancamento(){
        horaDAO horadao = new horaDAO();
        lishoras.clear();
        lishoras.addAll(horadao.getHora(usuario));
        observablelisthoras.setAll(lishoras);
        tabelaLancamento.setItems(observablelisthoras);
        
        tabelaN.setCellValueFactory(new PropertyValueFactory<>("id"));
        tabelaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        tabelaStatus.setCellValueFactory(new PropertyValueFactory<>("status_aprovacao")); 
        tabelaInicio.setCellValueFactory(new PropertyValueFactory<>("data_hora_inicio")); 
        tabelaFim.setCellValueFactory(new PropertyValueFactory<>("data_hora_fim")); 
        tabelaCR.setCellValueFactory(new PropertyValueFactory<>("centro_resultado")); 
        tabelaCR.setCellValueFactory(new PropertyValueFactory<>("cod_cr")); 
        tabelaCliente.setCellValueFactory(new PropertyValueFactory<>("nome_cliente"));
        tabelaJustificativa.setCellValueFactory(new PropertyValueFactory<>("justificativa_lancamento"));
        tabelaProjeto.setCellValueFactory(new PropertyValueFactory<>("projeto"));
        tabelaJustificativa.setCellValueFactory(new PropertyValueFactory<>("justificativa_lancamento"));
        tabelaSolicitante.setCellValueFactory(new PropertyValueFactory<>("solicitante"));

        tabelaLancamento.refresh();
        
    }
    
    @FXML
    public void limmparFormatacao(){
        dataInicio.getEditor().clear();
        dataInicio.setValue(null);
        dataFim.getEditor().clear();
        dataFim.setValue(null);

        
        errohoraI.setText(null);
        errohoraII.setText(null);
        
        erroproj.setText(null);
        entradaProjeto.setStyle(null);
        
        horaInicio.setStyle(null);
        minutoInicio.setStyle(null);
        horaFim.setStyle(null);
        minutoFim.setStyle(null);  
    }
    
    @FXML
    public void forneceCliente(){
        clienteDAO clienteDAO = new clienteDAO();
        cli.clear();
        for (Cliente cliente: clienteDAO.getClientes()){
            cli.add(cliente.getRazao_social());
        }
        opCli.setAll(cli);
        selecaoCliente.setItems(opCli);
    }
    
    @FXML
    public void forneceCR(){
        crDAO crDAO = new crDAO();
        centro_r.clear();
        for (Centro_resultado cr: crDAO.getCrs()){
            centro_r.add(cr.getNome());
        }
        opCr.setAll(centro_r);
        selecaoCR.setItems(opCr);
    }
}