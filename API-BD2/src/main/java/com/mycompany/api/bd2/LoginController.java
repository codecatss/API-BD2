package com.mycompany.api.bd2;

import Usuario.Funcao;
import Usuario.Usuario;
import java.io.IOException;
import javafx.fxml.FXML;

public class LoginController {
    private Funcao cargo;
    private Usuario user;

    @FXML
    private void login() throws IOException {  
        if(cargo.equals(user.getCargo(), Funcao.ADMINISTRADOR)){
            App.setRoot("HomeAdm"); 
        }
        if (cargo.equals(user.getCargo(), Funcao.COLABORADOR)){
            App.setRoot("HomeColab"); 
        }
        if (cargo.equals(user.getCargo(), Funcao.GESTOR)){
            App.setRoot("HomeGest"); 
        }
    }
}
