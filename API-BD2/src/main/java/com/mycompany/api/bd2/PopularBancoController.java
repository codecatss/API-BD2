package com.mycompany.api.bd2;

import com.mycompany.api.bd2.daos.usuarioDAO;
import com.mycompany.api.bd2.models.Centro_resultado;
import com.mycompany.api.bd2.models.Usuario;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PopularBancoController {

    
    @FXML
    private Label nometelaatual;
    @FXML
    private Button fecharTela;
    @FXML
    private Button minimizarTela;
    @FXML
    private Button botaoGestor;
    @FXML
    private Button botaoAdicionar;
    @FXML
    private Button botaoExcluir;
    @FXML
    private Button botaoVoltar;
    @FXML
    private TableView<Usuario> tabelaUsuarioDisp;
    @FXML
    private TableColumn<Usuario, String> colunaUsarioDisp;
    @FXML
    private TableView<?> tabelaIntegrantes;
    @FXML
    private TableColumn<Usuario, String> colunaIntegrantes;
    

    private Centro_resultado crSelecionado = CadastroCRADMController.crInfo;

    private ObservableList<Usuario> observableListUser = FXCollections.observableArrayList();
    private ObservableList<Usuario> observableListUsuariosSelecionados = FXCollections.observableArrayList();

    private Usuario usuarioSelecionado;
   
    @FXML
    void BotaoAdicionar(ActionEvent event) {
        if (usuarioSelecionado != null) {
            observableListUsuariosSelecionados.add(usuarioSelecionado);
            //.setCellValueFactory(new PropertyValueFactory<>("username"));
            //.setItems(observableListUsuariosSelecionados);
        }
    }

    @FXML
    void BotaoExcluir(ActionEvent event) {

    }

    @FXML
    void BotaoGestor(ActionEvent event) {

    }

    void initialize() {
        System.out.println("ola");
        carregarTabelaUser();
        configurarColunas();
        adicionarListenerSelecaoUsuario();
    }

    private void carregarTabelaUser() {
        usuarioDAO usuarioDao = new usuarioDAO();
        observableListUser.setAll(usuarioDao.getUsuariosSemCr(crSelecionado.getCodigo_cr()));
        //.setItems(observableListUser);
    }

    private void configurarColunas() {
        //.setCellValueFactory(new PropertyValueFactory<>("username"));
    }

    private void adicionarListenerSelecaoUsuario() {
        //.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            usuarioSelecionado = newValue;
            if (usuarioSelecionado != null) {
                System.out.println("Usuário selecionado: " + usuarioSelecionado.getUsername());
            }
        });
    }

    @FXML
    private void BotaoVoltar(ActionEvent event) {
    }
}
