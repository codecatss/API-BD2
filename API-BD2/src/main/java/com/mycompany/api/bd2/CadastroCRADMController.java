/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.api.bd2;

import com.mycompany.api.bd2.daos.crDAO;
import com.mycompany.api.bd2.models.Centro_resultado;
import com.mycompany.api.bd2.models.Hora;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author csous
 */
public class CadastroCRADMController implements Initializable {

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
    private Button menuUsuario;
    @FXML
    private Button menuCR;
    @FXML
    private Button menuCliente;
    @FXML
    private Button menuAprovar;
    @FXML
    private Button menuRelatorio;
    @FXML
    private TextField entradaCod;
    @FXML
    private TextField entradaNome;
    @FXML
    private TextField entradaSigla;
    @FXML
    private Button botaoAdicionar;
    @FXML
    private Button botaoLimpar;
    @FXML
    private Button botaoEditar;
    @FXML
    private Button botaoInativar;
    @FXML
    private Button botaoAtivar;
    @FXML
    private TableView<Centro_resultado> tabelaCadastroCliente;
    @FXML
    private TableColumn<Centro_resultado, String> colunaCod;
    @FXML
    private TableColumn<Centro_resultado, String> colunaNome;
    @FXML
    private TableColumn<Centro_resultado, String> colunaSigla;

    private String usuario = TelaLoginController.usuariologado.getUsername();

    public void initialize(URL url, ResourceBundle rb) {
        entradaCod.setStyle(null);
        entradaCod.setPromptText("Apenas números");
        botaoEditar.setDisable(true);
        botaoInativar.setDisable(true);
        botaoAtivar.setDisable(true);

        menuCR.setDisable(true);
        nomeUsuario.setText(usuario);
        forneceTabela();

        tabelaCadastroCliente.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> {
            if (novo == null) {
                botaoAdicionar.setDisable(false);
                botaoEditar.setDisable(true);
                botaoInativar.setDisable(true);
                botaoAtivar.setDisable(true);
            } else {
                botaoAdicionar.setDisable(true);
                botaoEditar.setDisable(false);
                botaoInativar.setDisable(false);
                botaoAtivar.setDisable(false);
            }
        });
    }

    private String erro = "-fx-border-color:#E06469";

    @FXML
    private void BotaoAdicionar() {
        boolean ehdigito = false;
        boolean atequatro = false;
        String cod = "0";
        crDAO crdao = new crDAO();
        Centro_resultado cr = new Centro_resultado();
        //testa se o codigo da CR é um número
        if (entradaCod.getText().matches("\\d+")) {
            cod = entradaCod.getText();
            if (cod.length() < 4) {
                cod = String.format("%0" + (4 - cod.length()) + "d%s", 0, cod);
                ehdigito = true;
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("O código da CR deve conter ATÉ 4 números.");
                alert.showAndWait();
                System.out.println("mais de 4 caracteres");
            }
        } else {
            entradaCod.setStyle(erro);
            entradaCod.setText(null);
            entradaCod.setPromptText("Apenas números");
            entradaCod.setStyle("-fx-prompt-text-fill: #E06469;");
            System.out.println("Apenas números em como código");
        }

        if (ehdigito && !entradaNome.getText().isEmpty()) {
            try {
                cr.setNome(entradaNome.getText());
                cr.setCodigo_cr(cod);
                cr.setSigla(entradaSigla.getText().toUpperCase());
                cr.setStatus_cr("ativo");

                crdao.save(cr);
                System.out.println("Salvo");
                limparCampos();
                forneceTabela();
            } catch (Exception e) {
                System.out.println("Ocorreu um erro ao salvar os dados.");
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Preencha todos os campos.");
            alert.showAndWait();
            System.out.println("Preencha todos os campos");
        }
    }

    @FXML
    private void limparCampos() throws IOException {
        entradaNome.setText(null);
        entradaCod.setText(null);
        entradaSigla.setText(null);
        botaoAdicionar.setDisable(true);

        // Desseleciona a linha da tabela
        tabelaCadastroCliente.getSelectionModel().clearSelection();
    }

    @FXML
    private void navGestClientes(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CadastroClienteADM.fxml"));
        Parent root = loader.load();
        Scene cena = new Scene(root);
        Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        stage.setScene(cena);
        stage.centerOnScreen();
        stage.show();
    }

    private List<Centro_resultado> liscr = new ArrayList<>();
    private ObservableList<Centro_resultado> observablelistliscr = FXCollections.observableArrayList();

    @FXML
    private void forneceTabela() {
        crDAO crdao = new crDAO();
        liscr.clear();
        liscr.addAll(crdao.getCrs());
        observablelistliscr.setAll(liscr);
        tabelaCadastroCliente.setItems(observablelistliscr);

        colunaCod.setCellValueFactory(new PropertyValueFactory<>("codigo_cr"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("sigla"));
        colunaSigla.setCellValueFactory(new PropertyValueFactory<>("nome"));

    }
}
