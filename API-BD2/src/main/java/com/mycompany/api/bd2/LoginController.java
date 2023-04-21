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
        if(cargo.equals(user.getCargoObj(), Funcao.admin)){
            App.setRoot("HomeAdm"); 
        }
        if (cargo.equals(user.getCargoObj(), Funcao.colaborador)){
            App.setRoot("HomeColab"); 
        }
        if (cargo.equals(user.getCargoObj(), Funcao.gestor)){
            App.setRoot("HomeGest"); 
        }
    }
}
