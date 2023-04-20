/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.api.bd2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

/**
 * FXML Controller class
 *
 * @author nicol
 */
public class GestorAprovacaoController implements Initializable {

    @FXML
    private Button para_aprovacao;
    @FXML
    private Label usuario;
    @FXML
    private Label CR_FUNCAO;
    @FXML
    private Button relatorios_gestor;
    @FXML
    private Button botaoSair;
    @FXML
    private Button mudarCR;
    @FXML
    private Button meus_apontamentos;
    @FXML
    private TableColumn<?, ?> tabela_aprovacao;
    @FXML
    private Button botaoAprovar;
    @FXML
    private Button botaoReprovar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
