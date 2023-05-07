/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.api.bd2;

import static com.mycompany.api.bd2.TelaLoginController.usuariologado;
import com.mycompany.api.bd2.models.Hora;
import com.mycompany.api.bd2.models.Usuario;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author csous
 */
public class ApontamentoGestor implements Initializable {

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
    private Button menuLancamento;
    @FXML
    private Button menuApontamento;
    @FXML
    private Button menuRelatorio;
    @FXML
    private TableColumn<?, ?> tabela_aprovacao;
    @FXML
    private Button botaoAprovar1;
    @FXML
    private Button botaoReprovar1;

    @FXML
    private TableView<Hora> tabelaApontamento;

    @FXML
    private TableColumn<Hora, String> colunaCR;

    @FXML
    private TableColumn<Hora, String> colunaEmpresa;

    @FXML
    private TableColumn<Hora, String> colunaFunçao;

    @FXML
    private TableColumn<Hora, String> colunaGestor;

    @FXML
    private TableColumn<Hora, String> colunaNome;

    @FXML
    private TableColumn<Hora, String> colunaProjeto;

    @FXML
    private TableColumn<Hora, String> colunaUsername;
    @FXML
    private TableColumn<Hora, Timestamp> colunaFim;
    @FXML
    private TableColumn<Hora, Timestamp> colunaInicio;

    
    
    public static Usuario getUsuario1() {
        return usuariologado;
    }
    public static Usuario usuariologado = new Usuario();
    private String usuario = TelaLoginController.usuariologado.getUsername();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nomeUsuario.setText(usuario);
    }

    public void fechaTela() {
        Platform.exit();
    }

    public class ExportaCSV extends App {

        private static final String CSV_FILE = "Relatorio.csv";

    }
    /*
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        TableView<Person> table = new TableView<>(); // tabela para exibir dados
        
        TableColumn<Person, String> firstNameCol = new TableColumn<>("First Name"); // coluna para exibir primeiro nome
        firstNameCol.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        
        TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name"); // coluna para exibir sobrenome
        lastNameCol.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        
        TableColumn<Person, Integer> ageCol = new TableColumn<>("Age"); // coluna para exibir idade
        ageCol.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asObject());
        
        table.getColumns().addAll(firstNameCol, lastNameCol, ageCol); // adicionando colunas à tabela
        
        ObservableList<Person> data = readCsv(); // lendo dados do arquivo CSV e convertendo em objetos
        
        table.setItems(data); // exibindo dados na tabela
        
        BorderPane root = new BorderPane(table);
        Scene scene = new Scene(root, 400, 300);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private ObservableList<Person> readCsv() throws IOException {
        ObservableList<Person> data = FXCollections.observableArrayList(); // lista para armazenar objetos criados
        
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(","); // dividindo linha em campos
                String firstName = fields[0];
                String lastName = fields[1];
                int age = Integer.parseInt(fields[2]);
                data.add(new Person(firstName, lastName, age)); // adicionando objeto à lista
            }
        }
        
        return data;
    }
     */
}
