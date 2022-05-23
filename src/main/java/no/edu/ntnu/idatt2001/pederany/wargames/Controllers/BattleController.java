package no.edu.ntnu.idatt2001.pederany.wargames.Controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import no.edu.ntnu.idatt2001.pederany.wargames.Battle.Army;
import no.edu.ntnu.idatt2001.pederany.wargames.Battle.Battle;
import no.edu.ntnu.idatt2001.pederany.wargames.Battle.FileHandler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class BattleController {

    private Army armyOne;
    private Army armyTwo;
    private Army copyArmyOne;
    private Army copyArmyTwo;
    private String terrain;
    private Battle battle;
    private Army winner;
    private ArrayList<Integer> armyOneHealthOverTime;
    private ArrayList<Integer> armyTwoHealthOverTime;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label armyOneHealth;

    @FXML
    private Label armyTwoHealth;

    @FXML
    private Label armyOneName;

    @FXML
    private Label armyTwoName;

    @FXML
    private ProgressBar armyOneBar;

    @FXML
    private ProgressBar armyTwoBar;

    @FXML
    private Label showWinner;

    @FXML
    private Button welcomeScreen;

    @FXML
    private Button saveBattleWinner;

    @FXML
    public void initialize() {
        showWinner.setVisible(false);
        welcomeScreen.setVisible(false);
        saveBattleWinner.setVisible(false);
    }

    /**
     * Takes in two armies and terrain from another scene
     * @param armyOne
     * @param armyTwo
     * @param terrain
     */
    public void transferVariables(Army armyOne, Army armyTwo, String terrain) {
        this.armyOne = armyOne;
        this.armyTwo = armyTwo;
        this.terrain = terrain;
        armyOneName.setText(armyOne.getName());
        armyTwoName.setText(armyTwo.getName());
    }

    /**
     * Starts a new battle with two armies, and displays the battle and winner to the user
     */
    public void startBattle() {
        copyArmyOne = new Army(armyOne);
        copyArmyTwo = new Army(armyTwo);
        battle = new Battle(armyOne, armyTwo);
        winner = battle.simulate(this.terrain);
        armyOneHealthOverTime = battle.getArmyOneHealthOverTime();
        armyTwoHealthOverTime = battle.getArmyTwoHealthOverTime();
        armyOneHealth.setText(armyOneHealthOverTime.get(0) + "/" + armyOneHealthOverTime.get(0));
        armyTwoHealth.setText(armyTwoHealthOverTime.get(0) + "/" + armyTwoHealthOverTime.get(0));

        Runnable task = () -> {
            try {
                for(int i = 1; i < armyOneHealthOverTime.size(); i++) {
                    final int j = i;
                    Thread.sleep(200);
                    Platform.runLater(() -> {
                        armyOneHealth.setText(armyOneHealthOverTime.get(j) + "/" + armyOneHealthOverTime.get(0));
                        armyTwoHealth.setText(armyTwoHealthOverTime.get(j) + "/" + armyTwoHealthOverTime.get(0));
                        armyOneBar.setProgress(armyOneHealthOverTime.get(j) / (float) armyOneHealthOverTime.get(0));
                        armyTwoBar.setProgress(armyTwoHealthOverTime.get(j) / (float) armyTwoHealthOverTime.get(0));
                    });
                }
                Platform.runLater(() -> {
                    showWinner.setText(winner.getName() + " is victorious!");
                    showWinner.setVisible(true);
                    welcomeScreen.setVisible(true);
                    saveBattleWinner.setVisible(true);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        new Thread(task).start();
    }

    @FXML
    public void goBackToWelcomeScreen(ActionEvent e) throws IOException {
        URL url = new File("src/main/resources/no/edu/ntnu/idatt2001/pederany/wargames/welcomeScreen.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        root = loader.load();

        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void moveToSaveBattleWinner(ActionEvent e) throws IOException {
        FileHandler.checkDirectory();
        StringBuilder path = new StringBuilder();
        path.append("C:\\users\\peder\\.wargames\\records\\");
        if(winner.getName().equals(copyArmyOne.getName())) {
            winner = copyArmyOne;
        } else {
            winner = copyArmyTwo;
        }

        path.append(winner.getName()).append(".csv");
        FileHandler.writeToCsv(FileHandler.convertToString(winner), path.toString());
        saveBattleWinner.setDisable(true);
    }
}
