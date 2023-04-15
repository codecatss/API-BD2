package com.mycompany.api.bd2;

import java.io.IOException;
import javafx.fxml.FXML;

public class HomeAdmController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
