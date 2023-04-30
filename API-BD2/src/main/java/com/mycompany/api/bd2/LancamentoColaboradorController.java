package com.mycompany.api.bd2;

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
    private TableColumn<?, ?> tabelaId;
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
    private ComboBox<String> selecaoAcionamento;
    @FXML
    private Button botaoAdicionar;
    @FXML
    private Button botaoLimpar;
    @FXML
    private TextField entradaProjeto;
    
    
    
    private List<String> obs = new ArrayList<>();
    private ObservableList<String> opcoes = FXCollections.observableArrayList();
    
    private List<String> cli = new ArrayList<>();
    private ObservableList<String> opCli = FXCollections.observableArrayList();

    public void initialize() {
        
        nomeUsuario.setText(Usuario.getInstance().getUsername());
        
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
            selecaoAcionamento.setVisible(true);
            Acionamento.setVisible(true);
 
        } else {
            selecaoAcionamento.setVisible(false);
            Acionamento.setVisible(false);
        }
        });
    }
    
    
    @FXML
    public void limparCampos(){
        dataInicio.setValue(null);
        dataFim.setValue(null);
        horaInicio.getValueFactory().setValue(0);
        minutoInicio.getValueFactory().setValue(0);
        horaFim.getValueFactory().setValue(0);
        minutoFim.getValueFactory().setValue(0);
        horaTipo.getSelectionModel().clearSelection();
        selecaoCliente.getSelectionModel().clearSelection();
        selecaoAcionamento.getSelectionModel().clearSelection();
        selecaoCR.getSelectionModel().clearSelection();
        entradaProjeto.clear();
        Acionamento.setVisible(false);
        
    }
    
    
    @FXML
    public void tipoHora() throws ParseException{
    obs.add("Hora "+ TipoHora.EXTRA.name().toLowerCase());
    obs.add(TipoHora.SOBREAVISO.name().toLowerCase());
    opcoes.setAll(obs);
    horaTipo.setItems(opcoes);
    }
    
    
    private String erro = "-fx-border-color:#E06469";
    @FXML
    public void botaoAdicionar() {
        dataInicio.setStyle(null);
        dataFim.setStyle(null);
        errodata.setText(null);
        
        errohoraI.setText(null);
        errohoraII.setText(null);
        
        erroproj.setText(null);
        entradaProjeto.setStyle(null);
        
        horaInicio.setStyle(null);
        minutoInicio.setStyle(null);
        horaFim.setStyle(null);
        minutoFim.setStyle(null);

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

                    Hora hora = Hora.getInstance();
                    hora.setProjeto(entradaProjeto.getText());
                    hora.setCod_cr(entradaProjeto.getText());
                    hora.setData_hora_inicio(timestamp_inicio.toString());
                    hora.setData_hora_fim(timestamp_fim.toString());
                    hora.setUsername_lancador(nomeUsuario.getText());
                    hora.setCnpj_cliente(987654321);
                    hora.setJustificativa_lancamento("Muita demanda");
                    hora.setStatus_aprovacao("pendente");

                    //hora.save(hora);

                    System.out.println("Foi");

                }catch (Exception e){
                    System.out.println("Houve um erro");
                }  
            }else{
                if(entradaProjeto.getText().isEmpty()){
                    entradaProjeto.setStyle(erro);
                    erroproj.setText("Informe o projeto");
                }
            }
        }                
         
    }
    
    private List<Hora> lishoras = new ArrayList<>();
    private ObservableList<Hora> observablelisthoras = FXCollections.observableArrayList();
    
    @FXML
    public void carregarTabelaLancamento(){
        horaDAO horadao = new horaDAO();
        /*essa é a config que eu usei pra exibir horas lançadas pelo user logado na tabela*/
        Usuario usuario = Usuario.getInstance();
        String nome = usuario.getUsername().toString();
        lishoras.addAll(horadao.getHora(nome));
        
        /*original*/
        /*lishoras.addAll(horadao.getHorasFromUser());*/
        observablelisthoras.setAll(lishoras);
        tabelaLancamento.setItems(observablelisthoras);

        tabelaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tabelaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo")); 
        tabelaStatus.setCellValueFactory(new PropertyValueFactory<>("status")); 
        tabelaStatus.setCellValueFactory(new PropertyValueFactory<>("status_aprovacao")); 
        tabelaDHInicio.setCellValueFactory(new PropertyValueFactory<>("data_hora_inicio")); 
        tabelaDHFim.setCellValueFactory(new PropertyValueFactory<>("data_hora_fim")); 
        tabelaCR.setCellValueFactory(new PropertyValueFactory<>("centro_resultado")); 
        tabelaCR.setCellValueFactory(new PropertyValueFactory<>("cod_cr")); 
        tabelaCliente.setCellValueFactory(new PropertyValueFactory<>("cliente")); 
        tabelaProjeto.setCellValueFactory(new PropertyValueFactory<>("projeto"));
        
    }
    
    @FXML
    public void forneceCliente(){
        cli.add("EMBRAER");
        cli.add("ITAU");
        cli.add("SAMSUNG");
        opCli.setAll(cli);
        selecaoCliente.setItems(opCli);
    }
}