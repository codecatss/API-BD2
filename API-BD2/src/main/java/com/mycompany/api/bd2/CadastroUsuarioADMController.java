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
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


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
    
    private List<String> obs2 = new ArrayList<>();
    private ObservableList<String> opcoes2 = FXCollections.observableArrayList();
    
    
    private List<Usuario> lisusuarios = new ArrayList<>();
    private ObservableList<Usuario> observablelistusuario = FXCollections.observableArrayList();
    
    public void initialize() {
        nomeUsuario.setText("*nome do usuário*");
        botaoLimpar.setOnAction(event -> limparCampos());
        carregarTabelaUsuario();
    }
  

    @FXML
    private void BotaoAdicionar(ActionEvent event) {
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
        usuario.setHash(senha);
        
        
        usuarioDao.save(usuario);
    }
    @FXML
    public void handleTooltip(ActionEvent event){
 
    }
    @FXML
    public void carregarTabelaUsuario(){
        
        usuarioDAO usuariodao = new usuarioDAO();
        
        lisusuarios.addAll(usuariodao.getUsuarios());
        observablelistusuario.setAll(lisusuarios);
        tabelaCadastroUsuarios.setItems(observablelistusuario);

        colunaUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaSenha.setCellValueFactory(new PropertyValueFactory<>("hash"));
        colunaFuncao.setCellValueFactory(new PropertyValueFactory<>("cargo"));
        colunaStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
         
         
        
        
    }
    
    @FXML
    public void limparCampos(){
        selecaoFuncao.getSelectionModel().clearSelection();        
    }
    
    
    public void tipoFuncao(){
        obs.clear();
        
        obs.add("admin");
        obs.add("gestor");
        obs.add("colaborador");
        opcoes.setAll(obs);
        selecaoFuncao.setItems(opcoes);
    }
    
    public void tipoStatus(){
        obs2.clear();
        
        obs2.add("ativo");
        obs2.add("inativo");
        opcoes2.setAll(obs2);
        selecaoStatus.setItems(opcoes2);
    }
    
}