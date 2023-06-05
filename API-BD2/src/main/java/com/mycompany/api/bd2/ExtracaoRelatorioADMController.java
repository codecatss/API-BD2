package com.mycompany.api.bd2;

import Conexao.Conexao;
import com.mycompany.api.bd2.daos.horaDAO;
import com.mycompany.api.bd2.daos.integranteDAO;
import com.mycompany.api.bd2.models.Hora;
import com.mycompany.api.bd2.models.StatusAprovacao;
import com.mycompany.api.bd2.models.Usuario;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ExtracaoRelatorioADMController {
    @FXML
    private Button BotaoExtrair;

    @FXML
    private Button BotaoSair;

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
    private ComboBox<String> comboboxStatus;

    @FXML
    private DatePicker DataFim;

    @FXML
    private DatePicker DataInicio;

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
    private TableView<Hora> tabelaRelatorio;

    private String usuario = TelaLoginController.usuariologado.getUsername();
    private List<String> tiporelatorio = new ArrayList<>();
    private ObservableList<String> opcoes = FXCollections.observableArrayList();
    public void initialize() {
        nomeUsuario.setText(usuario);
        menuRelatorio.setDisable(true);
        tiporelatorio.add("Todos");
        tiporelatorio.add("Pendente");
        tiporelatorio.add("Aprovado");
        tiporelatorio.add("Negado");
        opcoes.addAll(tiporelatorio);
        comboboxStatus.setItems(opcoes);
        carregarTabelaLancamento();
    }
    
    private String tipo=null;
    @FXML
    private String statusRelatorio() {
        tipo = null;
        tipo = comboboxStatus.getValue();
        carregarTabelaLancamento();
        return tipo;
    }
    
    @FXML
    public void gerarRelatorio() throws Exception{
        
        Conexao conexao = new Conexao();
        Calendar data = Calendar.getInstance();
        SimpleDateFormat formatadorData = new SimpleDateFormat("dd.MM.yyyy");
        String formData = formatadorData.format(data.getTime());
        
        if(tipo==null||DataInicio.getValue() == null || DataFim.getValue()==null){
            if(tipo==null)erro("tipo");
            
            if(DataInicio.getValue() == null || DataFim.getValue()==null) erro("de início e de fim");
        }
        else{
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            if(tipo.equals("Todos")){
            conexao.gerarRelatorioCSV(formData,tipo,"SELECT * FROM 2rp.hora "
            + "WHERE data_hora_inicio BETWEEN '"+DataInicio.getValue().format(formato)+"' AND '"+DataFim.getValue().format(formato)+
            "'AND data_hora_fim BETWEEN '"+DataInicio.getValue().format(formato)+"' AND '"+DataFim.getValue().format(formato)+"'"); 
            }
            else{
            
            conexao.gerarRelatorioCSV(formData,tipo,"SELECT * FROM 2rp.hora WHERE status_aprovacao = '"+tipo.toLowerCase()+"' "
            + "AND data_hora_inicio BETWEEN '"+DataInicio.getValue().format(formato)+"' AND '"+DataFim.getValue().format(formato)+
            "'AND data_hora_fim BETWEEN '"+DataInicio.getValue().format(formato)+"' AND '"+DataFim.getValue().format(formato)+"'");           

            }

            exibirPopUpAviso("Arquivo extraído com sucesso!");
        }
    }

    private void exibirPopUpAviso(String mensagem) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
    
    private void erro(String motivo){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Alguns dos campos não foram preenchido");
        alert.setHeaderText(null);
        alert.setContentText("Preencha o campo "+ motivo);
        alert.showAndWait();   
    }
    
    @FXML
    private void botaoSair(ActionEvent event) throws IOException {
        Usuario usuario = new Usuario();
        usuario.logout();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaLogin.fxml"));
        Parent root = loader.load();
        Scene cena = new Scene(root);
        Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        stage.setScene(cena);
        stage.show();
    }
    @FXML
    private void fecharTela(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void botaoMinimizar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
    
    @FXML
    private void GestaoUsuarios(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CadastroUsuarioADM.fxml"));
        Parent root = loader.load();
        Scene cena = new Scene(root);
        Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        stage.setScene(cena);
        stage.show();
    }   
    
    @FXML
    private void GestaoCRs(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CadastroCRADM.fxml"));
        Parent root = loader.load();
        Scene cena = new Scene(root);
        Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        stage.setScene(cena);
        stage.show();
    }  
    
    @FXML
    private void GestaoClientes(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CadastroClienteADM.fxml"));
        Parent root = loader.load();
        Scene cena = new Scene(root);
        Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        stage.setScene(cena);
        stage.show();
    }    
    
    @FXML
    private void AprovarHoras(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AprovacaoADM.fxml"));
        Parent root = loader.load();
        Scene cena = new Scene(root);
        Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        stage.setScene(cena);
        stage.show();
    }
    
    @FXML
    private void boxIni(){
        carregarTabelaLancamento();
    }
    
    @FXML
    private void boxFim(){
        carregarTabelaLancamento();
    }
    
    private integranteDAO crgestor = new integranteDAO();
    private List<Hora> lishoras = new ArrayList<>();
    private ObservableList<Hora> observablelisthoras = FXCollections.observableArrayList();
    @FXML
    public void carregarTabelaLancamento() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String ini = "";
        String fim = "";
        if(DataInicio.getValue() != null)ini = DataInicio.getValue().format(formato);
        else{
            LocalDate hoje = LocalDate.now();
            LocalDate segundaFeira = hoje.with(DayOfWeek.MONDAY);
            ini = segundaFeira.format(formato);
            DataInicio.setValue(segundaFeira);
        }
        if(DataFim.getValue() != null)fim = DataFim.getValue().format(formato);
        else{
            LocalDate hoje = LocalDate.now();
            // Obtém o último dia da semana (sexta-feira)
            LocalDate domingo = hoje.with(DayOfWeek.SUNDAY);
            fim = domingo.format(formato);
            DataFim.setValue(domingo);
        }
        horaDAO horadao = new horaDAO();
        
        lishoras.clear();
        StatusAprovacao tipo = StatusAprovacao.todos;
        if(comboboxStatus.getValue()!=null){
            if(comboboxStatus.getValue().equals("Aprovado")){
                tipo = StatusAprovacao.aprovado_gestor;
            }
            if(comboboxStatus.getValue().equals("Negado")){
                tipo = StatusAprovacao.negado_gestor;
            }
            if(comboboxStatus.getValue().equals("Pendente")){
                tipo = StatusAprovacao.pendente;
            }
        }
        lishoras.addAll(horadao.getHora(tipo, ini, fim));
        observablelisthoras.setAll(lishoras);
        tabelaRelatorio.setItems(observablelisthoras);
        colunaColaborador.setCellValueFactory(new PropertyValueFactory<>("username_lancador"));
        colunaCR.setCellValueFactory(new PropertyValueFactory<>("nome_cliente"));
        colunaInicio.setCellValueFactory(new PropertyValueFactory<>("data_hora_inicio"));
        colunaFim.setCellValueFactory(new PropertyValueFactory<>("data_hora_fim"));
        colunaJustificativa.setCellValueFactory(new PropertyValueFactory<>("justificativa_lancamento"));
        colunaEmpresa.setCellValueFactory(new PropertyValueFactory<>("nome_cliente"));
        colunaProjeto.setCellValueFactory(new PropertyValueFactory<>("projeto"));
        colunaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colunaStatus.setCellValueFactory(new PropertyValueFactory<>("status_aprovacao"));

        tabelaRelatorio.refresh();
    }
}
