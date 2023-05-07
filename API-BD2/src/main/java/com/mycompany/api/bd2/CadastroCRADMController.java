/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.api.bd2;

import com.mycompany.api.bd2.daos.crDAO;
import com.mycompany.api.bd2.models.Centro_resultado;
import com.mycompany.api.bd2.models.Hora;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author csous
 */
public class CadastroCRADMController implements Initializable {

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
    private TextField entradaCod;
    @FXML
    private TextField entradaNome;
    @FXML
    private TextField entradaSigla;
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
    @FXML
    private TableView<Centro_resultado> tabelaCadastroCliente;
    @FXML
    private TableColumn<Centro_resultado, String> colunaCod;
    @FXML
    private TableColumn<Centro_resultado, String> colunaNome;
    @FXML
    private TableColumn<Centro_resultado, String> colunaSigla;

    private String usuario = TelaLoginController.usuariologado.getUsername();
    public void initialize(URL url, ResourceBundle rb) {
        nomeUsuario.setText(usuario);
        forneceTabela();
    }    

    @FXML
    private void BotaoAdicionar() {
        boolean ehdigito = false;
        boolean atequatro = false;
        crDAO crdao = new crDAO();
        Centro_resultado cr = new Centro_resultado();
        //testa se o codigo da CR é um número
        if(entradaCod.getText().matches("\\d+")){
            ehdigito = true;
        }else{
            System.out.println("n numero");
        }
        if(!entradaSigla.getText().isEmpty() && entradaSigla.getText().length()<=4){
            atequatro = true;
        }else{
            System.out.println("mais de 4 caracteres");
        }
        if(ehdigito && !entradaNome.getText().isEmpty() && atequatro){
            try{
                cr.setNome(entradaNome.getText());
                cr.setCodigo_cr(entradaCod.getText());
                cr.setSigla(entradaSigla.getText().toUpperCase());
                cr.setStatus_cr("ativo");
                
                crdao.save(cr);
                System.out.println("Salvo");
                limparCampos();
                forneceTabela();
            }
            catch(Exception e){
                System.out.println("Ocorreu um erro ao salvar os dados.");
                e.printStackTrace();
            }
        }
        else{
            System.out.println("n salvo");
        }
    }

    @FXML
    private void limparCampos() throws IOException {
        entradaNome.setText(null);
        entradaCod.setText(null);
        entradaSigla.setText(null);
    }
    
    @FXML
    private void navGestUsuario()throws IOException {
    App.setRoot("CadastroClienteADM");
    }
    
    private List<Centro_resultado> liscr = new ArrayList<>();
    private ObservableList<Centro_resultado> observablelistliscr = FXCollections.observableArrayList();
    @FXML
    private void forneceTabela(){
        crDAO crdao = new crDAO();
        liscr.clear();
        liscr.addAll(crdao.getCrs());
        observablelistliscr.setAll(liscr);
        tabelaCadastroCliente.setItems(observablelistliscr);
        
        colunaCod.setCellValueFactory(new PropertyValueFactory<>("codigo_cr"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("sigla"));
        colunaSigla.setCellValueFactory(new PropertyValueFactory<>("nome"));
        

    }
}