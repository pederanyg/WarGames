package no.edu.ntnu.idatt2001.pederany.wargames.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class RulesController {

    private Stage stage;
    private Scene scene;

    /**
     * Changes from rules.fxml to welcomeScreen.fxml
     * @param event Button clicked by user
     * @throws IOException Exception is thrown if the path is not found
     */
    @FXML
    public void backToWelcomeScreen(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/no/edu/ntnu/idatt2001/pederany/wargames/welcomeScreen.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
