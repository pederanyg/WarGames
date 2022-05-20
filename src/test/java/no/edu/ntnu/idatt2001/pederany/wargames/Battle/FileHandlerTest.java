package no.edu.ntnu.idatt2001.pederany.wargames.Battle;

import no.edu.ntnu.idatt2001.pederany.wargames.Battle.FileHandler;
import no.edu.ntnu.idatt2001.pederany.wargames.Units.CavalryUnit;
import no.edu.ntnu.idatt2001.pederany.wargames.Units.CommanderUnit;
import no.edu.ntnu.idatt2001.pederany.wargames.Units.InfantryUnit;
import no.edu.ntnu.idatt2001.pederany.wargames.Units.RangedUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {

    @Test
    @DisplayName("A unit is converted and presented as a cohesive string")
    public void convertToString() {
        Army army = new Army("Human Army");
        InfantryUnit unit_1 = new InfantryUnit("Ole", 25);
        RangedUnit unit_2 = new RangedUnit("Dole", 35 );
        army.add(unit_1);
        army.add(unit_2);
        System.out.println(FileHandler.convertToString(army));
    }

    @Test
    @DisplayName("A unit is written to the .csv file")
    public void writeToCsv() throws IOException {
        Army army = new Army("Human Army");
        InfantryUnit unit_1 = new InfantryUnit("Ole", 25);
        RangedUnit unit_2 = new RangedUnit("Dole", 35);
        army.add(unit_1);
        army.add(unit_2);
        FileHandler.writeToCsv(FileHandler.convertToString(army),
                "src/main/resources/no/edu/ntnu/idatt2001/pederany/wargames/ArmyWrite.csv");
    }

    @Nested
    @DisplayName("A unit should be able to be read from the ArmyRead.csv file")
    class readingFromFile {

        @Test
        @DisplayName("Confirming that an army has units in it")
        public void armyHasUnits () throws FileNotFoundException {
            Army army = FileHandler.readFromCsv("src/main/resources/no/edu/ntnu/idatt2001/pederany/wargames/ArmyWrite.csv");
            assert army != null;
            assertTrue(army.hasUnits());
        }

        @Test
        @DisplayName("Confirming that an army has a given name")
        public void armyHasName() throws FileNotFoundException {
            Army army = FileHandler.readFromCsv("src/main/resources/no/edu/ntnu/idatt2001/pederany/wargames/ArmyWrite.csv");
            assert army != null;
            assertEquals("Human Army", army.getName().trim());
        }

        @Test
        @DisplayName("The Infantry unit type is recognized")
        public void infantryUnitIsOfCorrectType() throws IOException {
            Army writeArmy = new Army("Human Army");
            InfantryUnit unit_1 = new InfantryUnit("Ole", 25);
            writeArmy.add(unit_1);
            FileHandler.writeToCsv(FileHandler.convertToString(writeArmy), "src/main/resources/ArmyWrite.csv");

            Army readArmy = FileHandler.readFromCsv("src/main/resources/ArmyWrite.csv");
            assert readArmy != null;
            assertTrue(readArmy.getInfantryUnits().get(0) instanceof InfantryUnit);
        }
        @Test
        @DisplayName("The Ranged unit type is recognized")
        public void rangedUnitIsOfCorrectType() throws IOException {
            Army writeArmy = new Army("RBK");
            RangedUnit unit_1 = new RangedUnit("Dole", 35);
            writeArmy.add(unit_1);
            FileHandler.writeToCsv(FileHandler.convertToString(writeArmy), "src/main/resources/ArmyWrite.csv");

            Army readArmy = FileHandler.readFromCsv("src/main/resources/ArmyWrite.csv");
            assert readArmy != null;
            assertTrue(readArmy.getRangedUnits().get(0) instanceof RangedUnit);
        }

        @Test
        @DisplayName("The Cavalry unit type is recognized")
        public void cavalryUnitIsOfCorrectType() throws IOException {
            Army writeArmy = new Army("Human Army");
            CavalryUnit unit_1 = new CavalryUnit("Ole", 25);
            CommanderUnit unit_2 = new CommanderUnit("Dole", 35);
            writeArmy.add(unit_1);
            writeArmy.add(unit_2);
            FileHandler.writeToCsv(FileHandler.convertToString(writeArmy), "src/main/resources/ArmyWrite.csv");

            Army readArmy = FileHandler.readFromCsv("src/main/resources/ArmyWrite.csv");
            assertTrue(readArmy.getCavalryUnits().get(0) instanceof CavalryUnit);
        }

        @Test
        @DisplayName("The Commander unit type is recognized")
        public void commanderUnitIsOfCorrectType() throws IOException {
            Army writeArmy = new Army("Human Army");
            CommanderUnit unit_1 = new CommanderUnit("Ole", 25);
            CavalryUnit unit_2 = new CavalryUnit("Dole", 35);
            writeArmy.add(unit_1);
            writeArmy.add(unit_2);

            FileHandler.writeToCsv(FileHandler.convertToString(writeArmy), "src/main/resources/no/edu/ntnu/idatt2001/pederany/wargames/ArmyWrite.csv");

            Army readArmy = FileHandler.readFromCsv("src/main/resources/no/edu/ntnu/idatt2001/pederany/wargames/ArmyWrite.csv");
            assertTrue(readArmy.getCommanderUnits().get(0) instanceof CommanderUnit);
        }

        @Test
        @DisplayName("Exception for when health and name of a unit is swapped when reading")
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

