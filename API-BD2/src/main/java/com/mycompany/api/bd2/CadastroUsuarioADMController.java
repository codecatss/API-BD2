/*
 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.api.bd2;

import com.mycompany.api.bd2.daos.usuarioDAO;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import com.mycompany.api.bd2.models.Usuario;
import java.io.IOException;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author conta
 */
public class CadastroUsuarioADMController {

    @FXML
    private Tooltip passaNome;
    @FXML
    private TableView<Usuario> tabelaCadastroUsuarios;
    @FXML
    private TableColumn<?, ?> colunaNome;
    @FXML
    private TableColumn<?, ?> colunaSenha;
    @FXML
    private TableColumn<?, ?> colunaUsername;
    @FXML
    private TableColumn<?, ?> colunaFuncao;
    @FXML
    private TableColumn<?, ?> colunaStatus;
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
    private TextField entradaNome;
    @FXML
    private TextField entradaUsername;
    @FXML
    private PasswordField entradaSenha;
    @FXML
    private TextField entradaMatricula;
    @FXML
    private ComboBox<String> selecaoFuncao;
    @FXML
    private ComboBox<String> selecaoStatus;
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

    private List<String> obs = new ArrayList<>();
    private ObservableList<String> opcoes = FXCollections.observableArrayList();

    String valorDoItemSelecionado;

    private List<String> obs2 = new ArrayList<>();
    private ObservableList<String> opcoes2 = FXCollections.observableArrayList();

    private List<Usuario> lisusuarios = new ArrayList<>();
    private ObservableList<Usuario> observablelistusuario = FXCollections.observableArrayList();

