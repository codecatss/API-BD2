/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.api.bd2;

import com.mycompany.api.bd2.daos.crDAO;
import com.mycompany.api.bd2.models.Centro_resultado;
import com.mycompany.api.bd2.models.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    private Button botaoGerir;
    @FXML
    private TableView<Centro_resultado> tabelaCadastroCr;
    @FXML
    private TableColumn<Centro_resultado, String> colunaCod;
    @FXML
    private TableColumn<Centro_resultado, String> colunaNome;
    @FXML
    private TableColumn<Centro_resultado, String> colunaSigla;

    private String usuario = TelaLoginController.usuariologado.getUsername();

    String valorDoItemSelecionado;

    public void initialize(URL url, ResourceBundle rb) {
        entradaCod.setStyle(null);
        entradaCod.setPromptText("Até 4 números");
        botaoEditar.setDisable(true);
        botaoInativar.setDisable(true);
        botaoAtivar.setDisable(true);
        botaoGerir.setDisable(true);

        menuCR.setDisable(true);
        nomeUsuario.setText(usuario);
        carregarTabelaCr();

        tabelaCadastroCr.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> {
            if (novo == null) {
                botaoAdicionar.setDisable(false);
                botaoEditar.setDisable(true);
                botaoInativar.setDisable(true);
                botaoAtivar.setDisable(true);
                botaoGerir.setDisable(true);
            } else {
                botaoAdicionar.setDisable(true);
                botaoEditar.setDisable(false);
                botaoInativar.setDisable(false);
                botaoAtivar.setDisable(false);
                botaoGerir.setDisable(false);
            }
        });
        tabelaCadastroCr.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) { // Verifica se é um único clique
                    Centro_resultado item = (Centro_resultado) tabelaCadastroCr.getSelectionModel().getSelectedItem(); // Obtém o item selecionado
                    valorDoItemSelecionado = item.getCodigo_cr();
                    entradaCod.setText(item.getCodigo_cr());
                    entradaNome.setText(item.getNome());
                    entradaSigla.setText(item.getSigla());
                    System.out.println("Item selecionado: " + valorDoItemSelecionado); // Imprime no console
                }
            }
        });
    }

    private String erro = "-fx-border-color:#E06469";

    @FXML
    private void BotaoAdicionar() {
        boolean digito = false;
        String cod = "0";
        crDAO crdao = new crDAO();
        Centro_resultado cr = new Centro_resultado();
        //testa se o codigo da CR é um número
        if (entradaCod.getText().matches("\\d+")) {
            cod = entradaCod.getText();
            if (cod.length() <= 4) {
                cod = String.format("%0" + (5 - cod.length()) + "d%s", 0, cod);
                digito = true;
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

        if (digito && !entradaNome.getText().isEmpty()) {
            try {
                cr.setNome(entradaNome.getText());
                cr.setCodigo_cr(cod);
                cr.setSigla(entradaSigla.getText().toUpperCase());
                cr.setStatus_cr("ativo");

                crdao.save(cr);
                System.out.println("Salvo");
                limparCampos();
                carregarTabelaCr();
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
    private void limparCampos() {
        entradaNome.setText(null);
        entradaCod.setText(null);
        entradaSigla.setText(null);
        botaoAdicionar.setDisable(true);

        // Desseleciona a linha da tabela
        tabelaCadastroCr.getSelectionModel().clearSelection();
    }

    @FXML

    private void BotaoEditar(ActionEvent event) {
        // verifica se alguma linha foi selecionada
        if (tabelaCadastroCr.getSelectionModel().getSelectedItem() != null) {
            // desabilita a edição da coluna de código
            tabelaCadastroCr.getColumns().get(0).setEditable(false);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText(null);
            alert.setContentText("Tem certeza que deseja atualizar os dados do CR?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // o usuário clicou em "OK", continue com a ação
                Centro_resultado crSelecionado = tabelaCadastroCr.getSelectionModel().getSelectedItem();

                String novoNome = entradaNome.getText();
                String novaSigla = entradaSigla.getText();
                if (!novoNome.isEmpty()) {
                    // atualiza o objeto Centro_resultado com o novo nome
                    crSelecionado.setNome(novoNome);
                    crSelecionado.setSigla(novaSigla);

                    // salva o objeto atualizado no banco de dados
                    crDAO crdao = new crDAO();
                    crdao.update(crSelecionado);

                    // atualiza a tabela com as novas informações
                    carregarTabelaCr();
                    limparCampos();
                }
            } else {
                limparCampos();
                System.out.println("Cancelado");
            }

        } else {
            System.out.println("Nenhuma linha selecionada");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nenhuma linha selecionada");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione uma linha da tabela para editar");
            alert.showAndWait();
        }
    }

    @FXML
    private void BotaoInativar(ActionEvent event) {
        // exibe um alerta de confirmação antes de inativar a CR
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText(null);
        alert.setContentText("Tem certeza que deseja inativar o CR?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // o usuário clicou em "Ok", então a CR será inativada
            crDAO crdao = new crDAO();
            Centro_resultado cr = crdao.getCrByCodigo(valorDoItemSelecionado);
            cr.setStatus_cr("inativo");
            crdao.update(cr);

            carregarTabelaCr();
            limparCampos();

            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("CR inativado");
            alert2.setHeaderText(null);
            alert2.setContentText("O CR foi inativado com sucesso!");
            alert2.showAndWait();
        } else {
            // o usuário clicou em "Cancelar", então nada será feito
            limparCampos();
            carregarTabelaCr();
        }
    }

    @FXML
    private void BotaoAtivar(ActionEvent event) {
        // exibe um alerta de confirmação antes de ativar a CR
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText(null);
        alert.setContentText("Tem certeza que deseja ativar a CR?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // o usuário clicou em "Ok", então a CR será ativada
            crDAO crdao = new crDAO();
            Centro_resultado cr = crdao.getCrByCodigo(valorDoItemSelecionado);
            cr.setStatus_cr("ativo");
            crdao.update(cr);
            carregarTabelaCr();
            limparCampos();

            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("CR ativado");
            alert2.setHeaderText(null);
            alert2.setContentText("O CR foi ativado com sucesso!");
            alert2.showAndWait();
        } else {
            // o usuário clicou em "Cancelar", então nada será feito
            limparCampos();
            carregarTabelaCr();
        }
    }

    private List<Centro_resultado> liscr = new ArrayList<>();
    private ObservableList<Centro_resultado> observablelistliscr = FXCollections.observableArrayList();

    @FXML
    private void carregarTabelaCr() {
        crDAO crdao = new crDAO();
        liscr.clear();
        liscr.addAll(crdao.getCrs());
        observablelistliscr.setAll(liscr);
        tabelaCadastroCr.setItems(observablelistliscr);

        colunaCod.setCellValueFactory(new PropertyValueFactory<>("codigo_cr"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("sigla"));
        colunaSigla.setCellValueFactory(new PropertyValueFactory<>("nome"));

    }

    @FXML
    void BotaoGerir(ActionEvent event) {
        System.out.println("Gerir");
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
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    private void GestaoUsuarios(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CadastroUsuarioADM.fxml"));
        Parent root = loader.load();
        Scene cena = new Scene(root);
        Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        stage.setScene(cena);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    private void GestaoClientes(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CadastroClienteADM.fxml"));
        Parent root = loader.load();
        Scene cena = new Scene(root);
        Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        stage.setScene(cena);
        stage.centerOnScreen();
        stage.show();
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
    void AprovarHoras(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Em progresso");
        alert.setHeaderText(null);
        alert.setContentText("Desculpe o transtorno, estamos sempre trabalhando em melhorias");
        alert.showAndWait();
    }

    @FXML
    void Relatorios(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Em progresso");
        alert.setHeaderText(null);
        alert.setContentText("Desculpe o transtorno, estamos sempre trabalhando em melhorias");
        alert.showAndWait();
    }
}
