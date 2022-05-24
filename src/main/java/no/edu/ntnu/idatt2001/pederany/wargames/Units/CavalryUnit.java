package no.edu.ntnu.idatt2001.pederany.wargames.Units;

public class CavalryUnit extends Unit {

    public int attackBonus;
    public int resistBonus;

    /**
     * specifying the attributes of a cavalry unit in a constructor, inheriting from the superclass Unit
     * @param name      the name of the unit
     * @param health    the health of the unit
     * @param attack    the unit's attacking capability
     * @param armor     the unit's defensive capability
     */
    public CavalryUnit(String name, int health, int attack, int armor){
        super.unit(name, health, attack, armor);
    }

    /**
     * a constructor in which the unit's attack and armor values are specified
     * @param name      the name of the unit
     * @param health    the health of the unit
     */
    public CavalryUnit(String name, int health){
        this(name, health, 20, 12);
        this.attackBonus = 10;
        this.resistBonus = 1;
    }

    /**
     * a get method that returns the unit's attack bonus as an int
     * @return  the unit's attack bonus
     */
    public int getAttackBonus(String terrain) {
        if(attackBonus > 6) {
            attackBonus -= 4;
        } else if(attackBonus > 2) {
            attackBonus -= 4;
        }
        if(terrain.toLowerCase().trim().equals("plains")) {
            return attackBonus + 3;
        }
        return attackBonus;
    }

    /**
     * a get method that returns the unit's resist bonus as an int
     * @return  the unit's resist bonus
     */
    public int getResistBonus(String terrain) {
        if(terrain.toLowerCase().trim().equals("forest")) {
            return 0;
        }
        return resistBonus;
    }
}
