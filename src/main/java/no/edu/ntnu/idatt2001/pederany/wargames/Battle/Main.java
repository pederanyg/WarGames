package no.edu.ntnu.idatt2001.pederany.wargames.Battle;

import no.edu.ntnu.idatt2001.pederany.wargames.Battle.Battle;
import no.edu.ntnu.idatt2001.pederany.wargames.Units.CavalryUnit;
import no.edu.ntnu.idatt2001.pederany.wargames.Units.CommanderUnit;
import no.edu.ntnu.idatt2001.pederany.wargames.Units.InfantryUnit;
import no.edu.ntnu.idatt2001.pederany.wargames.Units.RangedUnit;

public class Main {

    public static void main(String[] args) {

        Army a1 = new Army("Human Army");
        Army a2 = new Army("Dark Army");

        for(int i = 0; i < 500; i++){
            a1.add(new InfantryUnit("Barbarian", 100));
            a2.add(new InfantryUnit("Goblin", 100));
        }

        for(int i = 0; i < 100; i++){
            a1.add(new CavalryUnit("Knight", 100));
            a2.add(new CavalryUnit("Wizard", 100));
        }

        for(int i = 0; i < 200; i++){
            a1.add(new RangedUnit("Archer", 100));
            a2.add(new RangedUnit("Bowler", 100));
        }

        a1.add(new CommanderUnit("Barbarian King", 180));
        a2.add(new CommanderUnit("P.E.K.K.A.", 180));

        Battle battle = new Battle(a1, a2);
        System.out.println(battle.simulate("FOREST"));
    }
}
