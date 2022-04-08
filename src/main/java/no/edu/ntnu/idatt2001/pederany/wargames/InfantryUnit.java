package no.edu.ntnu.idatt2001.pederany.wargames;

public class InfantryUnit extends Unit {

    public int attackBonus;
    public int resistBonus;

    /**
     * specifying the attributes of a infantry unit in a constructor, inheriting from the superclass Unit
     * @param name      the name of the unit
     * @param health    the health of the unit
     * @param attack    the unit's attacking capability
     * @param armor     the unit's defensive capability
     */
    public InfantryUnit(String name, int health, int attack, int armor){
        super.unit(name, health, attack, armor);
        this.attackBonus = 2;
        this.resistBonus = 1;
    }

    /**
     * a constructor in which the unit's attack and armor are specified
     * @param name      the name of the unit
     * @param health    the health of the unit
     */
    public InfantryUnit(String name, int health){
        this(name, health, 15, 10);
    }

    /**
     * a get method for the units attack bonus
     * @return  the units attack bonus
     */
    public int getAttackBonus(){
        return attackBonus;
    }

    /**
     * a get method for the units resist bonus
     * @return  the units resist bonus
     */
    public int getResistBonus(){
        return resistBonus;
    }
}
