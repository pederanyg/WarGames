package no.edu.ntnu.idatt2001.pederany.wargames.Units;

import no.edu.ntnu.idatt2001.pederany.wargames.Battle.Army;
import no.edu.ntnu.idatt2001.pederany.wargames.Units.CavalryUnit;
import no.edu.ntnu.idatt2001.pederany.wargames.Units.RangedUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest {

    @Test
    @DisplayName("Resist bonus will reduce by two each time, and then stabilize at 2.")

    public void reducedResistBonus() {
        RangedUnit unit_1 = new RangedUnit("Conny", 50);
        assertEquals(6, unit_1.getResistBonus("FOREST"));
        assertEquals(4, unit_1.getResistBonus("FOREST"));
        assertEquals(2, unit_1.getResistBonus("FOREST"));
        assertEquals(2, unit_1.getResistBonus("FOREST"));
    }

    @Test
    @DisplayName("A unit's health is reduced when attacked.")

    public void reducedHealthWhenAttacked() {
        CavalryUnit unit_1 = new CavalryUnit("Peder", 100);
        assertEquals(6, unit_1.getAttackBonus("hill"));
        assertEquals(2, unit_1.getAttackBonus("hill"));
        assertEquals(2, unit_1.getAttackBonus("hill"));
    }

    @Test
    @DisplayName("The first 'charge' will yield 6 attack-bonus, and then it will be 2.")

    public void attackBonusOnFirstAttack() {
        CavalryUnit unit_1 = new CavalryUnit("Ron", 13);
        assertEquals(6, unit_1.getAttackBonus("hill"));
        assertEquals(2, unit_1.getAttackBonus("hill"));
        assertEquals(2, unit_1.getAttackBonus("hill"));
        assertEquals(2, unit_1.getAttackBonus("hill"));
    }

    @Test
    @DisplayName("Create a new unit based on input.")

    public void createCorrectUnitFromInput() {
        Unit unit = UnitFactory.createUnit("InfantryUnit", "Nygaard", 15);
        assertTrue(unit instanceof InfantryUnit);
    }

    @Test
    @DisplayName("Exception is thrown if unit type is invalid.")
    public void wrongUnitTypeInputInFactory(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Army army = new Army("NTNU");
            Unit unit1 = UnitFactory.createUnit("Wizard", "Nygaard", 12);
            army.add(unit1);
        });
        String expectedMessage = "This unit does not exist.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}