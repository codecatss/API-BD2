/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.api.bd2;

import com.mycompany.api.bd2.daos.crDAO;
import com.mycompany.api.bd2.models.Centro_resultado;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TableView<?> tabelaCadastroCliente;
    @FXML
    private TableColumn<?, ?> colunaCod;
    @FXML
    private TableColumn<?, ?> colunaNome;
    @FXML
    private TableColumn<?, ?> colunaSigla;

    private String usuario = TelaLoginController.usuariologado.getUsername();
    public void initialize(URL url, ResourceBundle rb) {
        nomeUsuario.setText(usuario);
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
                System.out.println("foi");
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
        App.setRoot("CadastroUsuarioADM");
    }   
}