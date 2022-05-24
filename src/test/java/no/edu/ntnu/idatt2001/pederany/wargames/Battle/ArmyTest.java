package no.edu.ntnu.idatt2001.pederany.wargames.Battle;

import no.edu.ntnu.idatt2001.pederany.wargames.Units.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class ArmyTest {

    @Test
    @DisplayName("Adding a unit to an army")
    void add() {
        Army army = new Army("NTNU");
        RangedUnit Ronny = new RangedUnit("Ronny", 100);
        army.add(Ronny);
        assertTrue(army.hasUnits());
    }

    @Test
    @DisplayName("Adding a list of units to an army")
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
    @DisplayName("Removing a unit from an army")
    void remove() {
        Army army = new Army("NTNU");
        RangedUnit Ronny = new RangedUnit("Ronny", 100);
        army.add(Ronny);
        army.remove(Ronny);
        assertFalse(army.hasUnits());
    }

    @Test
    @DisplayName("Getting a list of units at once")
    void getAllUnits() {
        Army army = new Army("NTNU");
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
    @DisplayName("Getting a random unit")
    void getRandom() {
        Army army = new Army("NTNU");
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
    @DisplayName("Getting an Infantry unit")
    void getInfantryUnits() {
        Army army = new Army("NTNU");
        InfantryUnit Peder = new InfantryUnit("Peder", 222);
        army.add(Peder);
        assertEquals(1,army.getInfantryUnits().size());
    }

    @Test
    @DisplayName("Checking that the Commander units are not added alongside the Cavalry units.")
    void getCavalryUnits() {
        Army army = new Army("NTNU");
        CavalryUnit Nygz = new CavalryUnit("Nygz", 100);
        CommanderUnit Ch = new CommanderUnit("Ch", 100);
        army.add(Nygz);
        army.add(Ch);
        assertEquals(1,army.getCavalryUnits().size());
    }
}