package no.edu.ntnu.idatt2001.pederany.wargames;

public class Main {

    public static void main(String[] args) {

        Army a1 = new Army("Human Army");
        Army a2 = new Army("Orchish Army");

        for(int i = 0; i < 500; i++){
            a1.add(new InfantryUnit("Footman", 100));
            a2.add(new InfantryUnit("Grunt", 100));
        }

        for(int i = 0; i < 100; i++){
            a1.add(new CavalryUnit("Knight", 100));
            a2.add(new CavalryUnit("Spider", 100));
        }

        for(int i = 0; i < 200; i++){
            a1.add(new RangedUnit("Archer", 100));
            a2.add(new RangedUnit("Spearman", 100));
        }

        a1.add(new CommanderUnit("Mountain King", 180));
        a2.add(new CommanderUnit("Gul'dan", 180));

        Battle battle = new Battle(a1, a2);
        System.out.println("The " + battle.simulate() + " is victorious!");
    }
}
