    package com.mycompany.api.bd2;

import java.io.IOException;
import javafx.fxml.FXML;

public class LoginController {

    @FXML
    private void Logon() throws IOException {
        App.setRoot("HomeColaborador");
    }
}