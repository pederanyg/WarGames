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

public class ArmyOneController extends ArmyTwoController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    final FileChooser fileChooser = new FileChooser();
    private String name;
    private Army army;

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

    @FXML
    public void initialize() {
        ToggleGroup scratchOrFile = new ToggleGroup();
        armyFromFile.setToggleGroup(scratchOrFile);
        armyFromScratch.setToggleGroup(scratchOrFile);
        armyFromFileSelected();
        proceed.setDisable(true);
        selectFile.setVisible(false);
        inputFile.setVisible(false);
    }

    /**
     * Makes everything related to "Create army from scratch" visible, also checks if the "Proceed" button should be disabled
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
     * Makes everything related to "Create army from scratch" invisible, also checks if the "Proceed" button should be disabled
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

    @FXML
    private void selectFileClicked() throws FileNotFoundException {
        File recordsDirectory = new File(System.getProperty("user.home"), ".wargames/records");
        FileHandler.checkDirectory();

        fileChooser.setTitle("Please select a file");
        fileChooser.setInitialDirectory(recordsDirectory);
        File file = fileChooser.showOpenDialog(null);

        try {
            inputFile.appendText(file.getAbsolutePath());
        } catch (NullPointerException e) {

        }
        if (!inputFile.getText().isEmpty()) {
            proceed.setDisable(false);
            this.army = FileHandler.readFromCsv(inputFile.getText());
            assert this.army != null;
            nameArea.setText(this.army.getName());
        }
    }

    /**
     * Checks which infantryUp-button was clicked, then updates the label containing the amount accordingly
     * @param event Which button was clicked
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
     * Checks which infantryDown-button was clicked, then updates the label containing the amount accordingly.
     * If the new amount is less than 0, the label and value is not updated
     * @param event Which button was clicked
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
     * Checks which rangedUp-button was clicked, then updates the label containing the amount accordingly
     * @param event Which button was clicked
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
     * Checks which rangedDown-button was clicked, then updates the label containing the amount accordingly.
     * If the new amount is less than 0, the label and value is not updated
     * @param event Which button was clicked
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
     * Checks which cavalryUp-button was clicked, then updates the label containing the amount accordingly
     * @param event Which button was clicked
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
     * Checks which cavalryDown-button was clicked, then updates the label containing the amount accordingly.
     * If the new amount is less than 0, the label and value is not updated
     * @param event Which button was clicked
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
     * Checks if the user currently has selected any units. If they have the "Proceed"-button will be enabled.
     * If the user has not selected any units, the button will be disabled
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

    @FXML
    public void enterName() {
        name = nameArea.getText();
    }

    /**
     * Creates an army, and transfers it to ready.fxml scene
     * @param event
     * @throws IOException
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
            army = new Army(name, totalUnits);
        } else if(armyFromFile.isSelected()) {
            this.army.setName(name);
        }

        URL url = new File("src/main/resources/no/edu/ntnu/idatt2001/pederany/wargames/armyTwo.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        root = loader.load();
        ArmyTwoController armyTwoController = loader.getController();
        armyTwoController.setArmyOne(this.army);


        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
