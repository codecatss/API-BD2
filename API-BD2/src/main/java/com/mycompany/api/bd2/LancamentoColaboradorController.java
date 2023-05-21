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
import java.util.LinkedList;
import java.util.Set;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LancamentoColaboradorController {

    @FXML
    private Button FecharTela;
    @FXML
    private Button minimizarTela;
    @FXML
    private Label nometelaatual;
    @FXML
    private ImageView imagemUser;
    @FXML
    private Label nomeUsuario;
    @FXML
    private Label errodata;
    @FXML
    private Label erroproj;
    @FXML
    private Button botaoSair;
    @FXML
    private Button botaoAcionamento;
    @FXML
    private TableView<Hora> tabelaLancamento;
    @FXML
    private TableColumn<Hora, ?> tabelaN;
    @FXML
    private TableColumn<Hora, String> tabelaTipo;
    @FXML
    private TableColumn<Hora, ?> tabelaStatus;
    @FXML
    private TableColumn<Hora, ?> tabelaInicio;
    @FXML
    private TableColumn<Hora, ?> tabelaFim;
    @FXML
    private TableColumn<Hora, ?> tabelaCR;
    @FXML
    private TableColumn<Hora, ?> tabelaCliente;
    @FXML
    private TableColumn<Hora, ?> tabelaProjeto;
    @FXML
    private TableColumn<Hora, ?> tabelaSolicitante;
    @FXML
    private TableColumn<Hora, ?> tabelaJustificativa;
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
    @FXML
    private Button menuLancamento;
    @FXML
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
    private boolean opened;

    public void initialize() {

        getNomeUsuario().setText(usuario);

        getHoraInicio().setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0, 1));
        getMinutoInicio().setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
        getHoraInicio().getValueFactory().setWrapAround(true);
        getMinutoInicio().getValueFactory().setWrapAround(true);

        getHoraFim().setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0, 1));
        getMinutoFim().setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
        getHoraFim().getValueFactory().setWrapAround(true);
        getMinutoFim().getValueFactory().setWrapAround(true);

        botaoLimpar.setOnAction(event -> limparCampos());
        carregarTabelaLancamento();
        
        botaoAcionamento.setVisible(false);
        
        getHoraTipo().getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
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

    @FXML
    public void limparCampos() {
        limmparFormatacao();

        getHoraInicio().getValueFactory().setValue(0);
        getMinutoInicio().getValueFactory().setValue(0);
        getHoraFim().getValueFactory().setValue(0);
        getMinutoFim().getValueFactory().setValue(0);
        getHoraTipo().getSelectionModel().clearSelection();
        getSelecaoCliente().getSelectionModel().clearSelection();
        getSelecaoCR().getSelectionModel().clearSelection();
        entradaProjeto.clear();
        getEntradaJustificativa().clear();
        getEntradaSolicitante().clear();
        botaoAcionamento.setVisible(false);
    }

    @FXML
    public void tipoHora() throws ParseException {
        obs.add("Hora extra");
        obs.add("Sobreaviso");
        opcoes.setAll(obs);
        getHoraTipo().setItems(opcoes);
    }

    private String erro = "-fx-border-color:#E06469";

    @FXML
    public void botaoAdicionar() throws ParseException {
        if (getDataInicio().getValue() == null || getHoraInicio().getValue() == null || getMinutoInicio().getValue() == null || getDataFim().getValue() == null || getHoraFim().getValue() == null || getMinutoFim().getValue() == null) {
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
            if (getDataInicio().getValue().isEqual(getDataFim().getValue())) {
                testedata = true;
            } else {
                if (getDataFim().getValue().isBefore(getDataInicio().getValue())) {
                    getDataInicio().setStyle(erro);
                    getDataFim().setStyle(erro);
                    errodata.setText("Data inválida");
                } else {
                    testeseq = true;
                }
            }
            if (testedata) {
                if ((getHoraFim().getValue() > getHoraInicio().getValue()) || ((getHoraInicio().getValue().equals(getHoraFim().getValue())) && getMinutoFim().getValue() > getMinutoInicio().getValue())) {
                    salvar = true;
                } else {
                    errohoraI.setText("Hora inválida");
                    errohoraII.setText("Hora inválida");
                    getHoraInicio().setStyle(erro);
                    getMinutoInicio().setStyle(erro);
                    getHoraFim().setStyle(erro);
                    getMinutoFim().setStyle(erro);

                }
            } else {
                if (testeseq) {
                    salvar = true;
                }
            }

            if (salvar && (!entradaProjeto.getText().isEmpty())) {
                List<Hora> lancar = new LinkedList<>();
                lancar.add(capturaHora());
                
                lancar.addAll(PopUpAcionamentoController.getAcionamentos());
                
                for (Hora hora : lancar){
                    horaDAO hrDAO = new horaDAO();
                    hrDAO.save(hora);
                }
            } else {
                if (entradaProjeto.getText().isEmpty()) {
                    entradaProjeto.setStyle(erro);
                    erroproj.setText("Informe o projeto");
                }
            }
        }
        
        opened = false;
        limparCampos();

    }
    
    private static Hora hora = new Hora();
    public static Hora getHora(){
        return hora;
    }
    
    public Hora capturaHora(){
        hora.beNull();
        try {
            LocalDate data_inicio = getDataInicio().getValue();
            int hora_inicio = TimeData.getInstance().getHoraInicio();
            int min_inicio = TimeData.getInstance().getMinutoInicio();
            String data_hora_inicio = data_inicio.getYear() + "-" + data_inicio.getMonthValue() + "-" + data_inicio.getDayOfMonth() + " " + hora_inicio + ":" + min_inicio + ":00";
            Timestamp timestamp_inicio = Timestamp.valueOf(data_hora_inicio);

            LocalDate data_fim = getDataFim().getValue();
            int hora_fim = TimeData.getInstance().getHoraFim();
            int min_fim = TimeData.getInstance().getMinutoFim();
            String data_hora_fim = data_fim.getYear() + "-" + data_fim.getMonthValue() + "-" + data_fim.getDayOfMonth() + " " + hora_fim + ":" + min_fim + ":00";
            Timestamp timestamp_fim = Timestamp.valueOf(data_hora_fim);
            hora.setData_hora_inicio(timestamp_inicio.toString());
            hora.setData_hora_fim(timestamp_fim.toString());

            hora.setData_hora_inicio(timestamp_inicio.toString());
            hora.setData_hora_fim(timestamp_fim.toString());

            String nome_cliente = getSelecaoCliente().getSelectionModel().getSelectedItem();
            clienteDAO cliente = new clienteDAO();

            String nome_cr = getSelecaoCR().getSelectionModel().getSelectedItem();
            crDAO cr = new crDAO();


            Cliente cli = new Cliente();
            hora.setProjeto(entradaProjeto.getText());
            hora.setCod_cr(cr.getCr(nome_cr).getCodigo_cr());

            hora.setUsername_lancador(getNomeUsuario().getText());
            hora.setCnpj_cliente((int) cliente.getCliente(nome_cliente).getCnpj());
            hora.setJustificativa_lancamento(getEntradaJustificativa().getText());
            if(TelaLoginController.usuariologado.getCargo().equalsIgnoreCase("gestor")){
                hora.setStatus_aprovacao("aprovado_gestor");
            }else{
            hora.setStatus_aprovacao("pendente");}
            hora.setSolicitante(getEntradaSolicitante().getText());
            hora.setTipo(getHoraTipo().getSelectionModel().getSelectedItem().toUpperCase());
            
            carregarTabelaLancamento();

        } catch (Exception e) {
            System.out.println("Houve um erro ao salvar");
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Houve um erro ao salvar");
            alert.setHeaderText(null);
            alert.setContentText("O bloco 'try' responsavél por salvar a nova hora para o lançamento apresentou alguma falha");
            alert.showAndWait();
        }
        return hora;
    }
        
    @FXML
    public void botaoAcionamento(ActionEvent event) throws ParseException{
        capturaHora();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PopUpAcionamento.fxml"));
            //PopUpAcionamentoController controller = new PopUpAcionamentoController( this, dataInicio, dataFim, horaTipo, selecaoCliente, selecaoCR, entradaProjeto, entradaSolicitante, nomeUsuario); // Passa as referências dos objetos DatePicker
            Parent root = loader.load();
            PopUpAcionamentoController controller = new PopUpAcionamentoController(); // Passa as referências dos objetos DatePicker

            loader.setController(controller);
            Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.initOwner(botaoAcionamento.getScene().getWindow());
            popup.setScene(new Scene(root));

            popup.showAndWait();
            

       // Obtenha o objeto Hora preenchido na PopUp

        } catch (IOException e) {

        }

    }
    
    @FXML
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
    

    
     public void atualizarInformacoes() {
         System.out.println(TimeData.getInstance().getHoraInicio());
        // ...
    }

    @FXML
    public void limmparFormatacao() {
        getDataInicio().getEditor().clear();
        getDataInicio().setValue(null);
        getDataFim().getEditor().clear();
        getDataFim().setValue(null);

        errohoraI.setText(null);
        errohoraII.setText(null);

        erroproj.setText(null);
        entradaProjeto.setStyle(null);

        getHoraInicio().setStyle(null);
        getMinutoInicio().setStyle(null);
        getHoraFim().setStyle(null);
        getMinutoFim().setStyle(null);
    }

    @FXML
    public void forneceCliente() {
        clienteDAO clienteDAO = new clienteDAO();
        cli.clear();
        for (Cliente cliente : clienteDAO.getClientes()) {
            cli.add(cliente.getRazao_social());
        }
        opCli.setAll(cli);
        getSelecaoCliente().setItems(opCli);
    }

    @FXML
    public void forneceCR() {
        crDAO crDAO = new crDAO();
        centro_r.clear();
        for (Centro_resultado cr : crDAO.getCrs()) {
            centro_r.add(cr.getNome());
        }
        opCr.setAll(centro_r);
        getSelecaoCR().setItems(opCr);
    }

    @FXML
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

    @FXML
    public void botaoExit() {
        Platform.exit();
    }

    @FXML
    public void navApontamentoGestor(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ApontamentoGestor.fxml"));
        Parent root = loader.load();
        Scene cena = new Scene(root);
        Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        stage.setScene(cena);
        stage.show();
    }

    /**
     * @return the nomeUsuario
     */
    public Label getNomeUsuario() {
        return nomeUsuario;
    }

    /**
     * @return the dataInicio
     */
    public DatePicker getDataInicio() {
        return dataInicio;
    }

    /**
     * @return the dataFim
     */
    public DatePicker getDataFim() {
        return dataFim;
    }

    /**
     * @return the horaInicio
     */
    public Spinner<Integer> getHoraInicio() {
        return horaInicio;
    }

    /**
     * @return the minutoInicio
     */
    public Spinner<Integer> getMinutoInicio() {
        return minutoInicio;
    }

    /**
     * @return the horaFim
     */
    public Spinner<Integer> getHoraFim() {
        return horaFim;
    }

    /**
     * @return the minutoFim
     */
    public Spinner<Integer> getMinutoFim() {
        return minutoFim;
    }

    /**
     * @return the horaTipo
     */
    public ComboBox<String> getHoraTipo() {
        return horaTipo;
    }

    /**
     * @return the selecaoCliente
     */
    public ComboBox<String> getSelecaoCliente() {
        return selecaoCliente;
    }

    /**
     * @return the selecaoCR
     */
    public ComboBox<String> getSelecaoCR() {
        return selecaoCR;
    }

    /**
     * @return the entradaJustificativa
     */
    public TextField getEntradaJustificativa() {
        return entradaJustificativa;
    }

    /**
     * @return the entradaSolicitante
     */
    public TextField getEntradaSolicitante() {
        return entradaSolicitante;
    }
}