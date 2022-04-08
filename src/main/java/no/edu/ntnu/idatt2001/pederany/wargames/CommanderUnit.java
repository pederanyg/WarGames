package no.edu.ntnu.idatt2001.pederany.wargames;

public class CommanderUnit extends CavalryUnit {

    /**
     * specifying the attributes of a commander unit in a constructor, inheriting from the superclass CavalryUnit
     * @param name      the name of the unit
     * @param health    the health of the unit
     * @param attack    the unit's attacking capability
     * @param armor     the unit's defensive capability
     */
    public CommanderUnit(String name, int health, int attack, int armor){
        super(name, health, attack, armor);
    }

    /**
     * a constructor in which the unit's attack and armor are specified
     * @param name      the name of the unit
     * @param health    the health of the unit
     */
    public CommanderUnit(String name, int health){
        this(name, health, 25, 15);
    }

}
