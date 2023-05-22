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
import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LancamentoColaboradorController {

    @FXML
    private Button minimizarTela;
    @FXML
    private Label nometelaatual;
    @FXML
    private ImageView imagemUser;
    @FXML
    private Label nomeUsuario;
    private Label errodata;
    private Label erroproj;
    @FXML
    private Button botaoSair;
    private Button botaoAcionamento;
    private TableView<Hora> tabelaLancamento;
    private TableColumn<Hora, ?> tabelaN;
    private TableColumn<Hora, String> tabelaTipo;
    private TableColumn<Hora, ?> tabelaStatus;
    private TableColumn<Hora, ?> tabelaInicio;
    private TableColumn<Hora, ?> tabelaFim;
    private TableColumn<Hora, ?> tabelaCR;
    private TableColumn<Hora, ?> tabelaCliente;
    private TableColumn<Hora, ?> tabelaProjeto;
    private TableColumn<Hora, ?> tabelaSolicitante;
    private TableColumn<Hora, ?> tabelaJustificativa;
    private Label errohoraI;
    private Label errohoraII;
    private DatePicker dataInicio;
    private DatePicker dataFim;
    private Spinner<Integer> horaInicio;
    private Spinner<Integer> minutoInicio;
    private Spinner<Integer> horaFim;
    private Spinner<Integer> minutoFim;
    private ComboBox<String> horaTipo;
    private ComboBox<String> selecaoCliente;
    private ComboBox<String> selecaoCR;
    private Button botaoLimpar;
    private TextField entradaProjeto;
    private TextField entradaJustificativa;
    private TextField entradaSolicitante;
    private Button menuLancamento;
    private Button menuApontamento;
    @FXML
    private Button menuRelatorio;

    private String usuario = TelaLoginController.usuariologado.getUsername();

    private List<String> obs = new ArrayList<>();
    private ObservableList<String> opcoes = FXCollections.observableArrayList();

    private List<String> cli = new ArrayList<>();
    private ObservableList<String> opCli = FXCollections.observableArrayList();

    private List<Hora> lishoras = new ArrayList<>();
    private ObservableList<Hora> observablelisthoras = FXCollections.observableArrayList();

    private List<String> centro_r = new ArrayList<>();
    private ObservableList<String> opCr = FXCollections.observableArrayList();
    @FXML
    private Button fecharTela;
    @FXML
    private AnchorPane menuADM;
    @FXML
    private Button menuUsuario;
    @FXML
    private Button menuCR;
    @FXML
    private Button menuCliente;
    @FXML
    private Button menuAprovar;
    @FXML
    private TableView<?> tabelaAprovacao;
    @FXML
    private TableColumn<?, ?> colunaColaboradorADM;
    @FXML
    private TableColumn<?, ?> colunaCRADM;
    @FXML
    private TableColumn<?, ?> colundaGestorADM;
    @FXML
    private TableColumn<?, ?> colunaEmpresaADM;
    @FXML
    private TableColumn<?, ?> colunaProjetoADM;
    @FXML
    private TableColumn<?, ?> colunaFuncaoADM;
    @FXML
    private TableColumn<?, ?> colunaInicioADM;
    @FXML
    private TableColumn<?, ?> colunaFimADM;
    @FXML
    private TableColumn<?, ?> colunaJustificativaADM;
    @FXML
    private Button botaoReprovar;
    @FXML
    private Button botaoAprovar;

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
        
        botaoAcionamento.setVisible(false);
        
        horaTipo.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
        if (newVal != null && newVal.equals("Sobreaviso")) {
            botaoAcionamento.setVisible(true);
 
        } else {
            botaoAcionamento.setVisible(false);
        }

        });

        if (TelaLoginController.usuariologado.getCargo().equals("gestor")) {
            menuLancamento.setVisible(true);
            menuLancamento.setDisable(true);
            menuApontamento.setVisible(true);
            menuRelatorio.setVisible(true);
        } else {
            menuLancamento.setVisible(false);
            menuApontamento.setVisible(false);
            menuRelatorio.setVisible(false);
        }
    }

    public void limparCampos() {
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
        botaoAcionamento.setVisible(false);
    }

    public void tipoHora() throws ParseException {
        obs.add("Hora extra");
        obs.add("Sobreaviso");
        opcoes.setAll(obs);
        horaTipo.setItems(opcoes);
    }

    private String erro = "-fx-border-color:#E06469";

    public void botaoAdicionar() throws ParseException {
        if (dataInicio.getValue() == null || horaInicio.getValue() == null || minutoInicio.getValue() == null || dataFim.getValue() == null || horaFim.getValue() == null || minutoFim.getValue() == null) {
            System.out.println("Preencha todos os campos - tela de lançamento");
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Preencha todos os campos");
            alert.setHeaderText(null);
            alert.setContentText("Alguns dos campos não foi preenchido");
            alert.showAndWait();
        } else {
            boolean testedata = false;
            boolean salvar = true;
            boolean testeseq = false;
            if (dataInicio.getValue().isEqual(dataFim.getValue())) {
                testedata = true;
            } else {
                if (dataFim.getValue().isBefore(dataInicio.getValue())) {
                    dataInicio.setStyle(erro);
                    dataFim.setStyle(erro);
                    errodata.setText("Data inválida");
                } else {
                    testeseq = true;
                }
            }
            if (testedata) {
                if ((horaFim.getValue() > horaInicio.getValue()) || ((horaInicio.getValue().equals(horaFim.getValue())) && minutoFim.getValue() > minutoInicio.getValue())) {
                    salvar = true;
                } else {
                    errohoraI.setText("Hora inválida");
                    errohoraII.setText("Hora inválida");
                    horaInicio.setStyle(erro);
                    minutoInicio.setStyle(erro);
                    horaFim.setStyle(erro);
                    minutoFim.setStyle(erro);

                }
            } else {
                if (testeseq) {
                    salvar = true;
                }
            }

            if (salvar && (!entradaProjeto.getText().isEmpty())) {
                //try {
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
                    if(TelaLoginController.usuariologado.getCargo().equalsIgnoreCase("gestor")){
                        hora.setStatus_aprovacao("aprovado_gestor");
                    }else{
                    hora.setStatus_aprovacao("pendente");}
                    hora.setSolicitante(entradaSolicitante.getText());
                    hora.setTipo(horaTipo.getSelectionModel().getSelectedItem().toUpperCase());
                    horaDAO hrDAO = new horaDAO();

                    hrDAO.save(hora);

                    System.out.println("Salvo");
                    carregarTabelaLancamento();

                /*} catch (Exception e) {
                    System.out.println("Houve um erro ao salvar");
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Houve um erro ao salvar");
                    alert.setHeaderText(null);
                    alert.setContentText("O bloco 'try' responsavél por salvar a nova hora para o lançamento apresentou alguma falha");
                    alert.showAndWait();
                }*/
            } else {
                if (entradaProjeto.getText().isEmpty()) {
                    entradaProjeto.setStyle(erro);
                    erroproj.setText("Informe o projeto");
                }
            }
        }
        limparCampos();

    }

    public void carregarTabelaLancamento() {
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

    public void limmparFormatacao() {
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

    public void forneceCliente() {
        clienteDAO clienteDAO = new clienteDAO();
        cli.clear();
        for (Cliente cliente : clienteDAO.getClientes()) {
            cli.add(cliente.getRazao_social());
        }
        opCli.setAll(cli);
        selecaoCliente.setItems(opCli);
    }

    public void forneceCR() {
        crDAO crDAO = new crDAO();
        centro_r.clear();
        for (Centro_resultado cr : crDAO.getCrs()) {
            centro_r.add(cr.getNome());
        }
        opCr.setAll(centro_r);
        selecaoCR.setItems(opCr);
    }

    private void BotaoSair(ActionEvent event) throws IOException {
        Usuario usuario = new Usuario();
        usuario.logout();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaLogin.fxml"));
        Parent root = loader.load();
        Scene cena = new Scene(root);
        Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        stage.setScene(cena);
        stage.show();
    }

    public void botaoExit() {
        Platform.exit();
    }

    public void navApontamentoGestor(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ApontamentoGestor.fxml"));
        Parent root = loader.load();
        Scene cena = new Scene(root);
        Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        stage.setScene(cena);
        stage.show();
    }
}