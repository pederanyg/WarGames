package no.edu.ntnu.idatt2001.pederany.wargames;

public class Battle {

    private Army armyOne;
    private Army armyTwo;

    /**
     * a constructor for a battle between two armies
     * @param armyOne   the name of army one
     * @param armyTwo   the name of army two
     */
    public Battle(Army armyOne, Army armyTwo){
        this.armyOne = armyOne;
        this.armyTwo = armyTwo;
    }

    /**
     * a method for simulating a battle between two armies
     * @return    the winner of the battle
     */
    public Army simulate() {
        while (true) {
            Unit attack1 = armyOne.getRandom();
            Unit defense2 = armyTwo.getRandom();
            attack1.attack(defense2);

            if (defense2.getHealth() < 1) {
                armyTwo.remove(defense2);
            }
            if (!(armyTwo.hasUnits())) {
                break;
            }

            Unit attack2 = armyTwo.getRandom();
            Unit defense1 = armyOne.getRandom();
            attack2.attack(defense1);

            if (defense1.getHealth() < 1) {
                armyOne.remove(defense1);
            }
            if (!(armyOne.hasUnits())) {
                break;
            }
        }

        if(armyOne.hasUnits()){
            return armyOne;
        } else {
            return armyTwo;
        }
    }
}
