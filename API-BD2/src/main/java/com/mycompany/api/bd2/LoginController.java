package com.mycompany.api.bd2;

import models.Funcao;
import models.Usuario;
import java.io.IOException;
import javafx.fxml.FXML;

public class LoginController {
    private Funcao cargo;
    private Usuario user;

    @FXML
    private void login() throws IOException {  
        if(cargo.equals(user.getCargoObj(), Funcao.ADMIN)){
            App.setRoot("HomeAdm"); 
        }
        if (cargo.equals(user.getCargoObj(), Funcao.COLABORADOR)){
            App.setRoot("HomeColab"); 
        }
        if (cargo.equals(user.getCargoObj(), Funcao.GESTOR)){
            App.setRoot("HomeGest"); 
        }
    }
}
