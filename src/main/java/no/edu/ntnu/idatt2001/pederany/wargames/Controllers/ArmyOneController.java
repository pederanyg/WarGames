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
    private RadioButton newArmy;
    @FXML
    private RadioButton armyFromFile;
    @FXML
    private Label infantryUnits;
    @FXML
    private Label rangedUnits;
    @FXML
    private Label cavalryUnits;
    @FXML
    private Label infantryLabel;
    @FXML
    private Label rangedLabel;
    @FXML
    private Label cavalryLabel;
    @FXML
    private Button proceed;
    @FXML
    private Button fiveMoreInfantryUnits;
    @FXML
    private Button fiveMoreRangedUnits;
    @FXML
    private Button fiveMoreCavalryUnits;
    @FXML
    private Button selectFile;
    @FXML
    private TextArea inputFile;
    @FXML
    private Button fiveLessInfantryUnits;
    @FXML
    private Button fiveLessRangedUnits;
    @FXML
    private Button fiveLessCavalryUnits;
    @FXML
    private RadioButton commanderBaggo;
    @FXML
    private RadioButton commanderNygz;

    @FXML
    public void initialize() {
        ToggleGroup scratchOrFile = new ToggleGroup();
        armyFromFile.setToggleGroup(scratchOrFile);
        newArmy.setToggleGroup(scratchOrFile);
        armyFromFileSelected();
        proceed.setDisable(true);
        selectFile.setVisible(false);
        inputFile.setVisible(false);
    }

    @FXML
    public void newArmySelected() {
        infantryUnits.setVisible(true);
        rangedUnits.setVisible(true);
        cavalryUnits.setVisible(true);
        infantryLabel.setVisible(true);
        rangedLabel.setVisible(true);
        cavalryLabel.setVisible(true);
        fiveMoreInfantryUnits.setVisible(true);
        fiveMoreRangedUnits.setVisible(true);
        fiveMoreCavalryUnits.setVisible(true);
        fiveLessInfantryUnits.setVisible(true);
        fiveLessRangedUnits.setVisible(true);
        fiveLessCavalryUnits.setVisible(true);
        commanderBaggo.setVisible(true);
        commanderNygz.setVisible(true);
        selectFile.setVisible(false);
        inputFile.setVisible(false);
        unitsSelected();

    }

    @FXML
    public void armyFromFileSelected() {
        infantryUnits.setVisible(false);
        rangedUnits.setVisible(false);
        cavalryUnits.setVisible(false);
        infantryLabel.setVisible(false);
        rangedLabel.setVisible(false);
        cavalryLabel.setVisible(false);
        fiveMoreInfantryUnits.setVisible(false);
        fiveMoreRangedUnits.setVisible(false);
        fiveMoreCavalryUnits.setVisible(false);
        selectFile.setVisible(true);
        inputFile.setVisible(true);
        if (inputFile.getText().isEmpty()) {
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

    @FXML
    public void infantryUpClicked(ActionEvent event) {
        int amount = Integer.parseInt(infantryUnits.getText());
        if (event.getSource().equals(fiveMoreInfantryUnits)) {
            amount += 5;
        }
        infantryUnits.setText(String.valueOf(amount));
        unitsSelected();
    }

    @FXML
    public void infantryDownClicked(ActionEvent event) {
        int amount = Integer.parseInt(infantryUnits.getText());
        if (event.getSource().equals(fiveLessInfantryUnits)) {
            amount -= 5;
        }
        infantryUnits.setText(String.valueOf(amount));
        unitsSelected();
    }

    public void rangedUpClicked(ActionEvent event) {
        int amount = Integer.parseInt(rangedUnits.getText());
        if (event.getSource().equals(fiveMoreRangedUnits)) {
            amount += 5;
        }
        infantryUnits.setText(String.valueOf(amount));
        unitsSelected();
    }

    public void rangedDownClicked(ActionEvent event) {
        int amount = Integer.parseInt(rangedUnits.getText());
        if (event.getSource().equals(fiveLessRangedUnits)) {
            amount -= 5;
        }
        infantryUnits.setText(String.valueOf(amount));
        unitsSelected();
    }

    public void cavalryUpClicked(ActionEvent event) {
        int amount = Integer.parseInt(cavalryUnits.getText());
        if (event.getSource().equals(fiveMoreCavalryUnits)) {
            amount += 5;
        }
        infantryUnits.setText(String.valueOf(amount));
        unitsSelected();
    }

    public void cavalryDownClicked(ActionEvent event) {
        int amount = Integer.parseInt(cavalryUnits.getText());
        if (event.getSource().equals(fiveLessCavalryUnits)) {
            amount -= 5;
        }
        infantryUnits.setText(String.valueOf(amount));
        unitsSelected();
    }
    @FXML
    public void unitsSelected() {
        if (commanderBaggo.isSelected() || commanderNygz.isSelected()){
            proceed.setDisable(false);
        }else if (Integer.parseInt(infantryUnits.getText()) != 0 || Integer.parseInt(rangedUnits.getText()) != 0 || Integer.parseInt(cavalryUnits.getText()) != 0){
            proceed.setDisable(false);
        }else if (Integer.parseInt(infantryUnits.getText()) == 0 || Integer.parseInt(rangedUnits.getText()) == 0 || Integer.parseInt(cavalryUnits.getText()) == 0){
            proceed.setDisable(true);
        }
    }

    @FXML
    public void enterName(){
        name = nameArea.getText();
    }

    @FXML
    public void proceedClicked(ActionEvent event) throws IOException {
        name = nameArea.getText();
        if (newArmy.isSelected()){
            ArrayList<Unit> totalUnits = new ArrayList<>();

            totalUnits.addAll(UnitFactory.createListOfUnits("Infantry Unit", "Per", 50, Integer.parseInt(infantryUnits.getText())));
            totalUnits.addAll(UnitFactory.createListOfUnits("Ranged Unit", "Pål", 30, Integer.parseInt(rangedUnits.getText())));
            totalUnits.addAll(UnitFactory.createListOfUnits("Cavalry Unit", "Steffen", 70, Integer.parseInt(cavalryUnits.getText())));
            army = new Army(name, totalUnits);
        }else if (armyFromFile.isSelected()){
            this.army.setName(name);
        }
        URL url = new File("scr/main/resources/no/edu/ntnu/idatt2001/pederany/wargames/armyTwo.fxml").toURI().toURL();
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
