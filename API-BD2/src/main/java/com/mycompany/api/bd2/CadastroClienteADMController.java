/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.api.bd2;

import com.mycompany.api.bd2.daos.clienteDAO;
import com.mycompany.api.bd2.models.Cliente;
import com.mycompany.api.bd2.models.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
 * @author CodeCats
 */
public class CadastroClienteADMController {

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
    private TextField entradaCNPJ;
    @FXML
    private TextField entradaRS;
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
    private TableView<Cliente> tabelaCadastroCliente;
    @FXML
    private TableColumn<?, ?> colunaCNPJ;
    @FXML
    private TableColumn<?, ?> colunaRS;
    @FXML
    private TableColumn<?, ?> colunaStatus;

    private String usuario = TelaLoginController.usuariologado.getUsername();

    private List<String> obs = new ArrayList<>();
    private ObservableList<String> opcoes = FXCollections.observableArrayList();
    private List<Cliente> lisClientes = new ArrayList<>();
    private ObservableList<Cliente> observableListCliente = FXCollections.observableArrayList();

    long valorDoItemSelecionado;

    public void initialize() {
        nomeUsuario.setText(usuario);
        // Desabilita os botões "Editar", "Inativar" e "Ativar" no início
        botaoEditar.setDisable(true);
        botaoInativar.setDisable(true);
        botaoAtivar.setDisable(true);
        menuCliente.setDisable(true);

        entradaCNPJ.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                entradaCNPJ.setText(oldValue);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Campo inválido");
                alert.setHeaderText(null);
                alert.setContentText("O campo CNPJ deve conter apenas números.");
                alert.showAndWait();
            }
        });

        botaoLimpar.setOnAction(event -> limparCampos());
        carregarTabelaCliente();

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

        tabelaCadastroCliente.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) { // Verifica se é um único clique
                    Cliente item = (Cliente) tabelaCadastroCliente.getSelectionModel().getSelectedItem(); // Obtém o item selecionado
                    valorDoItemSelecionado = item.getCnpj();
                    entradaCNPJ.setText(String.valueOf(item.getCnpj()));
                    entradaRS.setText(item.getRazao_social());
                    System.out.println("Item selecionado: " + valorDoItemSelecionado); // Imprime no console
                }
            }
        });
    }

    @FXML

    public void carregarTabelaCliente() {
        lisClientes.clear();

        clienteDAO clientedao = new clienteDAO();

        lisClientes.addAll(clientedao.getClientes());
        observableListCliente.setAll(lisClientes);
        tabelaCadastroCliente.setItems(observableListCliente);

        colunaCNPJ.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
        colunaRS.setCellValueFactory(new PropertyValueFactory<>("razao_social"));
        colunaStatus.setCellValueFactory(new PropertyValueFactory<>("status_cliente"));
    }

    @FXML
    private void BotaoAdicionar(ActionEvent event) {
        if (entradaCNPJ.getText().isEmpty() || entradaRS.getText().isEmpty()) {
            System.out.println("Preencha todos os campos - cadastro de cliente");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Preencha todos os campos");
            alert.setHeaderText(null);
            alert.setContentText("Alguns dos campos não foi preenchido");
            alert.showAndWait();
        } else {
            Cliente cliente = new Cliente();
            cliente.setCnpj(Integer.parseInt(entradaCNPJ.getText()));
            cliente.setRazao_social(entradaRS.getText());
            cliente.setStatus_cliente("ativo");

            clienteDAO clienteDao = new clienteDAO();
            clienteDao.save(cliente);
            carregarTabelaCliente();
        }
    }

    @FXML
    public void limparCampos() {
        entradaCNPJ.clear();
        entradaRS.clear();
        botaoAdicionar.setDisable(false);
        tabelaCadastroCliente.getSelectionModel().clearSelection();
    }

    @FXML
    private void BotaoEditar(ActionEvent event) {

        // verifica se alguma linha foi selecionada
        if (tabelaCadastroCliente.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText(null);
            alert.setContentText("Tem certeza que deseja atualizar os dados do cliente?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // o usuário clicou em "OK", continue com a ação
                Cliente clienteSelecionado = tabelaCadastroCliente.getSelectionModel().getSelectedItem();

                String novoNome = entradaRS.getText();
                if (!novoNome.isEmpty()) {
                    // atualiza o objeto Cliente com o novo nome
                    clienteSelecionado.setRazao_social(novoNome);

                    // salva o objeto atualizado no banco de dados
                    clienteDAO clienteDao = new clienteDAO();
                    clienteDao.update(clienteSelecionado);

                    // atualiza a tabela com as novas informações
                    carregarTabelaCliente();
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
        // exibe um alerta de confirmação antes de ativar o cliente
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText(null);
        alert.setContentText("Tem certeza que deseja inativar o cliente?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // o usuário clicou em "Ok", então o cliente será ativado
            clienteDAO clientedao = new clienteDAO();
            Cliente cliente = clientedao.getClientebyCNPJ(valorDoItemSelecionado);
            cliente.setStatus_cliente("inativo");

            clientedao.update(cliente);

            carregarTabelaCliente();
            limparCampos();

            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Cliente inativado");
            alert2.setHeaderText(null);
            alert2.setContentText("O cliente foi inativado com sucesso!");
            alert2.showAndWait();
        } else {
            // o usuário clicou em "Cancelar", então nada será feito
            limparCampos();

            carregarTabelaCliente();

        }
    }

    @FXML
    private void BotaoAtivar(ActionEvent event) {
        // exibe um alerta de confirmação antes de ativar o cliente
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText(null);
        alert.setContentText("Tem certeza que deseja ativar o cliente?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // o usuário clicou em "Ok", então o cliente será ativado
            clienteDAO clientedao = new clienteDAO();
            Cliente cliente = clientedao.getClientebyCNPJ(valorDoItemSelecionado);
            cliente.setStatus_cliente("ativo");

            clientedao.update(cliente);

            carregarTabelaCliente();
            limparCampos();

            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Cliente ativado");
            alert2.setHeaderText(null);
            alert2.setContentText("O cliente foi ativado com sucesso!");
            alert2.showAndWait();
        } else {
            // o usuário clicou em "Cancelar", então nada será feito
            limparCampos();

            carregarTabelaCliente();
        }
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
    void GestaoUsuarios(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CadastroUsuarioADM.fxml"));
        Parent root = loader.load();
        Scene cena = new Scene(root);
        Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        stage.setScene(cena);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void GestaoCRs(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CadastroCRADM.fxml"));
        Parent root = loader.load();
        Scene cena = new Scene(root);
        Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        stage.setScene(cena);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void GestaoClientes(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CadastroClienteADM.fxml"));
        Parent root = loader.load();
        Scene cena = new Scene(root);
        Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        stage.setScene(cena);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    public void AprovarHoras(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AprovacaoADM.fxml"));
        Parent root = loader.load();
        Scene cena = new Scene(root);
        Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        stage.setScene(cena);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void Relatorios(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Em progresso");
        alert.setHeaderText(null);
        alert.setContentText("Desculpe o transtorno, estamos sempre trabalhando em melhorias");
        alert.showAndWait();
    }
    
    @FXML
    public void RelatorioCSV() throws Exception {
        TesteGerarRelatorio.gerarRelatorio();
        /*
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ExtracaoRelatorio.fxml"));
        Parent root = loader.load();
        Scene cena = new Scene(root);
        Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        stage.setScene(cena);
        stage.centerOnScreen();
        stage.show();
        */
    }
}
