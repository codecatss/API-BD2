/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.api.bd2;

import static com.mycompany.api.bd2.TelaLoginController.usuariologado;
import com.mycompany.api.bd2.daos.horaDAO;
import com.mycompany.api.bd2.models.Hora;
import com.mycompany.api.bd2.models.Usuario;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author csous
 */
public class ApontamentoGestorController implements Initializable {

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
    private Button botaoSair;
    @FXML
    private Button menuLancamento;
    @FXML
    private Button menuApontamento;
    @FXML
    private Button menuRelatorio;
    @FXML
    private Button botaoAprovar;
    @FXML
    private Button botaoReprovar;

    @FXML
    private TableView<Hora> tabelaApontamento;

    @FXML
    private TableColumn<Hora, String> colunaCR;
    @FXML
    private TableColumn<Hora, String> colunaEmpresa;
    @FXML
    private TableColumn<Hora, String> colunaFunçao;
    @FXML
    private TableColumn<Hora, String> colunaGestor;
    @FXML
    private TableColumn<Hora, String> colunaNome;
    @FXML
    private TableColumn<Hora, String> colunaProjeto;
    @FXML
    private TableColumn<Hora, String> colunaJust;
    @FXML
    private TableColumn<Hora, String> colunaUsername;
    @FXML
    private TableColumn<Hora, Timestamp> colunaFim;
    @FXML
    private TableColumn<Hora, Timestamp> colunaInicio;
    
    

    private List<Hora> lishoras = new ArrayList<>();
    private ObservableList<Hora> observablelisthoras = FXCollections.observableArrayList();

    public static Usuario getUsuario1() {
        return usuariologado;
    }
    public static Usuario usuariologado = new Usuario();
    private String usuario = TelaLoginController.usuariologado.getUsername();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nomeUsuario.setText(usuario);

        minimizarTela.setOnAction(e -> {
            Stage stage = (Stage) minimizarTela.getScene().getWindow();
            stage.setIconified(true);
        });
    }

    public void fechaTela() {
        Platform.exit();
    }

    @FXML
    public void carregarTabelaLancamento() {
        horaDAO horadao = new horaDAO();
        lishoras.clear();
        lishoras.addAll(horadao.getHora(usuario));
        observablelisthoras.setAll(lishoras);
        tabelaApontamento.setItems(observablelisthoras);

        colunaUsername.setCellValueFactory(new PropertyValueFactory<>("username_lancador"));
        colunaCR.setCellValueFactory(new PropertyValueFactory<>("cod_cr"));
        colunaEmpresa.setCellValueFactory(new PropertyValueFactory<>("cnpj_cliente"));
        colunaProjeto.setCellValueFactory(new PropertyValueFactory<>("projeto"));
        colunaInicio.setCellValueFactory(new PropertyValueFactory<>("data_hora_fim"));
        colunaFim.setCellValueFactory(new PropertyValueFactory<>("data_hora_fim"));
        colunaJust.setCellValueFactory(new PropertyValueFactory<>("justificativa_lancamento"));

        tabelaApontamento.refresh();
    }
   
    @FXML
    public void lancamentoColaborador( ) throws IOException {
        App.setRoot("LancamentoColaborador");}
    }
