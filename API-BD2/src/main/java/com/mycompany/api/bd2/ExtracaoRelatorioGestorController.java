package com.mycompany.api.bd2;

import Conexao.Conexao;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

public class ExtracaoRelatorioGestorController {

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
    private Button menuApontamento;

    @FXML
    private Button menuLancamento;

    @FXML
    private Button menuRelatorio;

    @FXML
    private Button minimizarTela;

    @FXML
    private Label nomeUsuario;

    @FXML
    private Label nometelaatual;

    @FXML
    private TableView<?> tabelaRelatorio;

    @FXML
    void BotaoSair(ActionEvent event) {

    }

    @FXML
    void RelatorioCSV(ActionEvent event) {

    }

    @FXML
    void botaoExtrair(ActionEvent event) {

    }

    @FXML
    void fechaTela(ActionEvent event) {

    }

    @FXML
    void navLancamentoColaborador(ActionEvent event) {

    }
    @FXML
    public void gerarRelatorio() throws Exception{
        Conexao conexao = new Conexao();
        Calendar data = Calendar.getInstance();
        SimpleDateFormat formatadorData = new SimpleDateFormat("dd.MM.yyyy");
        String formData = formatadorData.format(data.getTime());

        conexao.gerarRelatorioCSV(formData,"SELECT * FROM 2rp.hora");
    }
}
