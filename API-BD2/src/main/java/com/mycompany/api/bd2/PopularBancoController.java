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
    private Button botaoAdicionar;

    @FXML
    private Button botaoExcluir;

    @FXML
    private TableColumn<?, ?> colunaFuncao;

    @FXML
    private TableView<Usuario> tabelaUsuarios;

    @FXML
    private TableColumn<Usuario, String> colunaUsuario;

    @FXML
    private Button botaoGestor;

    @FXML
    private Label botaoVoltar;

    @FXML
    private TableColumn<Usuario, String> colunaUserDisp;

    private Centro_resultado crSelecionado = CadastroCRADMController.crInfo;

    private ObservableList<Usuario> observableListUser = FXCollections.observableArrayList();
    private ObservableList<Usuario> observableListUsuariosSelecionados = FXCollections.observableArrayList();

    private Usuario usuarioSelecionado;

    @FXML
    void BotaoAdicionar(ActionEvent event) {
        if (usuarioSelecionado != null) {
            observableListUsuariosSelecionados.add(usuarioSelecionado);
            colunaUsuario.setCellValueFactory(new PropertyValueFactory<>("username"));
            tabelaUsuarios.setItems(observableListUsuariosSelecionados);
        }
    }

    @FXML
    void BotaoExcluir(ActionEvent event) {

    }

    @FXML
    void BotaoGestor(ActionEvent event) {

    }

    @FXML
    void initialize() {
        System.out.println("ola");
        carregarTabelaUser();
        configurarColunas();
        adicionarListenerSelecaoUsuario();
    }

    private void carregarTabelaUser() {
        usuarioDAO usuarioDao = new usuarioDAO();
        observableListUser.setAll(usuarioDao.getUsuariosSemCr(crSelecionado.getCodigo_cr()));
        tabelaUsuarios.setItems(observableListUser);
    }

    private void configurarColunas() {
        colunaUserDisp.setCellValueFactory(new PropertyValueFactory<>("username"));
    }

    private void adicionarListenerSelecaoUsuario() {
        tabelaUsuarios.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            usuarioSelecionado = newValue;
            if (usuarioSelecionado != null) {
                System.out.println("Usuário selecionado: " + usuarioSelecionado.getUsername());
            }
        });
    }
}
