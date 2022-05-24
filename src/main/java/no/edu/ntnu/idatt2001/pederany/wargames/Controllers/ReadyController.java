package no.edu.ntnu.idatt2001.pederany.wargames.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import no.edu.ntnu.idatt2001.pederany.wargames.Battle.Army;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ReadyController {

    private Army armyOne;
    private Army armyTwo;
    private String terrain;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label armyOneName;
    @FXML
    private Label armyTwoName;
    @FXML
    private Button battle;
    @FXML
    private Label armyOneCommander;
    @FXML
    private Label armyOneInfantry;
    @FXML
    private Label armyOneRanged;
    @FXML
    private Label armyOneCavalry;
    @FXML
    private Label armyTwoCommander;
    @FXML
    private Label armyTwoInfantry;
    @FXML
    private Label armyTwoRanged;
    @FXML
    private Label armyTwoCavalry;
    @FXML
    private RadioButton forest;
    @FXML
    private RadioButton hill;
    @FXML
    private RadioButton plains;

    /**
     * Toggle-groups for the terrain radio-buttons
     */
    @FXML
    public void initialize() {
        ToggleGroup toggleGroup = new ToggleGroup();
        forest.setToggleGroup(toggleGroup);
        hill.setToggleGroup(toggleGroup);
        plains.setToggleGroup(toggleGroup);
        battle.setDisable(true);
    }

    /**
     * Confirms the selected terrain
     */
    @FXML
    public void terrainClicked() {
        battle.setDisable(false);
        if(forest.isSelected()) {
            terrain = "forest";
        } else if (hill.isSelected()) {
            terrain = "hill";
        } else if (plains.isSelected()) {
            terrain = "plains";
        }
    }

    /**
     * Imports the number of units in armyOne from armyOne.fxml
     * @param armyOne army one
     */
    public void setArmyOne(Army armyOne) {
        this.armyOne = armyOne;
        armyOneName.setText(armyOne.getName());
        armyOneInfantry.setText(String.valueOf(armyOne.getInfantryUnits().size()));
        armyOneRanged.setText(String.valueOf(armyOne.getRangedUnits().size()));
        armyOneCavalry.setText(String.valueOf(armyOne.getCavalryUnits().size()));
        armyOneCommander.setText(String.valueOf(armyOne.getCommanderUnits().size()));
    }

    /**
     * Imports the number of units in armyTwo from armyTwo.fxml
     * @param armyTwo army two
     */
    public void setArmyTwo(Army armyTwo) {
        this.armyTwo = armyTwo;
        armyTwoName.setText(armyTwo.getName());
        armyTwoInfantry.setText(String.valueOf(armyTwo.getInfantryUnits().size()));
        armyTwoRanged.setText(String.valueOf(armyTwo.getRangedUnits().size()));
        armyTwoCavalry.setText(String.valueOf(armyTwo.getCavalryUnits().size()));
        armyTwoCommander.setText(String.valueOf(armyTwo.getCommanderUnits().size()));
    }

    /**
     * Changes to battle.fxml and runs the battle
     * @param event Button clicked by user
     * @throws IOException Exception is thrown if the path is not found
     */
    @FXML
    public void startBattleClicked(ActionEvent event) throws IOException {

        URL url = new File("src/main/resources/no/edu/ntnu/idatt2001/pederany/wargames/battle.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        root = loader.load();

        BattleController battleController = loader.getController();
        battleController.transferVariables(armyOne, armyTwo, terrain);
        battleController.startBattle();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
