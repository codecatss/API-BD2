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
    private Button botaoGestor;

    @FXML
    private Label botaoVoltar;

    @FXML
    private TableColumn<Usuario, String> colunaUserDisp;

    @FXML
    private TableView<Usuario> tabelaUsuarios;

    private Centro_resultado crSelecionado = CadastroCRADMController.crInfo;

    private ObservableList<Usuario> observableListUser = FXCollections.observableArrayList();

    @FXML
    void BotaoAdicionar(ActionEvent event) {
        System.out.println(crSelecionado.getNome());
        System.out.println(crSelecionado.getSigla());
        System.out.println(crSelecionado.getCodigo_cr());
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
    }

    @FXML
    private void carregarTabelaUser() {
        usuarioDAO usuarioDao = new usuarioDAO();
        observableListUser.setAll(usuarioDao.getUsuariosSemCr(crSelecionado.getCodigo_cr()));
        
        colunaUserDisp.setCellValueFactory(new PropertyValueFactory<>("username"));
        tabelaUsuarios.setItems(observableListUser);
    }

}
