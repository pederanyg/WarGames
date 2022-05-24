package no.edu.ntnu.idatt2001.pederany.wargames.Battle;

import no.edu.ntnu.idatt2001.pederany.wargames.Units.Unit;

import java.util.ArrayList;

public class Battle {

    public Army armyOne;
    public Army armyTwo;
    public ArrayList<Integer> armyOneHealthOverTime = new ArrayList<Integer>();
    public ArrayList<Integer> armyTwoHealthOverTime = new ArrayList<Integer>();

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
    public Army simulate(String terrain) {
        armyOneHealthOverTime.add(armyOne.getTotalArmyHealth());
        armyTwoHealthOverTime.add(armyTwo.getTotalArmyHealth());
        while(true) {
            Unit attack1 = armyOne.getRandom();
            Unit defense2 = armyTwo.getRandom();
            attack1.attack(defense2, terrain);

            if(defense2.getHealth() < 1) {
                armyTwo.remove(defense2);
                if(!(armyTwo.hasUnits())) {
                    armyTwoHealthOverTime.add(0);
                    // To make the lists equal in size, armyOneHealthOverTime needs another index
                    armyOneHealthOverTime.add(armyOne.getTotalArmyHealth());
                    break;
                }
            }
            armyTwoHealthOverTime.add(armyTwo.getTotalArmyHealth());

            Unit attack2 = armyTwo.getRandom();
            Unit defense1 = armyOne.getRandom();
            attack2.attack(defense1, terrain);

            if(defense1.getHealth() < 1) {
                armyOne.remove(defense1);
                if(!(armyOne.hasUnits())) {
                    armyOneHealthOverTime.add(0);
                    break;
                }
            }
            armyOneHealthOverTime.add(armyOne.getTotalArmyHealth());
        }

        if(armyOne.hasUnits()){
            return armyOne;
        } else {
            return armyTwo;
        }
    }

    /**
     * The combined health of the units in the army
     * @return the army's health
     */
    public ArrayList<Integer> getArmyOneHealthOverTime() {
        return armyOneHealthOverTime;
    }

    /**
     * The combined health of the units in the army
     * @return the army's health
     */
    public ArrayList<Integer> getArmyTwoHealthOverTime() {
        return armyTwoHealthOverTime;
    }
}
