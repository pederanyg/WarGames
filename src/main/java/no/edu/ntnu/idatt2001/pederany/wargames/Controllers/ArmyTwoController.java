package no.edu.ntnu.idatt2001.pederany.wargames.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import no.edu.ntnu.idatt2001.pederany.wargames.Battle.Army;
import no.edu.ntnu.idatt2001.pederany.wargames.Battle.FileHandler;
import no.edu.ntnu.idatt2001.pederany.wargames.Units.Unit;
import no.edu.ntnu.idatt2001.pederany.wargames.Units.UnitFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class ArmyTwoController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    final FileChooser fileChooser = new FileChooser();
    private String name;
    private Army armyOne;
    private Army armyTwo;

    @FXML
    private TextField nameArea;
    @FXML
    private RadioButton armyFromScratch;
    @FXML
    private RadioButton armyFromFile;
    @FXML
    private Label infantryAmount;
    @FXML
    private Label rangedAmount;
    @FXML
    private Label cavalryAmount;
    @FXML
    private Label infantryLabel;
    @FXML
    private Label rangedLabel;
    @FXML
    private Label cavalryLabel;
    @FXML
    private RadioButton commanderBaggo;
    @FXML
    private RadioButton commanderNygz;
    @FXML
    private Button proceed;
    @FXML
    private Button infantryOneUp;
    @FXML
    private Button infantryOneDown;
    @FXML
    private Button infantryFiveDown;
    @FXML
    private Button infantryFiveUp;
    @FXML
    private Button rangedOneUp;
    @FXML
    private Button rangedOneDown;
    @FXML
    private Button rangedFiveUp;
    @FXML
    private Button rangedFiveDown;
    @FXML
    private Button cavalryOneUp;
    @FXML
    private Button cavalryOneDown;
    @FXML
    private Button cavalryFiveUp;
    @FXML
    private Button cavalryFiveDown;
    @FXML
    private Button selectFile;
    @FXML
    private TextArea inputFile;

    /**
     * Initializes the window, but disables some functions until army is selected + toggle-groups for the radio buttons
     */
    @FXML
    public void initialize() {
        ToggleGroup scratchOrFile = new ToggleGroup();
        ToggleGroup commander = new ToggleGroup();
        armyFromFile.setToggleGroup(scratchOrFile);
        armyFromScratch.setToggleGroup(scratchOrFile);
        commanderBaggo.setToggleGroup(commander);
        commanderNygz.setToggleGroup(commander);
        armyFromFileSelected();
        proceed.setDisable(true);
        selectFile.setVisible(false);
        inputFile.setVisible(false);
    }

    /**
     * Everything related to "Create army from scratch" becomes visible. Checks if the "Confirm" button should be disabled also.
     */
    @FXML
    public void armyFromScratchSelected() {
        infantryAmount.setVisible(true);
        rangedAmount.setVisible(true);
        cavalryAmount.setVisible(true);
        infantryLabel.setVisible(true);
        rangedLabel.setVisible(true);
        cavalryLabel.setVisible(true);
        commanderBaggo.setVisible(true);
        commanderNygz.setVisible(true);
        infantryOneUp.setVisible(true);
        infantryOneDown.setVisible(true);
        infantryFiveUp.setVisible(true);
        infantryFiveDown.setVisible(true);
        rangedOneUp.setVisible(true);
        rangedOneDown.setVisible(true);
        rangedFiveUp.setVisible(true);
        rangedFiveDown.setVisible(true);
        cavalryOneUp.setVisible(true);
        cavalryOneDown.setVisible(true);
        cavalryFiveUp.setVisible(true);
        cavalryFiveDown.setVisible(true);
        selectFile.setVisible(false);
        inputFile.setVisible(false);
        unitsSelected();
    }

    /**
     * Everything related to "Import army from file" visible. Checks if the "Confirm" button should be disabled also.
     */
    @FXML
    public void armyFromFileSelected() {
        infantryAmount.setVisible(false);
        rangedAmount.setVisible(false);
        cavalryAmount.setVisible(false);
        commanderBaggo.setVisible(false);
        commanderNygz.setVisible(false);
        infantryLabel.setVisible(false);
        rangedLabel.setVisible(false);
        cavalryLabel.setVisible(false);
        infantryOneUp.setVisible(false);
        infantryOneDown.setVisible(false);
        infantryFiveUp.setVisible(false);
        infantryFiveDown.setVisible(false);
        rangedOneUp.setVisible(false);
        rangedOneDown.setVisible(false);
        rangedFiveUp.setVisible(false);
        rangedFiveDown.setVisible(false);
        cavalryOneUp.setVisible(false);
        cavalryOneDown.setVisible(false);
        cavalryFiveUp.setVisible(false);
        cavalryFiveDown.setVisible(false);
        selectFile.setVisible(true);
        inputFile.setVisible(true);
        if(inputFile.getText().isEmpty()) {
            proceed.setDisable(true);
        }
    }

    /**
     * Opens the records folder, where the previously saved armies are located
     * @throws FileNotFoundException If the folder is not found
     */
    @FXML
    private void selectFileClicked() throws FileNotFoundException {
        File recordsDirectory = new File(System.getProperty("user.home"), ".wargames/records");
        FileHandler.checkDirectory();

        fileChooser.setTitle("Please select a file");
        fileChooser.setInitialDirectory(recordsDirectory);
        File file = fileChooser.showOpenDialog(null);

        try {
            inputFile.appendText(file.getAbsolutePath());
        } catch(NullPointerException e) {

        }

        if(!inputFile.getText().isEmpty()) {
            proceed.setDisable(false);
            this.armyTwo = FileHandler.readFromCsv(inputFile.getText());
            nameArea.setText(this.armyTwo.getName());
        }
    }

    /**
     * When Infantry Units are added, the label updates accordingly
     * @param event Button clicked by user
     */
    @FXML
    public void infantryUpClicked(ActionEvent event) {
        int amount = Integer.parseInt(infantryAmount.getText());
        if(event.getSource().equals(infantryOneUp)) {
            amount += 1;
        } else if(event.getSource().equals(infantryFiveUp)) {
            amount += 5;
        }
        infantryAmount.setText(String.valueOf(amount));
        unitsSelected();
    }

    /**
     * When Infantry Units are removed, the label updates accordingly
     * If the amount is 0, no change is made
     * @param event Button clicked by user
     */
    @FXML
    public void infantryDownClicked(ActionEvent event) {
        int amount = Integer.parseInt(infantryAmount.getText());
        if(event.getSource().equals(infantryOneDown) && amount - 1 >= 0) {
            amount -= 1;
        } else if(event.getSource().equals(infantryFiveDown) && amount - 5 >= 0) {
            amount -= 5;
        }
        infantryAmount.setText(String.valueOf(amount));
        unitsSelected();
    }

    /**
     * When Ranged Units are added, the label updates accordingly
     * @param event Button clicked by user
     */
    @FXML
    public void rangedUpClicked(ActionEvent event) {
        int amount = Integer.parseInt(rangedAmount.getText());
        if(event.getSource().equals(rangedOneUp)) {
            amount += 1;
        } else if(event.getSource().equals(rangedFiveUp)) {
            amount += 5;
        }
        rangedAmount.setText(String.valueOf(amount));
        unitsSelected();
    }

    /**
     * When Ranged Units are removed, the label updates accordingly
     * If the amount is 0, no change is made
     * @param event Button clicked by user
     */
    @FXML
    public void rangedDownClicked(ActionEvent event) {
        int amount = Integer.parseInt(rangedAmount.getText());
        if(event.getSource().equals(rangedOneDown) && amount - 1 >= 0) {
            amount -= 1;
        } else if(event.getSource().equals(rangedFiveDown) && amount - 5 >= 0) {
            amount -= 5;
        }
        rangedAmount.setText(String.valueOf(amount));
        unitsSelected();
    }

    /**
     * When Cavalry Units are added, the label updates accordingly
     * @param event Button clicked by user
     */
    @FXML
    public void cavalryUpClicked(ActionEvent event) {
        int amount = Integer.parseInt(cavalryAmount.getText());
        if(event.getSource().equals(cavalryOneUp)) {
            amount += 1;
        } else if(event.getSource().equals(cavalryFiveUp)) {
            amount += 5;
        }
        cavalryAmount.setText(String.valueOf(amount));
        unitsSelected();
    }

    /**
     * When Cavalry Units are removed, the label updates accordingly
     * If the amount is 0, no change is made
     * @param event Button clicked by user
     */
    @FXML
    public void cavalryDownClicked(ActionEvent event) {
        int amount = Integer.parseInt(cavalryAmount.getText());
        if(event.getSource().equals(cavalryOneDown) && amount - 1 >= 0) {
            amount -= 1;
        } else if(event.getSource().equals(cavalryFiveDown) && amount - 5 >= 0) {
            amount -= 5;
        }
        cavalryAmount.setText(String.valueOf(amount));
        unitsSelected();
    }

    /**
     * Checks if the user has selected any units. If so, the "Confirm"-button will be enabled
     * If no units are selected, the button remains disabled
     */
    @FXML
    public void unitsSelected() {
        if(commanderBaggo.isSelected() || commanderNygz.isSelected()) {
            proceed.setDisable(false);
        } else if(Integer.parseInt(infantryAmount.getText()) != 0 || Integer.parseInt(rangedAmount.getText()) != 0 || Integer.parseInt(cavalryAmount.getText()) != 0) {
            proceed.setDisable(false);
        } else if(Integer.parseInt(infantryAmount.getText()) == 0 || Integer.parseInt(rangedAmount.getText()) == 0 || Integer.parseInt(cavalryAmount.getText()) == 0) {
            proceed.setDisable(true);
        }
    }

    /**
     * The user's army name
     */
    @FXML
    public void enterName() {
        name = nameArea.getText();
    }

    /**
     * Creates army and transfers it to ready.fxml
     * @param event Button clicked by user
     * @throws IOException Exception is thrown if the path is not found
     */
    @FXML
    public void proceedClicked(ActionEvent event) throws IOException {
        if(armyFromScratch.isSelected()) {
            ArrayList<Unit> totalUnits = new ArrayList<Unit>();

            if (commanderNygz.isSelected()) {
                totalUnits.add(UnitFactory.createUnit("CommanderUnit", "Nygz", 100));
            } else if (commanderBaggo.isSelected()) {
                totalUnits.add(UnitFactory.createUnit("CommanderUnit", "Baggo", 100));
            }

            totalUnits.addAll(UnitFactory.createListOfUnits("InfantryUnit", "Goblin", 50, Integer.parseInt(infantryAmount.getText())));
            totalUnits.addAll(UnitFactory.createListOfUnits("RangedUnit", "Archer", 30, Integer.parseInt(rangedAmount.getText())));
            totalUnits.addAll(UnitFactory.createListOfUnits("CavalryUnit", "Knight", 70, Integer.parseInt(cavalryAmount.getText())));
            armyTwo = new Army(name, totalUnits);
        } else if(armyFromFile.isSelected()) {
            this.armyTwo.setName(name);
        }
        URL url = new File("src/main/resources/no/edu/ntnu/idatt2001/pederany/wargames/ready.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        root = loader.load();
        ReadyController readyController = loader.getController();
        readyController.setArmyOne(this.armyOne);
        readyController.setArmyTwo(this.armyTwo);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setArmyOne(Army armyOne) {
        this.armyOne = armyOne;
    }
}
