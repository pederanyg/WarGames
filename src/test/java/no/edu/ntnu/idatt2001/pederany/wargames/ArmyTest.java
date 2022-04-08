package no.edu.ntnu.idatt2001.pederany.wargames;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ArmyTest {

    @Test
    @DisplayName("Testing the add units method.")
    void add() {
        Army army = new Army("Ronaldo");
        RangedUnit Ronny = new RangedUnit("Ronny", 100);
        army.add(Ronny);
        assertTrue(army.hasUnits());
    }

    @Test
    @DisplayName("Testing the add all method.")
    void addAll() {
        Army army = new Army("BI");
        ArrayList<Unit> units = new ArrayList<>();
        RangedUnit Ronny = new RangedUnit("Ronny", 100);
        CavalryUnit Conny = new CavalryUnit("Conny", 200);
        InfantryUnit Jonny = new InfantryUnit("Jonny", 300);
        CommanderUnit Tommy = new CommanderUnit("Tommy", 400);
        units.add(Ronny);
        units.add(Conny);
        units.add(Jonny);
        units.add(Tommy);
        army.addAll(units);
        assertTrue(army.hasUnits());
    }

    @Test
    @DisplayName("Testing the remove method.")
    void remove() {
        Army army = new Army("Erlends Privstory");
        RangedUnit Ronny = new RangedUnit("Ronny", 100);
        army.add(Ronny);
        army.remove(Ronny);
        assertFalse(army.hasUnits());
    }

    @Test
    @DisplayName("Testing the get all method.")
    void getAllUnits() {
        Army army = new Army("BI");
        RangedUnit Ronny = new RangedUnit("Ronny", 100);
        CavalryUnit Conny = new CavalryUnit("Conny", 200);
        InfantryUnit Jonny = new InfantryUnit("Jonny", 300);
        CommanderUnit Tommy = new CommanderUnit("Tommy", 400);
        army.add(Ronny);
        army.add(Conny);
        army.add(Jonny);
        army.add(Tommy);
        assertEquals(4, army.getAllUnits().size());
    }

    @Test
    @DisplayName("Testing the method for getting a random unit.")
    void getRandom() {
        Army army = new Army("BI");
        RangedUnit Ronny = new RangedUnit("Ronny", 100);
        CavalryUnit Conny = new CavalryUnit("Conny", 200);
        InfantryUnit Jonny = new InfantryUnit("Jonny", 300);
        CommanderUnit Tommy = new CommanderUnit("Tommy", 400);
        army.add(Ronny);
        army.add(Conny);
        army.add(Jonny);
        army.add(Tommy);
        assertNotNull(army.getRandom());
    }

    @Test
    @DisplayName("Testing the getInfantryUnits-method")
    void getInfantryUnits() {
        Army army = new Army("baggo");
        InfantryUnit Peder = new InfantryUnit("Peder", 222);
        army.add(Peder);
        assertEquals(1,army.getInfantryUnits().size());
    }

    @Test
    @DisplayName("Testing that the Commander unit is not added with the Cavalry units.")
    void getCavalryUnits() {
        Army army = new Army("Gutta");
        CavalryUnit Nygz = new CavalryUnit("Nygz", 100);
        CommanderUnit Ch = new CommanderUnit("Ch", 200);
        army.add(Nygz);
        army.add(Ch);
        assertEquals(1,army.getCavalryUnits().size());
    }
}