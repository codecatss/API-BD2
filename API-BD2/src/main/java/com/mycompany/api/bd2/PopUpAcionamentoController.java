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
import com.mycompany.api.bd2.models.TimeData;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
import javafx.stage.Stage;


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
     
    
   
     
    private ObservableList<Integer> observablelisthoras =  FXCollections.observableArrayList();
    
    private LancamentoColaboradorController lancamentoController;
    private DatePicker dataInicio;
    private DatePicker dataFim;
    private ComboBox<String> horaTipo;
    private ComboBox<String> selecaoCliente;
    private ComboBox<String> selecaoCR;
    private TextField entradaProjeto;
    private TextField entradaSolicitante;
    private Label nomeUsuario;

    
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
        
        //carregarTabelaAcionamento();
        
        //botaoLimpar.setOnAction(event -> limparCampos());
    }    

    @FXML
    private void botaoAdicionar(ActionEvent event) throws ParseException {
        Hora hora = LancamentoColaboradorController.getHora();

        horaDAO hrDAO = new horaDAO();

        hrDAO.save(hora);
    } 

    @FXML
    private void carregarTabelaAcionamento(){
        
        
        observablelisthoras.add(TimeData.getInstance().getHoraInicio());
        tabelaAcionamento.setItems(observablelisthoras);
        colunaInicio.setCellValueFactory(new PropertyValueFactory<>("inicio"));
        colunaFim.setCellValueFactory(new PropertyValueFactory<>("fim"));
        
    }

    @FXML
    private void limparCampos(ActionEvent event) {
        horaInicio.getValueFactory().setValue(0);
        minutoInicio.getValueFactory().setValue(0);
        horaFim.getValueFactory().setValue(0);
        minutoFim.getValueFactory().setValue(0);
        
    }
    
}

