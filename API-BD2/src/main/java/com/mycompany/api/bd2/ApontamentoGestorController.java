/*/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.api.bd2;

import Conexao.Conexao;
import com.mycompany.api.bd2.daos.horaDAO;
import com.mycompany.api.bd2.daos.integranteDAO;
import com.mycompany.api.bd2.models.TabelaAprovaçãoGestor;
import com.mycompany.api.bd2.models.Usuario;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    private Button BotaoAprovar;
    @FXML
    private Button BotaoReprovar;

    @FXML
    private TableView<TabelaAprovaçãoGestor> tabelaApontamento;

    @FXML
    private TableColumn<TabelaAprovaçãoGestor, String> colunaCR;//ok
    @FXML
    private TableColumn<TabelaAprovaçãoGestor, String> colunaEmpresa;//ok
    @FXML
    private TableColumn<TabelaAprovaçãoGestor, String> colunaFunçao;//ok
    @FXML
    private TableColumn<TabelaAprovaçãoGestor, String> colunaProjeto;//ok
    @FXML
    private TableColumn<TabelaAprovaçãoGestor, String> colunaJust;//ok
    @FXML
    private TableColumn<TabelaAprovaçãoGestor, String> colunaUsername;//ok
    @FXML
    private TableColumn<TabelaAprovaçãoGestor, String> colunaFim;//ok
    @FXML
    private TableColumn<TabelaAprovaçãoGestor, String> colunaInicio;//ok

    horaDAO horadao = new horaDAO();

    private List<TabelaAprovaçãoGestor> lishoras = new ArrayList<>();
    private ObservableList<TabelaAprovaçãoGestor> observablelisthoras = FXCollections.observableArrayList();

    private String usuario = TelaLoginController.usuariologado.getUsername();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nomeUsuario.setText(usuario);
        menuApontamento.setDisable(true);

        minimizarTela.setOnAction(e -> {
            Stage stage = (Stage) minimizarTela.getScene().getWindow();
            stage.setIconified(true);
        });
        carregarTabelaLancamento();

    }

    public void fechaTela() {
        Platform.exit();
    }

    private integranteDAO crgestor = new integranteDAO();

    @FXML
    public void carregarTabelaLancamento() {
        lishoras.clear();
        lishoras.addAll(horadao.getHora(crgestor.getListCrGestor(usuario)));
        observablelisthoras.setAll(lishoras);
        tabelaApontamento.setItems(observablelisthoras);

        colunaUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colunaCR.setCellValueFactory(new PropertyValueFactory<>("cod_cr"));
        colunaEmpresa.setCellValueFactory(new PropertyValueFactory<>("empresa"));
        colunaProjeto.setCellValueFactory(new PropertyValueFactory<>("projeto"));
        colunaInicio.setCellValueFactory(new PropertyValueFactory<>("inicio"));
        colunaFim.setCellValueFactory(new PropertyValueFactory<>("fim"));
        //colunaJust.setCellValueFactory(new PropertyValueFactory<>("justificativa_lancamento"));
        //colunaFunçao.setCellValueFactory(new PropertyValueFactory<>("justificativa_lancamento"));
        tabelaApontamento.refresh();
    }

    @FXML
    public void botaoAprovar() {
        if (tabelaApontamento.getSelectionModel().getSelectedItem() != null) {
            horadao.aprovarHora(tabelaApontamento.getSelectionModel().getSelectedItem().getId());
            carregarTabelaLancamento();
        }
    }

    @FXML
    public void botaoReprovar() {
        if (tabelaApontamento.getSelectionModel().getSelectedItem() != null) {
            horadao.reprovarHora(tabelaApontamento.getSelectionModel().getSelectedItem().getId());
            carregarTabelaLancamento();
        }
    }

    @FXML
    public void navLancamentoColaborador(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LancamentoColaborador.fxml"));
        Parent root = loader.load();
        Scene cena = new Scene(root);
        Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        stage.setScene(cena);
        stage.centerOnScreen();
        stage.show();
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

    
}
