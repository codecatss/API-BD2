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
import com.mycompany.api.bd2.models.Usuario;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.input.MouseEvent;
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
    private Hora horaCapturada; // Atributo para armazenar a instância de Hora capturada
    ObservableList<Hora>  valorDoItemSelecionado;
    int index;
    private List<Hora> lantemp = new LinkedList<Hora>();  
    private static List<Hora> acionamentos = new LinkedList<Hora>();
    
    
    public void setHoraCapturada(Hora hora) {
        this.horaCapturada = hora;
    }

    
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
        lantemp.clear();
        contagem = 1;
        
          tabelaAcionamento.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) { // Verifica se é um único clique
                   valorDoItemSelecionado = tabelaAcionamento.getSelectionModel().getSelectedItems(); // Obtém os itens selecionados

                    index = tabelaAcionamento.getItems().indexOf(valorDoItemSelecionado);
                    
                    System.out.println(valorDoItemSelecionado);
                }
            }
        });
                
        carregarTabelaAcionamento();
        
        //botaoLimpar.setOnAction(event -> limparCampos());
    }    
    

    private static int contagem = 1;
    @FXML
    private void botaoAdicionar() throws ParseException{
        Hora horaExtra = new Hora();
        Hora hora = LancamentoColaboradorController.getHora();
        
        Timestamp timestampini = hora.getData_hora_inicio();
        String timestampini2 = hora.getData_hora_inicio().toString();
        timestampini2 = Timestamp.valueOf(timestampini2).toString();
        System.out.println(timestampini2);
        String dataIni = timestampini2.substring(0,10);
        int hora_inicio = horaInicio.getValue();
        int min_inicio = minutoInicio.getValue();
        String data_hora_inicio = dataIni + " " + hora_inicio + ":" + min_inicio + ":00";
        horaExtra.setData_hora_inicio(data_hora_inicio);
        horaExtra.setCnpj_cliente(hora.getCnpj_cliente());
        horaExtra.setCod_cr(hora.getCod_cr());
        horaExtra.setJustificativa_lancamento(hora.getJustificativa_lancamento());
        horaExtra.setNome_cliente(hora.getNome_cliente());
        horaExtra.setProjeto(hora.getProjeto());
        horaExtra.setUsername_aprovador(hora.getUsername_aprovador());
        horaExtra.setUsername_lancador(hora.getUsername_lancador());
        horaExtra.setTipo(hora.getTipo());
        horaExtra.setStatus_aprovacao(hora.getStatus_aprovacao());
        horaExtra.setSolicitante(hora.getSolicitante());

        Timestamp timestampfim = hora.getData_hora_fim();
        String timestampfim2 = hora.getData_hora_fim().toString();
        timestampfim2 = Timestamp.valueOf(timestampfim2).toString();
        System.out.println(timestampfim);
        String dataFim = timestampfim2.substring(0,10);
        int hora_fim = horaFim.getValue();
        int min_fim = minutoFim.getValue();
        String data_hora_fim = dataFim + " " + hora_fim + ":" + min_fim + ":00";
        horaExtra.setData_hora_fim(data_hora_fim);
        
        horaExtra.setTipo(TipoHora.EXTRA.name());
        horaExtra.setId(contagem);
        contagem++;
        lantemp.add(horaExtra); 
    
        System.out.println(hora);
       
        carregarTabelaAcionamento();
    } 

    @FXML
    private void botaoSalvar() throws ParseException{
        acionamentos.addAll(lantemp);
        for (Hora hora : acionamentos){
            System.out.println("Adicionado "+hora.getData_hora_inicio()+" até "+hora.getData_hora_fim());
        }
        Stage stage = (Stage) botaoSalvar.getScene().getWindow();
        stage.close();
    } 
    
    private ObservableList<Hora> observablelisthoras =  FXCollections.observableArrayList();
    @FXML
    private void carregarTabelaAcionamento(){
        
        observablelisthoras.clear();
        observablelisthoras.setAll(lantemp);
        tabelaAcionamento.setItems(observablelisthoras);
        colunaAcionamento.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaInicio.setCellValueFactory(new PropertyValueFactory<>("data_hora_inicio"));
        colunaFim.setCellValueFactory(new PropertyValueFactory<>("data_hora_fim"));
        
    }

    @FXML
    private void limparCampos(ActionEvent event) throws ParseException {
        horaInicio.getValueFactory().setValue(0);
        minutoInicio.getValueFactory().setValue(0);
        horaFim.getValueFactory().setValue(0);
        minutoFim.getValueFactory().setValue(0);
        
            
            
            if (!valorDoItemSelecionado.isEmpty()) {
            lantemp.removeAll(valorDoItemSelecionado);
            tabelaAcionamento.getItems().removeAll(valorDoItemSelecionado); // Remove os itens selecionados da lista da tabela
        }
            
            
             
    }

    public static List<Hora> getAcionamentos() {
        return acionamentos;
    }
    
}

