/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.api.bd2;

import com.mycompany.api.bd2.daos.horaDAO;
import com.mycompany.api.bd2.models.Hora;
import com.mycompany.api.bd2.models.TipoHora;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    private Spinner<Integer> horaInicio;
    @FXML
    private Spinner<Integer> minutoInicio;
    @FXML
    private Spinner<Integer> horaFim;
    @FXML
    private Spinner<Integer> minutoFim;
    @FXML
    private TableView<Hora> tabelaAcionamento;
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
    @FXML
    private DatePicker dataFimAc;
    @FXML
    private DatePicker dataInicioAc;

    private Hora sobreaviso = LancamentoColaboradorController.getHora();
    private LocalDate dtInicioSA = sobreaviso.getData_hora_inicio().toLocalDateTime().toLocalDate();
    private LocalDate dtFimSA = sobreaviso.getData_hora_fim().toLocalDateTime().toLocalDate();

    private LocalDate inicioAcionamento;
    private LocalDate fimAcionamento;

    private Hora horaCapturada; // Atributo para armazenar a instância de Hora capturada
    ObservableList<Hora> valorDoItemSelecionado;
    int index;
    private List<Hora> lantemp = new LinkedList<Hora>();
    private static List<Hora> acionamentos = new LinkedList<Hora>();

    public void setHoraCapturada(Hora hora) {
        this.horaCapturada = hora;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        horaInicio.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0, 1));
        minutoInicio.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
        horaInicio.getValueFactory().setWrapAround(true);
        minutoInicio.getValueFactory().setWrapAround(true);

        horaFim.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0, 1));
        minutoFim.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
        horaFim.getValueFactory().setWrapAround(true);
        minutoFim.getValueFactory().setWrapAround(true);

        acionamentos.clear();
        lantemp.clear();
        contagem = 1;
        
        
        dataInicioAc.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.compareTo(dtInicioSA) < 0 || date.compareTo(dtFimSA) > 0);
            }
        });

        dataFimAc.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.compareTo(dtInicioSA) < 0 || date.compareTo(dtFimSA) > 0);
            }
        });

        tabelaAcionamento.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) { // Verifica se é um único clique
                    valorDoItemSelecionado = tabelaAcionamento.getSelectionModel().getSelectedItems(); // Obtém os itens selecionados

                    index = tabelaAcionamento.getItems().indexOf(valorDoItemSelecionado);

                    System.out.println(valorDoItemSelecionado);
                }
            }
        });

        carregarTabelaAcionamento();

        //botaoLimpar.setOnAction(event -> limparCampos());
    }

    private static int contagem = 1;

    @FXML
    private void botaoAdicionar() throws ParseException {
        // Obtém os valores de data de inicio e de fim (campos de entrada)
        inicioAcionamento = dataInicioAc.getValue();
        fimAcionamento = dataFimAc.getValue();

        // Verifica se as datas (NÃO) foram preenchidas
        if (inicioAcionamento == null || fimAcionamento == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Preencha todos os campos");
            alert.setHeaderText(null);
            alert.setContentText("Alguns dos campos não foram preenchidos");
            alert.showAndWait();
        } else {
            Hora horaExtra = new Hora();

            // Preenche os dados que vêm do lançamento do sobreaviso
            horaExtra.setCnpj_cliente(sobreaviso.getCnpj_cliente());
            horaExtra.setCod_cr(sobreaviso.getCod_cr());
            horaExtra.setJustificativa_lancamento(sobreaviso.getJustificativa_lancamento());
            horaExtra.setNome_cliente(sobreaviso.getNome_cliente());
            horaExtra.setProjeto(sobreaviso.getProjeto());
            horaExtra.setUsername_aprovador(sobreaviso.getUsername_aprovador());
            horaExtra.setUsername_lancador(sobreaviso.getUsername_lancador());
            horaExtra.setTipo(sobreaviso.getTipo());
            horaExtra.setStatus_aprovacao(sobreaviso.getStatus_aprovacao());
            horaExtra.setSolicitante(sobreaviso.getSolicitante());

            // Formata as strings de inicioAcionamento e fimAcionamento
            String dtInicioAc = inicioAcionamento.toString();
            String dtFimAc = fimAcionamento.toString();

            // Obtém os valores de hora e minuto de inicio (campos de entrada)
            int hora_inicio = horaInicio.getValue();
            int min_inicio = minutoInicio.getValue();

            // Formata as strings de hora_inicio e min_inicio
            String hora_inicioS = Integer.toString(hora_inicio);
            String min_inicioS = Integer.toString(min_inicio);

            if (min_inicioS.length() < 2) {
                min_inicioS = "0" + min_inicioS;
            }
            if (hora_inicioS.length() < 2) {
                hora_inicioS = "0" + hora_inicioS;
            }

            // Concatena as strings de hora e minuto iniciais
            hora_inicioS = hora_inicioS + ":" + min_inicioS + ":00";

            // Concatena as strings de data de inicio e hora de inicio
            String data_hora_inicio = dtInicioAc + " " + hora_inicioS;

            // Preenche a data e a hora de inicio no objeto horaExtra
            horaExtra.setData_hora_inicio(data_hora_inicio);

            // Obtém os valores de hora e minuto de fim (campos de entrada)
            int hora_fim = horaFim.getValue();
            int min_fim = minutoFim.getValue();

            // Formata as strings de hora_fim e min_fim
            String hora_fimS = Integer.toString(hora_fim);
            String min_fimS = Integer.toString(min_fim);
            if (min_fimS.length() < 2) {
                min_fimS = "0" + min_fimS;
            }
            if (hora_fimS.length() < 2) {
                hora_fimS = "0" + hora_fimS;
            }

            // Concatena as strings de hora e minuto finais
            hora_fimS = hora_fimS + ":" + min_fimS + ":00";

            // Concatena as strings de data de inicio e hora finais
            String data_hora_fim = dtFimAc + " " + hora_fimS;

            // Preenche a data e a hora de fim no objeto horaExtra
            horaExtra.setData_hora_fim(data_hora_fim);

            int resultInicio = horaExtra.getData_hora_inicio().compareTo(sobreaviso.getData_hora_inicio());
            int resultFim = horaExtra.getData_hora_fim().compareTo(sobreaviso.getData_hora_fim());
            int resultHoraExtra = horaExtra.getData_hora_inicio().compareTo(horaExtra.getData_hora_fim());

            // Verifica se a hora-extra informada está dentro do intervalo de sobreaviso
            if (resultInicio >= 0 && resultFim <= 0) {
                if (resultHoraExtra < 0) {

                    boolean conflito = false;
                    for (Hora horaExistente : lantemp) {
                        Timestamp inicio = horaExistente.getData_hora_inicio();
                        Timestamp fim = horaExistente.getData_hora_fim();

                        int resultIniIni = horaExtra.getData_hora_inicio().compareTo(inicio);
                        int resultFimFim = horaExtra.getData_hora_fim().compareTo(fim);
                        int resultIniFim = horaExtra.getData_hora_inicio().compareTo(fim);
                        int resultFimIni = horaExtra.getData_hora_fim().compareTo(inicio);

                        if ((resultIniIni > 0 && resultIniFim < 0)
                                || (resultFimIni > 0 && resultFimFim < 0)
                                || (resultIniIni < 0 && resultFimFim > 0)
                                || resultIniIni == 0 || resultFimFim == 0) {
                            conflito = true;
                            break;
                        }
                    }

                    if (!conflito) {
                        horaExtra.setTipo(TipoHora.EXTRA.name());
                        horaExtra.setId(lantemp.size() + 1);
                        contagem++;
                        lantemp.add(horaExtra);
                        carregarTabelaAcionamento();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Conflito de horas");
                        alert.setHeaderText(null);
                        alert.setContentText("A hora informada está em conflito com uma hora já adicionada.");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Hora-extra incompatível");
                    alert.setHeaderText(null);
                    alert.setContentText("O início da hora-extra deve ser antes do fim");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Hora-extra fora do intervalo");
                alert.setHeaderText(null);
                alert.setContentText("A hora-extra informada precisa estar dentro do intervalo de data do sobreaviso.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void botaoSalvar() throws ParseException {
        horaDAO hrDAO = new horaDAO();
        
        hrDAO.save(sobreaviso);
        System.out.println("Adicionado" + sobreaviso.getData_hora_inicio() + " até " + sobreaviso.getData_hora_fim());
        
        acionamentos.addAll(lantemp);
        for (Hora hora : acionamentos) {
            hrDAO.save(hora);
            System.out.println("Adicionado " + hora.getData_hora_inicio() + " até " + hora.getData_hora_fim());
        }

        Stage stage = (Stage) botaoSalvar.getScene().getWindow();
        stage.close();
    }

    private ObservableList<Hora> observablelisthoras = FXCollections.observableArrayList();

    @FXML
    private void carregarTabelaAcionamento() {
        for (Hora hora : lantemp) {
            hora.setId(lantemp.indexOf(hora) + 1);
        }
        observablelisthoras.clear();
        observablelisthoras.setAll(lantemp);
        tabelaAcionamento.setItems(observablelisthoras);
        colunaAcionamento.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaInicio.setCellValueFactory(new PropertyValueFactory<>("data_hora_inicio"));
        colunaFim.setCellValueFactory(new PropertyValueFactory<>("data_hora_fim"));

    }

    @FXML
    private void limparCampos(ActionEvent event) throws ParseException {
        horaInicio.getValueFactory().setValue(0);
        minutoInicio.getValueFactory().setValue(0);
        horaFim.getValueFactory().setValue(0);
        minutoFim.getValueFactory().setValue(0);

        if (!valorDoItemSelecionado.isEmpty()) {
            lantemp.removeAll(valorDoItemSelecionado);
            tabelaAcionamento.getItems().removeAll(valorDoItemSelecionado); // Remove os itens selecionados da lista da tabela
        }
        carregarTabelaAcionamento();
    }

    public static List<Hora> getAcionamentos() {
        return acionamentos;
    }

}
