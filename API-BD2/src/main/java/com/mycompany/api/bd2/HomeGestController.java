package com.mycompany.api.bd2;

import java.io.IOException;
import javafx.fxml.FXML;

public class HomeGestController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}