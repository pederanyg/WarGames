package no.edu.ntnu.idatt2001.pederany.wargames;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest {

    @Test
    @DisplayName("Resistbonus will reduce by two each time, and then stabilize at 2.")

    public void reducedResistBonus() {
        RangedUnit unit_1 = new RangedUnit("Kåre", 50);
        assertEquals(6, unit_1.getResistBonus());
        assertEquals(4, unit_1.getResistBonus());
        assertEquals(2, unit_1.getResistBonus());
        assertEquals(2, unit_1.getResistBonus());
    }

    @Test
    @DisplayName("Health should be reduced when attacked.")

    public void reducedHealthWhenAttacked() {
        InfantryUnit unit_1 = new InfantryUnit("Baggo", 15);
        InfantryUnit unit_2 = new InfantryUnit("Peder", 30);
        unit_1.attack(unit_2);
        assertEquals(24, unit_2.getHealth());
    }

    @Test
    @DisplayName("The first 'charge' will yield 6 attack-bonus, and then it'll be 2.")

    public void attackBonusEveryHit() {
        CavalryUnit unit_1 = new CavalryUnit("Ron", 13);
        assertEquals(6, unit_1.getAttackBonus());
        assertEquals(2, unit_1.getAttackBonus());
        assertEquals(2, unit_1.getAttackBonus());
        assertEquals(2, unit_1.getAttackBonus());
    }
}