    public void initialize() {
        nomeUsuario.setText(new Usuario().getUsername());
        //nomeUsuario.setText("*nome do usuário*");
        // Desabilita os botões "Editar", "Inativar" e "Ativar" no início
        botaoEditar.setDisable(true);
        botaoInativar.setDisable(true);
        botaoAtivar.setDisable(true);

        botaoLimpar.setOnAction(event -> limparCampos());
        carregarTabelaUsuario();

        tabelaCadastroUsuarios.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> {
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

        tabelaCadastroUsuarios.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) { // Verifica se é um único clique
                    Usuario item = tabelaCadastroUsuarios.getSelectionModel().getSelectedItem(); // Obtém o item selecionado
                    valorDoItemSelecionado = item.getUsername();
                    entradaNome.setText(item.getNome());
                    entradaUsername.setText(item.getUsername());
                    selecaoFuncao.setValue(item.getCargo());
                    entradaSenha.setText(item.getSenha());
                    selecaoStatus.setValue(item.getStatus());
                }
            }
        });

    }

    @FXML
    private void BotaoAdicionar(ActionEvent event) {
        lisusuarios.clear();
        System.out.println("click");
        Usuario usuario = new Usuario();
        usuarioDAO usuarioDao = new usuarioDAO();
        String nome = entradaNome.getText();
        String username = entradaUsername.getText();
        String funcao = selecaoFuncao.getSelectionModel().getSelectedItem();
        String senha = entradaSenha.getText();
        usuario.setUsername(username);
        usuario.setNome(nome);
        usuario.setSenha(senha);
        usuario.setCargo(funcao);
        usuario.setStatus("ativo");
        //usuario.setHash(senha);
        usuarioDao.save(usuario);
        carregarTabelaUsuario();
    }

    @FXML
    private void BotaoInativar(ActionEvent event) {
        lisusuarios.clear();
        System.out.println("click");
        usuarioDAO usuarioDao = new usuarioDAO();
        Usuario usuario = new Usuario();

        String cargo = usuarioDao.getUsuarioByUsername(valorDoItemSelecionado).getCargo();
        String nome = usuarioDao.getUsuarioByUsername(valorDoItemSelecionado).getNome();
        String senha = usuarioDao.getUsuarioByUsername(valorDoItemSelecionado).getSenha();
        usuario.setUsername(valorDoItemSelecionado);
        usuario.setCargo(cargo);
        usuario.setNome(nome);
        usuario.setSenha(senha);
        usuario.setStatus("inativo");
        usuarioDao.update(usuario);
        carregarTabelaUsuario();

    }

    @FXML
    private void BotaoAtivar(ActionEvent event) {

        System.out.println("click");
        usuarioDAO usuarioDao = new usuarioDAO();
        Usuario usuario = usuarioDao.getUsuarioByUsername(valorDoItemSelecionado);
        usuario.setStatus("ativo");
        usuarioDao.update(usuario);
        lisusuarios.clear();
        carregarTabelaUsuario();
    }

    @FXML
    public void carregarTabelaUsuario() {

        usuarioDAO usuariodao = new usuarioDAO();

        lisusuarios.addAll(usuariodao.getUsuarios());
        observablelistusuario.setAll(lisusuarios);
        tabelaCadastroUsuarios.setItems(observablelistusuario);

        colunaUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaSenha.setCellValueFactory(new PropertyValueFactory<>("senha"));
        colunaFuncao.setCellValueFactory(new PropertyValueFactory<>("cargo"));
        colunaStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

    }

    @FXML

    private void BotaoEditar(ActionEvent event) {
        if (valorDoItemSelecionado == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Usuário não selecionado");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione um usuário na tabela para editar.");
            alert.showAndWait();
        } else {
            String nome = entradaNome.getText().trim();
            String funcao = selecaoFuncao.getSelectionModel().getSelectedItem();
            String senha = entradaSenha.getText().trim();
            String status = selecaoStatus.getSelectionModel().getSelectedItem();

            if (nome.isEmpty() || senha.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Campos obrigatórios");
                alert.setHeaderText(null);
                alert.setContentText("Por favor, preencha todos os campos obrigatórios (*).");
                alert.showAndWait();
            } else {
                lisusuarios.clear();

                Usuario usuario = new Usuario();
                usuario.setNome(nome);
                usuario.setUsername(valorDoItemSelecionado);
                usuario.setSenha(senha);
                usuario.setCargo(funcao);
                usuario.setStatus(status);

                usuarioDAO usuarioDao = new usuarioDAO();
                usuarioDao.update(usuario);
                limparCampos();
                System.out.println("Tabela Limpa");
                carregarTabelaUsuario();

            }
        }
    }

    @FXML
    public void limparCampos() {
        entradaNome.clear();
        entradaUsername.clear();
        selecaoFuncao.setValue(null);
        selecaoStatus.setValue(null);
        entradaSenha.clear();
        botaoAdicionar.setDisable(true);

        // Desseleciona a linha da tabela
        tabelaCadastroUsuarios.getSelectionModel().clearSelection();
    }

    public void tipoFuncao() {
        obs.clear();
        obs.add("admin");
        obs.add("gestor");
        obs.add("colaborador");
        opcoes.setAll(obs);
        selecaoFuncao.setItems(opcoes);
    }

    public void tipoStatus() {
        obs2.clear();
        obs2.add("ativo");
        obs2.add("inativo");
        opcoes2.setAll(obs2);
        selecaoStatus.setItems(opcoes2);
    }

    @FXML

    private void botaoSair(ActionEvent event) throws IOException {
        Usuario usuario = new Usuario();
        usuario.logout();
        System.out.println("sair");

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.2), ((Node) event.getSource()).getScene().getRoot());
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setOnFinished((ActionEvent event1) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaLogin.fxml"));
                Parent root = loader.load();
                Scene cena = new Scene(root);
                Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
                stage.setScene(cena);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(CadastroUsuarioADMController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        fadeOut.play();
    }

    @FXML
    void GestaoClientes(ActionEvent event) throws IOException {
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.1), ((Node) event.getSource()).getScene().getRoot());
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setOnFinished((ActionEvent event1) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CadastroClienteADM.fxml"));
                Parent root = loader.load();
                Scene cena = new Scene(root);
                Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
                stage.setScene(cena);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(CadastroUsuarioADMController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        fadeOut.play();
    }

}
