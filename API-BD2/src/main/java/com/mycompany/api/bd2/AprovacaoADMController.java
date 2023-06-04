package com.mycompany.api.bd2;

import com.mycompany.api.bd2.daos.crDAO;
import com.mycompany.api.bd2.daos.horaDAO;
import com.mycompany.api.bd2.models.Hora;
import com.mycompany.api.bd2.models.Usuario;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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
    private TableColumn<Hora, String> status;

    @FXML
    private Button botaoReprovar;
    @FXML
    private Button botaoAprovar;
    @FXML
    private ComboBox<String> comboboxStatusApontamentos;

    @FXML
    private TextArea textoJustificativa;

    private List<Hora> lishoras = new ArrayList<>();
    private ObservableList<Hora> observablelisthoras = FXCollections.observableArrayList();
    private horaDAO horadao = new horaDAO();
    private crDAO crgestor = new crDAO();
    private Usuario usuario = TelaLoginController.usuariologado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboboxStatusApontamentos.getItems().addAll("TODAS HORAS", "APROVADAS", "REPROVADAS", "PENDENTES");
        comboboxStatusApontamentos.setValue("PENDENTES");

        comboboxStatusApontamentos.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("TODAS HORAS")) {
                System.out.println("Todas");
                carregarTabelaLancamento();
            } else if (newValue.equals("REPROVADAS")) {
                System.out.println("Reprovada");
                carregarTabelaLancamento();
                // Lógica para exibir as horas reprovadas
            } else if (newValue.equals("APROVADAS")) {
                System.out.println("Aprovadas");
                carregarTabelaLancamento(); // Chama o método para carregar as horas aprovadas
            } else if (newValue.equals("PENDENTES")) {
                // Lógica para exibir as horas pendentes
                carregarTabelaLancamento();
            }
        });

        // Restante do código...
        nomeUsuario.setText(usuario.getUsername());
        minimizarTela.setOnAction(e -> {
            Stage stage = (Stage) minimizarTela.getScene().getWindow();
            stage.setIconified(true);
        });
        menuAprovar.setDisable(true);

        carregarTabelaLancamento(); // Carrega as horas iniciais
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
        String opcaoSelecionada = comboboxStatusApontamentos.getValue();

        lishoras.clear(); // Limpa a lista antes de adicionar as horas corretas

        if (opcaoSelecionada.equals("APROVADAS")) {
            lishoras.addAll(horadao.getAprovadaHoras());
        } else if (opcaoSelecionada.equals("TODAS HORAS")) {
            lishoras.addAll(horadao.getTodasHoras());
        } else if (opcaoSelecionada.equals("PENDENTES")) {
            lishoras.addAll(horadao.getHorasAprovadas());
        } else if (opcaoSelecionada.equals("REPROVADAS")) {
            lishoras.addAll(horadao.getReprovadaHoras());
        }

        observablelisthoras.setAll(lishoras);

        tabelaAprovacao.setItems(observablelisthoras);
        colunaColaboradorADM.setCellValueFactory(new PropertyValueFactory<>("username_lancador"));
        colunaCRADM.setCellValueFactory(new PropertyValueFactory<>("cod_cr"));
        colundaGestorADM.setCellValueFactory(new PropertyValueFactory<>("aprovador_gestor"));
        colunaEmpresaADM.setCellValueFactory(new PropertyValueFactory<>("cnpj_cliente"));
        colunaProjetoADM.setCellValueFactory(new PropertyValueFactory<>("projeto"));
        colunaInicioADM.setCellValueFactory(new PropertyValueFactory<>("data_hora_inicio"));
        colunaFimADM.setCellValueFactory(new PropertyValueFactory<>("data_hora_fim"));
        colunaJustificativaADM.setCellValueFactory(new PropertyValueFactory<>("justificativa_lancamento"));
        status.setCellValueFactory(new PropertyValueFactory<>("status_aprovacao"));

        tabelaAprovacao.refresh();
    }

    @FXML
    public void BotaoReprovar() {
        System.out.println("esse é o id" + tabelaAprovacao.getSelectionModel().getSelectedItem().getId());
        if (tabelaAprovacao.getSelectionModel().getSelectedItem() != null) {
            horadao.reprovarHoraADM(tabelaAprovacao.getSelectionModel().getSelectedItem().getId());
            carregarTabelaLancamento();
        }
    }

    @FXML
    public void BotaoAprovar() {
        Hora horaSelecionada = tabelaAprovacao.getSelectionModel().getSelectedItem();
        System.out.println(horaSelecionada);
        if (horaSelecionada != null) {
            horadao.aprovarHoraADM(tabelaAprovacao.getSelectionModel().getSelectedItem().getId(), usuario.getUsername());
            carregarTabelaLancamento(); // Atualiza a tabela após aprovar a hora
        }
    }

}
