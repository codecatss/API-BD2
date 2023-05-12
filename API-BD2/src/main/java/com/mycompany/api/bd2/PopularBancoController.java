/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.bd2;

import com.mycompany.api.bd2.models.Centro_resultado;
import com.mycompany.api.bd2.models.Integrante;
import com.mycompany.api.bd2.models.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

/**
 *
 * @author larissa
 */
public class PopularBancoController {

    @FXML
    private Button botaoAdicionar;

    @FXML
    private Button botaoExcluir;

    @FXML
    private Button botaoGestor;

    @FXML
    private TableColumn<?, ?> colunaFuncao;

    @FXML
    private TableColumn<?, ?> colunaUsuario;

    @FXML
    private Button fecharTela;

    @FXML
    private Button minimizarTela;

    @FXML
    private Label nometelaatual;

    @FXML
    private TableView<Integrante> tabelaIntegrantes;

    @FXML
    private TableView<Usuario> tabelaUsuarios;

    @FXML
    private TableColumn<?, ?> colunaUserDisp;

    private Centro_resultado crSelecionado = CadastroCRADMController.crInfo;

    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    void BotaoAdicionar(ActionEvent event) {
        System.out.println(crSelecionado.getNome());
        System.out.println(crSelecionado.getSigla());

    }

    @FXML
    void BotaoExcluir(ActionEvent event) {

    }

}
