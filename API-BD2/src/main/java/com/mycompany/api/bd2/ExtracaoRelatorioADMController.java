package com.mycompany.api.bd2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

public class ExtracaoRelatorioADMController {
    @FXML
    private Button botaoExtrair;

    @FXML
    private Button botaoSair;

    @FXML
    private TableColumn<?, ?> colunaCR;

    @FXML
    private TableColumn<?, ?> colunaColaborador;

    @FXML
    private TableColumn<?, ?> colunaEmpresa;

    @FXML
    private TableColumn<?, ?> colunaFim;

    @FXML
    private TableColumn<?, ?> colunaGestor;

    @FXML
    private TableColumn<?, ?> colunaInicio;

    @FXML
    private TableColumn<?, ?> colunaJustificativa;

    @FXML
    private TableColumn<?, ?> colunaProjeto;

    @FXML
    private TableColumn<?, ?> colunaStatus;

    @FXML
    private TableColumn<?, ?> colunaTipo;

    @FXML
    private ComboBox<?> comboboxStatus;

    @FXML
    private DatePicker dataFimRelatorio;

    @FXML
    private DatePicker dataInicioRelatorio;

    @FXML
    private Button fecharTela;

    @FXML
    private ImageView imagemUser;

    @FXML
    private Button menuAprovar;

    @FXML
    private Button menuCR;

    @FXML
    private Button menuCliente;

    @FXML
    private Button menuRelatorio;

    @FXML
    private Button menuUsuario;

    @FXML
    private Button minimizarTela;

    @FXML
    private Label nomeUsuario;

    @FXML
    private Label nometelaatual;

    @FXML
    private TableView<?> tabelaRelatorio;

    @FXML
    void RelatorioCSV(ActionEvent event) {

    }

    @FXML
    void botaoExtrair(ActionEvent event) {

    }
}
