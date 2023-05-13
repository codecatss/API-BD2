/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.api.bd2;

import com.mycompany.api.bd2.daos.clienteDAO;
import com.mycompany.api.bd2.daos.crDAO;
import com.mycompany.api.bd2.daos.horaDAO;
import com.mycompany.api.bd2.models.Cliente;
import com.mycompany.api.bd2.models.Hora;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author conta
 */
public class PopUpAcionamentoController implements Initializable {

    @FXML
    private Button fecharTela;
    @FXML
    private Button minimizarTela;
    @FXML
    private Label nometelaatual;
    @FXML
    private Spinner<Integer> horaInicio;
    @FXML
    private Spinner<Integer> minutoInicio;
    @FXML
    private Spinner<Integer> horaFim;
    @FXML
    private Spinner<Integer> minutoFim;
    @FXML
    private TableView<Integer> tabelaAcionamento;
    @FXML
    private TableColumn<?, ?> colunaAcionamento;
    @FXML
    private TableColumn<?, ?> colunaInicio;
    @FXML
    private TableColumn<?, ?> colunaFim;
    @FXML
    private Button botaoSalvar;
    @FXML
    private Button botaoExcluir;
    @FXML
    private Button botaoAdicionar;
    @FXML
    private Label errohoraI;
    @FXML
    private Label errohoraII;

    /**
     * Initializes the controller class.
     */
    private List<Integer> horas = new LinkedList<>();
    private List<Integer> lishoras = new ArrayList<>();
     
    private DatePicker dataInicio;
    private DatePicker dataFim;
    private ComboBox<String> selecaoCliente;
    private ComboBox<String> selecaoCR;
    private TextField entradaProjeto;
    private TextField entradaJustificativa;
    private TextField entradaSolicitante;
    private Label nomeUsuario;
    

    /*public PopUpAcionamentoController(DatePicker dataInicio, DatePicker dataFim, ComboBox<String> selecaoCliente, ComboBox<String> selecaoCR, TextField entradaProjeto, TextField entradaJustificativa, TextField entradaSolicitante, Label nomeUsuario) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.selecaoCliente = selecaoCliente;
        this.selecaoCR = selecaoCR;
        this.entradaProjeto = entradaProjeto;
        this.entradaJustificativa = entradaJustificativa;
        this.entradaSolicitante = entradaSolicitante;
        this.nomeUsuario = nomeUsuario;
    }*/
     
    private ObservableList<Integer> observablelisthoras = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        horaInicio.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0, 1));
        minutoInicio.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
        horaInicio.getValueFactory().setWrapAround(true);
        minutoInicio.getValueFactory().setWrapAround(true);

        horaFim.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0, 1));
        minutoFim.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
        horaFim.getValueFactory().setWrapAround(true);
        minutoFim.getValueFactory().setWrapAround(true);

        //botaoLimpar.setOnAction(event -> limparCampos());
    }    

    @FXML
    private void botaoAdicionar(ActionEvent event) throws ParseException {
        if (horaInicio.getValue() == 0 || minutoInicio.getValue() == 0 || horaFim.getValue() == 0 || minutoFim.getValue() == null) {
            System.out.println("Preencha todos os campos - tela de lançamento");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Preencha todos os campos");
            alert.setHeaderText(null);
            alert.setContentText("Alguns dos campos não foi preenchido");
            alert.showAndWait();
        } else {
            boolean testedata = false;
            boolean salvar = true;
            boolean testeseq = false;
            
            if (testedata) {
                if ((horaFim.getValue() > horaInicio.getValue()) || ((horaInicio.getValue().equals(horaFim.getValue())) && minutoFim.getValue() > minutoInicio.getValue())) {
                    salvar = true;
                } else {
                    errohoraI.setText("Hora inválida");
                    errohoraII.setText("Hora inválida");

                }
            } else {
                if (testeseq) {
                    salvar = true;
                }
          
            }
            if(salvar){
                 
                 
            }
        }
    }
    
    public Hora getHora() throws ParseException {
        Hora hora = new Hora();
        LocalDate data_inicio = dataInicio.getValue();
        int hora_inicio = horaInicio.getValue();
        int min_inicio = minutoInicio.getValue();
        String data_hora_inicio = data_inicio.getYear() + "-" + data_inicio.getMonthValue() + "-" + data_inicio.getDayOfMonth() + " " + hora_inicio + ":" + min_inicio + ":00";
        Timestamp timestamp_inicio = Timestamp.valueOf(data_hora_inicio);
        hora.setData_hora_inicio(timestamp_inicio.toString());

        LocalDate data_fim = dataFim.getValue();
        int hora_fim = horaFim.getValue();
        int min_fim = minutoFim.getValue();
        String data_hora_fim = data_fim.getYear() + "-" + data_fim.getMonthValue() + "-" + data_fim.getDayOfMonth() + " " + hora_fim + ":" + min_fim + ":00";
        Timestamp timestamp_fim = Timestamp.valueOf(data_hora_fim);
        hora.setData_hora_fim(timestamp_fim.toString());

        return hora;
    }

    @FXML
    private void carregarTabelaAcionamento(){
        
    }

    @FXML
    private void limparCampos(ActionEvent event) {
        horaInicio.getValueFactory().setValue(0);
        minutoInicio.getValueFactory().setValue(0);
        horaFim.getValueFactory().setValue(0);
        minutoFim.getValueFactory().setValue(0);
        
    }
    
}
