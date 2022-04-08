package no.edu.ntnu.idatt2001.pederany.wargames;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {

    @Test
    @DisplayName("Testing the convertToString method")
    public void convertToString() {
        Army army = new Army("Human Army");
        InfantryUnit unit_1 = new InfantryUnit("Ole", 25);
        RangedUnit unit_2 = new RangedUnit("Dole", 35 );
        army.add(unit_1);
        army.add(unit_2);
        System.out.println(FileHandler.convertToString(army));
    }

    @Test
    @DisplayName("Testing the writeToCsv method")
    public void writeToCsv() throws IOException {
        Army army = new Army("Human Army");
        InfantryUnit unit_1 = new InfantryUnit("Ole", 25);
        RangedUnit unit_2 = new RangedUnit("Dole", 35);
        army.add(unit_1);
        army.add(unit_2);
        FileHandler.writeToCsv(FileHandler.convertToString(army), "src/main/resources/ArmyWrite.csv");
    }

    @Nested
    @DisplayName("Testing reading from file")
    class readingFromFile {

        @Test
        @DisplayName("Testing the 'army has units' method")
        public void armyHasUnits () throws FileNotFoundException {
            Army army = FileHandler.readFromCsv("src/main/resources/ArmyWrite.csv");
            assert army != null;
            assertTrue(army.hasUnits());
        }

        @Test
        @DisplayName("Testing the 'army has name' method")
        public void armyHasName() throws FileNotFoundException {
            Army army = FileHandler.readFromCsv("src/main/resources/ArmyWrite.csv");
            assert army != null;
            assertEquals("Human Army", army.getName().trim());
        }

        @Test
        @DisplayName("Testing the 'Infantry Unit is of correct type' method")
        public void readInfantryUnitIsOfCorrectType() throws IOException {
            Army writeArmy = new Army("Human Army");
            InfantryUnit unit_1 = new InfantryUnit("Ole", 25);
            writeArmy.add(unit_1);
            FileHandler.writeToCsv(FileHandler.convertToString(writeArmy), "src/main/resources/ArmyWrite.csv");

            Army readArmy = FileHandler.readFromCsv("src/main/resources/ArmyWrite.csv");
            assert readArmy != null;
            assertTrue(readArmy.getInfantryUnits().get(0) instanceof InfantryUnit);
        }
        @Test
        @DisplayName("Testing the 'Ranged Unit is of correct type' method")
        public void readRangedUnitIsOfCorrectType() throws IOException {
            Army writeArmy = new Army("RBK");
            RangedUnit unit_1 = new RangedUnit("Dole", 35);
            writeArmy.add(unit_1);
            FileHandler.writeToCsv(FileHandler.convertToString(writeArmy), "src/main/resources/ArmyWrite.csv");

            Army readArmy = FileHandler.readFromCsv("src/main/resources/ArmyWrite.csv");
            assert readArmy != null;
            assertTrue(readArmy.getRangedUnits().get(0) instanceof RangedUnit);
        }

        @Test
        @DisplayName("Testing the 'Cavalry Unit is of correct type' method")
        public void readCavalryUnitIsOfCorrectType() throws IOException {
            Army writeArmy = new Army("Human Army");
            CavalryUnit unit_1 = new CavalryUnit("Ole", 25);
            CommanderUnit unit_2 = new CommanderUnit("Dole", 35);
            writeArmy.add(unit_1);
            writeArmy.add(unit_2);
            FileHandler.writeToCsv(FileHandler.convertToString(writeArmy), "src/main/resources/ArmyWrite.csv");

            Army readArmy = FileHandler.readFromCsv("src/main/resources/ArmyWrite.csv");
            assert readArmy != null;
            assertTrue(readArmy.getCavalryUnits().get(0) instanceof CavalryUnit);
        }

        @Test
        @DisplayName("Testing the 'Commander Unit is of correct type' method")
        public void readCommanderUnitIsOfCorrectType() throws IOException {
            Army writeArmy = new Army("Human Army");
            CommanderUnit unit_1 = new CommanderUnit("Ole", 25);
            CavalryUnit unit_2 = new CavalryUnit("Dole", 35);
            writeArmy.add(unit_1);
            writeArmy.add(unit_2);

            FileHandler.writeToCsv(FileHandler.convertToString(writeArmy), "src/main/resources/ArmyWrite.csv");

            Army readArmy = FileHandler.readFromCsv("src/main/resources/ArmyWrite.csv");
            assert readArmy != null;
            assertTrue(readArmy.getCommanderUnits().get(0) instanceof CommanderUnit);
        }

        @Test
        @DisplayName("Exception for when the health and the name of a unit is swapped when reading")
        public void healthAndNameSwapped() throws FileNotFoundException {
            Exception exception = assertThrows(NumberFormatException.class, () -> {
                Army army = FileHandler.readFromCsv("src/main/resources/ArmyRead.csv");
            });
            String expectedMessage = "For input string:";
            String actualMessage = exception.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }
}

