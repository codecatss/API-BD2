package com.mycompany.api.bd2;

import com.mycompany.api.bd2.daos.crDAO;
import com.mycompany.api.bd2.daos.horaDAO;
import com.mycompany.api.bd2.models.Hora;
import com.mycompany.api.bd2.models.Usuario;
import com.mycompany.api.bd2.*;
import com.mycompany.api.bd2.daos.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AprovacaoADMController implements Initializable {

    @FXML
    private Button fecharTela;
    @FXML
    private Button minimizarTela;
    @FXML
    private Label nometelaatual;
    @FXML
    private AnchorPane menuADM;
    @FXML
    private ImageView imagemUser;
    @FXML
    private Label nomeUsuario;
    @FXML
    private Button botaoSair;
    @FXML
    private Button gestaoUsuario;
    @FXML
    private Button gestaoCR;
    @FXML
    private Button gestaoCiente;
    @FXML
    private Button menuAprovar;
    @FXML
    private Button menuRelatorio;
    @FXML
    private TableView<Hora> tabelaAprovacao;
    @FXML
    private TableColumn<Hora, String> colunaColaboradorADM;
    @FXML
    private TableColumn<Hora, String> colunaCRADM;
    @FXML
    private TableColumn<Hora, String> colundaGestorADM;
    @FXML
    private TableColumn<Hora, String> colunaEmpresaADM;
    @FXML
    private TableColumn<Hora, String> colunaProjetoADM;
    @FXML
    private TableColumn<Hora, String> colunaFuncaoADM;
    @FXML
    private TableColumn<Hora, String> colunaInicioADM;
    @FXML
    private TableColumn<Hora, String> colunaFimADM;
    @FXML
    private TableColumn<Hora, String> colunaJustificativaADM;
    @FXML
    private Button botaoReprovar;
    @FXML
    private Button botaoAprovar;

    private ObservableList<Hora> observablelisthoras = FXCollections.observableArrayList();
    private horaDAO horadao = new horaDAO();
    private Hora hora = new Hora();
    private crDAO crgestor = new crDAO();
    private Usuario usuario = new Usuario();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        minimizarTela.setOnAction(e -> {
            Stage stage = (Stage) minimizarTela.getScene().getWindow();
            stage.setIconified(true);
        });

        menuAprovar.setDisable(true);
        botaoAprovar.setOnAction(this::aprovarHora);
        botaoReprovar.setOnAction(this::reprovarHora);
        carregarTabelaLancamento();
    }

    public void fechaTela() {
        Platform.exit();
    }

    @FXML
    public void navGestaoCRADM(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cadastroCRADM.fxml"));
        Parent root = loader.load();
        Scene cena = new Scene(root);
        Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        stage.setScene(cena);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    public void navGestaoCliente(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CadastroClienteADM.fxml"));
        Parent root = loader.load();
        Scene cena = new Scene(root);
        Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        stage.setScene(cena);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    public void navCadastroUsuario(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CadastroUsuarioADM.fxml"));
        Parent root = loader.load();
        Scene cena = new Scene(root);
        Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        stage.setScene(cena);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    private void botaoSair(ActionEvent event) throws IOException {
        usuario.logout();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaLogin.fxml"));
        Parent root = loader.load();
        Scene cena = new Scene(root);
        Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        stage.setScene(cena);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void relatorios(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Em progresso");
        alert.setHeaderText(null);
        alert.setContentText("Desculpe o transtorno, estamos sempre trabalhando em melhorias");
        alert.showAndWait();
    }

    private void carregarTabelaLancamento() {
        colunaColaboradorADM.setCellValueFactory(new PropertyValueFactory<>("username_lancador"));
        colunaCRADM.setCellValueFactory(new PropertyValueFactory<>("cod_cr"));
        colunaEmpresaADM.setCellValueFactory(new PropertyValueFactory<>("cnpj_cliente"));
        colunaProjetoADM.setCellValueFactory(new PropertyValueFactory<>("projeto"));
        colunaInicioADM.setCellValueFactory(new PropertyValueFactory<>("data_hora_inicio"));
        colunaFimADM.setCellValueFactory(new PropertyValueFactory<>("data_hora_fim"));
        colunaJustificativaADM.setCellValueFactory(new PropertyValueFactory<>("justificativa_lancamento"));

        observablelisthoras.setAll(horadao.getHorasAprovadas());
        tabelaAprovacao.setItems(observablelisthoras);
        tabelaAprovacao.refresh();

    }

    @FXML
    private void aprovarHora(ActionEvent event) {
        Hora horaSelecionada = tabelaAprovacao.getSelectionModel().getSelectedItem();
        horaDAO dao = new horaDAO();

        if (horaSelecionada != null) {
            String codCr = horaSelecionada.getCod_cr();
            String statusAprovacaoADM = "aprovado_rh";

            dao.atualizarStatusAprovacao(codCr, statusAprovacaoADM);

            horaSelecionada.setStatus_aprovacao(statusAprovacaoADM);
            tabelaAprovacao.refresh();
            carregarTabelaLancamento();
        }
    }

    public void reprovarHora(ActionEvent event) {
        horaDAO horaDAO = new horaDAO();

        Hora horaSelecionada = tabelaAprovacao.getSelectionModel().getSelectedItem();

        if (horaSelecionada != null) {
            horaSelecionada.setStatus_aprovacao("negado");
            horaSelecionada.setJustificativa_negacao("Negada por RH");

            horaDAO.atualizarStatusAprovacao(horaSelecionada.getCod_cr(), horaSelecionada.getStatus_aprovacao());
            horaDAO.save(horaSelecionada);

            System.out.println("Hora reprovada com sucesso");
            tabelaAprovacao.refresh();
            carregarTabelaLancamento();
        }
    }

}
