package no.edu.ntnu.idatt2001.pederany.wargames.Battle;

import no.edu.ntnu.idatt2001.pederany.wargames.Battle.Battle;
import no.edu.ntnu.idatt2001.pederany.wargames.Units.CavalryUnit;
import no.edu.ntnu.idatt2001.pederany.wargames.Units.CommanderUnit;
import no.edu.ntnu.idatt2001.pederany.wargames.Units.InfantryUnit;
import no.edu.ntnu.idatt2001.pederany.wargames.Units.RangedUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleTest {

    @Test
    @DisplayName("The simulate method runs a battle in its entirety")

    void simulate() {
        Army army1 = new Army("1");
        Army army2 = new Army("2");
        RangedUnit Ronny = new RangedUnit("Ronny", 100);
        CavalryUnit Conny = new CavalryUnit("Conny", 200);
        InfantryUnit Jonny = new InfantryUnit("Jonny", 300);
        CommanderUnit Tommy = new CommanderUnit("Tommy", 400);
        army1.add(Ronny);
        army1.add(Conny);
        army2.add(Jonny);
        army2.add(Tommy);
        Battle battle = new Battle(army1, army2);
        battle.simulate("hill");
        assertFalse(army1.hasUnits() & army2.hasUnits());
    }
}