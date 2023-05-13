package com.mycompany.api.bd2;

import com.mycompany.api.bd2.daos.integranteDAO;
import com.mycompany.api.bd2.daos.usuarioDAO;
import com.mycompany.api.bd2.models.Centro_resultado;
import com.mycompany.api.bd2.models.Integrante;
import com.mycompany.api.bd2.models.Usuario;
import java.util.List;

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
    private TableColumn<Usuario, String> colunaUsuarioDisp;
    @FXML
    private TableView<Integrante> tabelaIntegrantes;
    @FXML
    private TableColumn<Integrante, String> colunaIntegrantes;
    @FXML
    private TableColumn<Integrante, String> colunaFuncao;

    private Centro_resultado crSelecionado = CadastroCRADMController.crInfo;

    private ObservableList<Usuario> observableListUser = FXCollections.observableArrayList();
    private ObservableList<Usuario> observableListUsuariosSelecionados = FXCollections.observableArrayList();

    private ObservableList<Integrante> observableListIntegrante = FXCollections.observableArrayList();
    private ObservableList<Integrante> observableListIntegrantesSelecionados = FXCollections.observableArrayList();

    private Usuario usuarioSelecionado;
    private Integrante integranteSelecionado;

    @FXML
    void BotaoAdicionar(ActionEvent event) {
        if (usuarioSelecionado != null) {
            Integrante integrante = new Integrante();
            integrante.setCod_cr(crSelecionado.getCodigo_cr());
            integrante.setUsername_integrante(usuarioSelecionado.getUsername());
            integrante.setGestor("colaborador");

            integranteDAO integranteDao = new integranteDAO();
            integranteDao.save(integrante);

            carregarTabelaUser();
            carregarTabelaIntegrantes();
        } else {
            System.out.println("Necas de pitibiribas");
        }
    }

    @FXML
    void BotaoExcluir(ActionEvent event) {
        if (integranteSelecionado != null) {
            integranteDAO integranteDao = new integranteDAO();
            integranteDao.removeFromCr(
                    crSelecionado.getCodigo_cr(),
                    integranteSelecionado.getUsername_integrante()
            );
            carregarTabelaUser();
            carregarTabelaIntegrantes();
        } else {
            System.out.println("Nada selecionado");
        }
    }

    @FXML
    void BotaoGestor(ActionEvent event) {
        if (integranteSelecionado != null) {
            integranteDAO integranteDao = new integranteDAO();
            usuarioDAO usuarioDao = new usuarioDAO();

            Integrante antigoGestor = integranteDao.getGestorFromCr(crSelecionado.getCodigo_cr());
            Usuario novoGestor = usuarioDao.getUsuarioByUsername(integranteSelecionado.getUsername_integrante());

            if (antigoGestor == null) {
                integranteDao.updateGestor("gestor",
                        novoGestor.getUsername(),
                        crSelecionado.getCodigo_cr());
            } else {
                integranteDao.updateGestor("gestor", novoGestor.getUsername(), crSelecionado.getCodigo_cr());

                integranteDao.updateGestor("colaborador", antigoGestor.getUsername_integrante(), crSelecionado.getCodigo_cr());

                List<Integer> crsDoGestor = integranteDao.getListCrGestor(antigoGestor.getUsername_integrante());

                if (crsDoGestor.isEmpty()) {
                    System.out.println("não há CRs associados a este gestor");
                    Usuario usuarioAntigoGestor = usuarioDao.getUsuarioByUsername(antigoGestor.getUsername_integrante());
                    usuarioAntigoGestor.setCargo("colaborador");
                    usuarioDao.update(usuarioAntigoGestor);
                }

                if (novoGestor.getCargo().equals("colaborador")) {
                    novoGestor.setCargo("gestor");
                    usuarioDao.update(novoGestor);
                }
            }
            carregarTabelaIntegrantes();
        } else {
            System.out.println("Ninguem selecionado");
        }
    }

    @FXML
    void initialize() {
        carregarTabelaUser();
        adicionarListenerSelecaoUsuario();
        carregarTabelaIntegrantes();
        adicionarListenerSelecaoIntegrante();
    }

    private void carregarTabelaUser() {
        usuarioDAO usuarioDao = new usuarioDAO();
        observableListUser.setAll(usuarioDao.getUsuariosSemCr(crSelecionado.getCodigo_cr()));
        tabelaUsuarioDisp.setItems(observableListUser);

        colunaUsuarioDisp.setCellValueFactory(new PropertyValueFactory<>("username"));
    }

    private void adicionarListenerSelecaoUsuario() {
        tabelaUsuarioDisp.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            usuarioSelecionado = newValue;
            if (usuarioSelecionado != null) {
                System.out.println("Usuário selecionado: " + usuarioSelecionado.getUsername());
            }
        });
    }

    private void carregarTabelaIntegrantes() {
        integranteDAO integranteDao = new integranteDAO();
        observableListIntegrante.setAll(integranteDao.getIntegrantesByCr(crSelecionado.getCodigo_cr()));
        tabelaIntegrantes.setItems(observableListIntegrante);

        colunaFuncao.setCellValueFactory(new PropertyValueFactory<>("gestor"));
        colunaIntegrantes.setCellValueFactory(new PropertyValueFactory<>("username_integrante"));
    }

    private void adicionarListenerSelecaoIntegrante() {
        tabelaIntegrantes.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            integranteSelecionado = newValue;
            if (integranteSelecionado != null) {
                System.out.println("Integrante selecionado: " + integranteSelecionado.getUsername_integrante());
            }
        });
    }

    @FXML
    private void BotaoVoltar(ActionEvent event) {
    }
}
