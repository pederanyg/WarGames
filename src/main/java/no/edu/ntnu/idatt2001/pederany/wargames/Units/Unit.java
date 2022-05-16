package no.edu.ntnu.idatt2001.pederany.wargames.Units;

public abstract class Unit {

    private String name;
    private int health;
    private int attack;
    private int armor;

    /**
     * A method for a unit with all its attributes
     * @param name      the name of the unit
     * @param health    the health of the unit
     * @param attack    the unit's attacking capability
     * @param armor     the unit's defensive capability
     */
    public void unit(String name, int health, int attack, int armor) {
        if (health < 0) {
            throw new IllegalArgumentException("A unit's health can never be negative.");
        } else if (attack < 0) {
            throw new IllegalArgumentException("A unit's attack can never be negative.");
        } else if (armor < 0) {
            throw new IllegalArgumentException("A unit's armor can never be negative.");
        }
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.armor = armor;
    }

    /**
     * A method for an opponent's health value after an attack
     * @param opponent  the opponent's health value based on health, attacking capability and defensive capability
     */
    public void attack(Unit opponent, String terrain) {
        opponent.setHealth(opponent.getHealth()-(getAttack()+getAttackBonus(terrain)) + (opponent.getArmor()+ opponent.getResistBonus(terrain)));
    }

    /**
     * a get-method for a units name
     * @return  a units name
     */
    public String getName() {
        return name;
    }

    /**
     * a get-method for a units health
     * @return  a units health
     */
    public int getHealth() {
        return health;
    }

    /**
     * a get-method for a units attacking damage
     * @return  a units attacking damage
     */
    public int getAttack() {
        return attack;
    }

    /**
     *  a get-method for a units armor
     * @return  a units armor
     */
    public int getArmor() {
        return armor;
    }

    /**
     * a set-method for a units health
     * @param health    a units health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * a to-string method which presents a units attributes in a tidy manner
     * @return  a units attributes in the form of a string
     */
    public String toString(){
        return "Unit: " + name + ". Health: " + health + ". Attack: " + attack + ". Armor: " + armor;
    }

    /**
     * an abstract method for a units attacking bonus
     * @return  a units attack bonus
     */
    public abstract int getAttackBonus(String terrain);

    /**
     * an abstract method for a units resist bonus
     * @return  a units resist bonus
     */
    public abstract int getResistBonus(String terrain);
}
