package no.edu.ntnu.idatt2001.pederany.wargames;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class WarGamesController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}