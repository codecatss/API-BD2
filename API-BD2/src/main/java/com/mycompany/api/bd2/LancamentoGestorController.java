package com.mycompany.api.bd2;

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
//import daos.horaDAO;


import com.mycompany.api.bd2.models.*;


public class LancamentoGestorController {

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
    private Button botaoSair;
    @FXML
    private TableView<?> tabelaLancamento;
    @FXML
    private TableColumn<?, ?> tabelaN;
    @FXML
    private TableColumn<?, ?> tabelaTipo;
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
    private TableColumn<?, ?> tabelaJustificativa;
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
    @FXML
    private TextField entradaJustificativa;
    @FXML
    private Button menuLancamento;
    @FXML
    private Button menuApontamento;
    @FXML
    private Button menuRelatorio;
    
    
    private List<String> obs = new ArrayList<>();
    private ObservableList<String> opcoes = FXCollections.observableArrayList();
    
    private List<String> cli = new ArrayList<>();
    private ObservableList<String> opCli = FXCollections.observableArrayList();
    
    private List<Hora> lishoras = new ArrayList<>();
    private ObservableList<Hora> observablelisthoras = FXCollections.observableArrayList();
    

    public void initialize() {
        nomeUsuario.setText("*nome do usuário*");
        
        horaInicio.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0, 1));
        minutoInicio.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
        horaInicio.getValueFactory().setWrapAround(true);
        minutoInicio.getValueFactory().setWrapAround(true);
        
        horaFim.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0, 1));
        minutoFim.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
        horaFim.getValueFactory().setWrapAround(true);
        minutoFim.getValueFactory().setWrapAround(true);
        
        botaoLimpar.setOnAction(event -> limparCampos());
        //carregarTabelaLancamento();
        
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
        entradaJustificativa.clear();
        Acionamento.setVisible(false);
    }
    
    
    @FXML
    public void tipoHora() throws ParseException{
    obs.add("Hora "+ TipoHora.EXTRA.name().toLowerCase());
    obs.add(TipoHora.SOBREAVISO.name().toLowerCase());
    opcoes.setAll(obs);
    horaTipo.setItems(opcoes);
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
