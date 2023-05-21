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
import com.mycompany.api.bd2.models.TipoHora;
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
    private TableView<Hora> tabelaAcionamento;
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
        
        acionamentos.clear();
        contagem = 1;
                
        carregarTabelaAcionamento();
        
        //botaoLimpar.setOnAction(event -> limparCampos());
    }    
    private static List<Hora> acionamentos = new LinkedList<Hora>();
    private static int contagem = 1;
    @FXML
    private void botaoAdicionar() throws ParseException{
        Hora hora = LancamentoColaboradorController.getHora();
        
        Timestamp timestampini = hora.getData_hora_inicio();
        int hora_inicio = horaInicio.getValue();
        int min_inicio = minutoInicio.getValue();
        String data_hora_inicio = timestampini.getYear() + "-" + timestampini.getMonth() + "-" + timestampini.getDay()+ " " + hora_inicio + ":" + min_inicio + ":00";
        hora.setData_hora_inicio(data_hora_inicio);
        
        Timestamp timestampfim = hora.getData_hora_fim();
        int hora_fim = horaFim.getValue();
        int min_fim = minutoFim.getValue();
        String data_hora_fim = timestampfim.getYear() + "-" + timestampfim.getMonth() + "-" + timestampfim.getDay()+ " " + hora_fim + ":" + min_fim + ":00";
        hora.setData_hora_fim(data_hora_fim);
        
        hora.setTipo(TipoHora.EXTRA.name());
        hora.setId(contagem);
        contagem++;
        
        acionamentos.add(hora);
        carregarTabelaAcionamento();
    } 

    @FXML
    private void botaoSalvar() throws ParseException{
        for (Hora hora : acionamentos){
            
            System.out.println("Adicionado "+hora.getData_hora_inicio()+" até "+hora.getData_hora_fim());
            //horaDAO hrDAO = new horaDAO();

            //hrDAO.save(hora);
        }
    } 
    
    private ObservableList<Hora> observablelisthoras =  FXCollections.observableArrayList();
    @FXML
    private void carregarTabelaAcionamento(){
        
        observablelisthoras.clear();
        observablelisthoras.setAll(acionamentos);
        tabelaAcionamento.setItems(observablelisthoras);
        colunaAcionamento.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaInicio.setCellValueFactory(new PropertyValueFactory<>("data_hora_inicio"));
        colunaFim.setCellValueFactory(new PropertyValueFactory<>("data_hora_fim"));
        
    }

    @FXML
    private void limparCampos(ActionEvent event) {
        horaInicio.getValueFactory().setValue(0);
        minutoInicio.getValueFactory().setValue(0);
        horaFim.getValueFactory().setValue(0);
        minutoFim.getValueFactory().setValue(0);
        
    }

    public static List<Hora> getAcionamentos() {
        return acionamentos;
    }
    
}

