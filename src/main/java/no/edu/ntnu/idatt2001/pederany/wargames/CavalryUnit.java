package no.edu.ntnu.idatt2001.pederany.wargames;

public class CavalryUnit extends Unit {

    private int attackBonus;

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
        this.getAttackBonus();
        this.getResistBonus();
    }

    /**
     * a get method that returns the unit's attack bonus as an int
     * @return  the unit's attack bonus
     */
    public int getAttackBonus(){
        attackBonus = 10;
        if(attackBonus > 6){
            attackBonus -= 4;
        }
        else if(attackBonus > 2){
            attackBonus -= 4;
        }
        else if(attackBonus > 0){
            attackBonus = 2;
        }
        return attackBonus;
    }

    /**
     * a get method that returns the unit's resist bonus as an int
     * @return  the unit's resist bonus
     */
    public int getResistBonus(){
        return 1;
    }
}
