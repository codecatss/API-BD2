package com.mycompany.api.bd2;

import com.mycompany.api.bd2.daos.integranteDAO;
import com.mycompany.api.bd2.daos.usuarioDAO;
import com.mycompany.api.bd2.models.Centro_resultado;
import com.mycompany.api.bd2.models.Integrante;
import com.mycompany.api.bd2.models.Usuario;
import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PopularBancoController {

    @FXML
    private Label nometelaatual;
    @FXML
    private Label nomeCR;
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
    void initialize() {
        nomeCR.setText(crSelecionado.getNome());
        carregarTabelaUser();
        adicionarListenerSelecaoUsuario();
        carregarTabelaIntegrantes();
        adicionarListenerSelecaoIntegrante();
    }

    @FXML
    void BotaoAdicionar(ActionEvent event) {
        if (usuarioSelecionado != null) {
            integranteDAO integranteDao = new integranteDAO();
            Integrante Gestor = integranteDao.getGestorFromCr(crSelecionado.getCodigo_cr());

            Integrante integrante = new Integrante();
            integrante.setCod_cr(crSelecionado.getCodigo_cr());
            integrante.setUsername_integrante(usuarioSelecionado.getUsername());
            integrante.setGestor("colaborador");

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
                tabelaIntegrantes.getSelectionModel().clearSelection();
                botaoAdicionar.setDisable(false);
                botaoExcluir.setDisable(true);
                botaoGestor.setDisable(true);
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
                tabelaUsuarioDisp.getSelectionModel().clearSelection();
                botaoAdicionar.setDisable(true);
                botaoExcluir.setDisable(false);
                botaoGestor.setDisable(false);
                System.out.println("Integrante selecionado: " + integranteSelecionado.getUsername_integrante());
            }
        });
    }

    @FXML
    private void BotaoFechar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void BotaoMinimizar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void BotaoVoltar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CadastroCRADM.fxml"));
        Parent root = loader.load();
        Scene cena = new Scene(root);
        Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        stage.setScene(cena);
        stage.centerOnScreen();
        stage.show();
    }
}
