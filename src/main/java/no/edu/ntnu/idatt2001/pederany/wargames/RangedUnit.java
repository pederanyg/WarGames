package no.edu.ntnu.idatt2001.pederany.wargames;

public class RangedUnit extends Unit {

    private int resistBonus;

    /**
     * specifying the attributes of a ranged unit in a constructor, inheriting from the superclass Unit
     * @param name      the name of the unit
     * @param health    the health of the unit
     * @param attack    the unit's attacking capability
     * @param armor     the unit's defensive capability
     */
    public RangedUnit(String name, int health, int attack, int armor){
        super.unit(name, health, attack, armor);
        this.getAttackBonus();
        this.getResistBonus();
    }

    /**
     * a constructor in which the unit's attack and armor are specified
     * @param name      the name of the unit
     * @param health    the health of the unit
     */
    public RangedUnit(String name, int health){
        this(name, health, 15, 8);
    }

    /**
     * a get method for the units attack bonus
     * @return  the units attack bonus
     */
    public int getAttackBonus(){
        return 3;
    }

    /**
     * a get method for the units resist bonus
     * @return  the units resist bonus
     */
    public int getResistBonus(){
        resistBonus = 8;
        if(resistBonus > 6){
            resistBonus -= 2;
        }else if(resistBonus > 4){
            resistBonus -= 2;
        }else if(resistBonus > 2){
            resistBonus -= 2;
        }
        return resistBonus;
    }

}
