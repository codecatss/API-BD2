/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.api.bd2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;

/**
 * FXML Controller class
 *
 * @author conta
 */
public class PopUpAcionamentoController implements Initializable {

    @FXML
    private Button fecharTela;
    @FXML
    private Button minimizarTela;
    @FXML
    private Label nometelaatual;
    @FXML
    private Spinner<?> horaInicio;
    @FXML
    private Spinner<?> minutoInicio;
    @FXML
    private Spinner<?> horaFim;
    @FXML
    private Spinner<?> minutoFim;
    @FXML
    private TableColumn<?, ?> colunaAcionamento;
    @FXML
    private TableColumn<?, ?> colunaInicio;
    @FXML
    private TableColumn<?, ?> colunaFim;
    @FXML
    private Button botaoSalvar;
    @FXML
    private Button botaoExcluir;
    @FXML
    private Button botaoAdicionar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void botaoAdicionar(ActionEvent event) {
    }

    @FXML
    private void limparCampos(ActionEvent event) {
    }
    
}
