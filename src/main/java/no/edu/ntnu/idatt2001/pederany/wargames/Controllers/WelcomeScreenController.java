package no.edu.ntnu.idatt2001.pederany.wargames.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class WelcomeScreenController {

    private Stage stage;
    private Scene scene;

    @FXML
    private Button exit;

    /**
     *  Changes from welcomeScreen.fxml to armyOne.fxml
     * @param event
     * @throws IOException Exception is thrown if the path is not found
     */
    @FXML
    public void createBattleClicked(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/no/edu/ntnu/idatt2001/pederany/wargames/armyOne.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     *  Changes from welcomeScreen.fxml to rules.fxml
     * @param event Button clicked by user
     * @throws IOException Exception is thrown if the path is not found
     */
    @FXML
    public void gameplayRulesClicked(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/no/edu/ntnu/idatt2001/pederany/wargames/rules.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Exits the application
     */
    @FXML
    private void exitWindow(){
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }
}
