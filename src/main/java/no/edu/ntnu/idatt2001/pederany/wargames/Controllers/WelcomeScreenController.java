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
    private Parent root;

    @FXML
    private Button exit;

    /**
     *  Changes to armyOne.fxml
     * @param event
     * @throws IOException
     */
    @FXML
    public void createBattleClicked(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/armyOne.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     *  Changes to rules.fxml
     * @param event
     * @throws IOException
     */
    @FXML
    public void gameplayRulesClicked(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/rules.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void exitWindow(){
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }
}
